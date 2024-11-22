package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MagmoidEntity extends Monster {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(MagmoidEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState groundHitAnimationState = new AnimationState();
    public int groundHitAnimationTimeout = 0;

    public MagmoidEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.maxUpStep = 1f;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40f)
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.ARMOR_TOUGHNESS, 6f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.25f)
                .add(Attributes.ATTACK_DAMAGE, 12f)
                .add(Attributes.ARMOR, 7f)
                .add(Attributes.FOLLOW_RANGE, 24f)
                .add(Attributes.JUMP_STRENGTH, 2f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.7f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false, (MagmoidEntity::isEntityInMotion)));
    }

    public static boolean isEntityInMotion(@Nullable LivingEntity livingEntity) {
        if (livingEntity == null) {
            return false;
        }
        else if (livingEntity instanceof Monster) {
            return false;
        }

        boolean stationary = livingEntity.xOld == livingEntity.getX() &&
                livingEntity.yOld == livingEntity.getY() &&
                livingEntity.zOld == livingEntity.getZ();

        return !stationary;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            setupAnimationStates();
        }
        else if (!isEntityInMotion(this.getTarget())) {
            this.setTarget(null);
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
    public boolean doHurtTarget(@NotNull Entity pEntity) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        pEntity.setSecondsOnFire(4);
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

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getTarget() != null) {
            return ModSounds.MAGMOID_COMBAT.get();
        }
        return ModSounds.MAGMOID_CURIOUS.get();
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.MAGMOID_HURT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MAGMOID_DEATH.get();
    }
}