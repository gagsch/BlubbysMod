package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class SporeFlyEntity extends Monster implements FlyingAnimal {

    public final AnimationState flyingAnimationState = new AnimationState();
    private int flyingAnimationTimeout = 0;

    public SporeFlyEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);

        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = new LookControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f)
                .add(Attributes.FLYING_SPEED, 0.5f);
    }

    public static boolean spawn(EntityType<? extends Monster> entityType, LevelAccessor levelAccessor, BlockPos blockPos) {
        if (levelAccessor instanceof Level level && level.isEmptyBlock(blockPos))
        {
            return level.getEntities(new SporeFlyEntity(entityType, level), new AABB(blockPos).inflate(12)).isEmpty();
        }

        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 1D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.399999976158142, true));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(ModBlocks.GLEAM_SHROOM.get()), false));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity livingEntity && livingEntity.level instanceof ServerLevel)
        {
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.MYCOSIS.get(), 80, 0, true, true));
        }
        return super.doHurtTarget(entity);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            if (random.nextInt(30) == 0)
            {
                double offsetX = (this.level.random.nextDouble() - 0.5) * 2;
                double offsetY = (this.level.random.nextDouble() - 0.5) * 2;
                double offsetZ = (this.level.random.nextDouble() - 0.5) * 2;

                this.level.addParticle(
                        ModParticles.SPORE_PARTICLE.get(),
                        this.position().x + offsetX,
                        this.position().y + offsetY,
                        this.position().z + offsetZ,
                        0, 0, 0
                );
            }

            if(this.flyingAnimationTimeout <= 0) {
                this.flyingAnimationTimeout = this.random.nextInt(40) + 80;
                this.flyingAnimationState.start(this.tickCount);
            } else {
                --this.flyingAnimationTimeout;
            }
        }
    }

    @Override
    protected @NotNull FlyingPathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    @Override
    public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
        return false;
    }

    @Override
    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) { }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void playStepSound(BlockPos p_27820_, BlockState p_27821_) { }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ROT_FLY_BUZZ.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) { return ModSounds.ROT_FLY_HURT.get(); }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ROT_FLY_HURT.get();
    }

    @Override
    public boolean isFlying() {
        return true;
    }
}