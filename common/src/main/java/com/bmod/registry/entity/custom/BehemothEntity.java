package com.bmod.registry.entity.custom;

import com.bmod.registry.entity.ai.BehemothGroundHitGoal;
import com.bmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

public class BehemothEntity extends Monster {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BehemothEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState groundHitAnimationState = new AnimationState();
    public int groundHitAnimationTimeout = 0;

    public BehemothEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);

        this.moveControl = new MoveControl(this);
        this.lookControl = new LookControl(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ATTACK_KNOCKBACK, 3f)
                .add(Attributes.ATTACK_DAMAGE, 10f)
                .add(Attributes.ARMOR, 8)
                .add(Attributes.JUMP_STRENGTH, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true) );
        this.goalSelector.addGoal(1, new BehemothGroundHitGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Targets players
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.isAttacking() && groundHitAnimationTimeout <= 0) {
            groundHitAnimationTimeout = 55;
            groundHitAnimationState.start(this.tickCount);
        } else {
            --this.groundHitAnimationTimeout;
        }

        if(!this.isAttacking()) {
            groundHitAnimationState.stop();
        }
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity pEntity) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        return super.doHurtTarget(pEntity);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if(pId == 4) {
            this.attackAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, @NotNull MobSpawnType spawnReason) {
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
    protected SoundEvent getAmbientSound() {
        return ModSounds.BEHEMOTH_GROWL.get();
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) { return ModSounds.BEHEMOTH_HURT.get(); }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BEHEMOTH_DEATH.get();
    }
}