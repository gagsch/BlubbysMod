package me.blubby.bmod.common.entity.custom;

import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.client.events.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

public class SnowFlinxEntity extends TamableAnimal {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public SnowFlinxEntity(EntityType<? extends TamableAnimal> type, Level world) {
        super(type, world);
    }

    // Register the attributes for the custom entity
    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 3f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COD), false));

        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (!this.isInSittingPose())
        {
            if(this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 20;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        }
        else {
            this.idleAnimationTimeout = 0;
            this.idleAnimationState.stop();
        }
    }

    @Nullable
    @Override
    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();

        boolean isSolidGroundBelow = world.getBlockState(pos.below()).getMaterial().isSolid();
        boolean isAboveVoid = pos.getY() > 0;

        if (world instanceof ServerLevel serverWorld) {
            Player player = serverWorld.getRandomPlayer();
            if (player != null) {
                BlockPos playerPos = player.blockPosition();
                double distance = playerPos.distSqr(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));

                return isSolidGroundBelow && isAboveVoid && distance >= 32 * 32;
            }
        }
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() { return ModSounds.SNOW_FLINX_AMBIENCE.get(); }
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) { return ModSounds.SNOW_FLINX_HURT.get(); }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SNOW_FLINX_DEATH.get();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        SnowFlinxEntity offspring = ModEntities.SNOW_FLINX.get().create(serverLevel);

        if (offspring != null && ageableMob instanceof SnowFlinxEntity) {
            SnowFlinxEntity parent = (SnowFlinxEntity) ageableMob;
            if (parent.isTame()) {
                offspring.setTame(true);
                offspring.setOwnerUUID(parent.getOwnerUUID());
            }
        }

        return offspring;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.COD);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.level.isClientSide()) {
            return this.isTame() && this.isOwnedBy(player) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }

        if (this.isTame()) {
            if (this.isOwnedBy(player)) {
                if (itemstack.is(Items.COD)) { // Healing with cod
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        this.heal(4.0F);
                        return InteractionResult.SUCCESS;
                    }
                } else if (!this.isInSittingPose()) { // Toggle sit/stand
                    this.setOrderedToSit(true);
                    return InteractionResult.SUCCESS;
                } else {
                    this.setOrderedToSit(false);
                    return InteractionResult.SUCCESS;
                }
            }
        } else if (itemstack.is(Items.COD)) { // Taming with cod
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.setOrderedToSit(true);
                this.level.broadcastEntityEvent(this, (byte) 7); // Tamed particle effect
            } else {
                this.level.broadcastEntityEvent(this, (byte) 6); // Heart particle effect (failure)
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }
}