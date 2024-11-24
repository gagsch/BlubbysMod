package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class MagmoidEntity extends Monster {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState groundHitAnimationState = new AnimationState();

    private int lastGroundHitTick = -1000;
    private final Supplier<List<Entity>> entityCalc = () -> this.level.getEntities(this, this.getBoundingBox().inflate(4))
            .stream()
            .filter((entity) -> entity instanceof LivingEntity livingEntity && !(livingEntity instanceof Blaze || livingEntity instanceof MagmoidEntity || livingEntity.isDeadOrDying()))
            .toList();

    public MagmoidEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.maxUpStep = 1f;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.25f)
                .add(Attributes.ATTACK_DAMAGE, 12f)
                .add(Attributes.ARMOR, 7f)
                .add(Attributes.FOLLOW_RANGE, 35f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, true, (mob) -> !(mob instanceof Monster)));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level.isClientSide()) {
            List<Entity> nearbyEntities;

            if (this.tickCount % 40 == 0 && this.level.random.nextInt(6) == 0 && this.isOnGround()) {
                nearbyEntities = entityCalc.get();

                if (!nearbyEntities.isEmpty()) {
                    this.playSound(ModSounds.MAGMOID_ATTACK.get());
                    this.lastGroundHitTick = this.tickCount;
                    this.level.broadcastEntityEvent(this, (byte) 33);
                }
            }
            if (this.lastGroundHitTick + 15 == this.tickCount) {
                nearbyEntities = entityCalc.get();

                damageEntities(nearbyEntities);
                this.playSound(SoundEvents.GENERIC_EXPLODE);
            }
        }
    }

    public void damageEntities(List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.hurt(DamageSource.mobAttack(this), 10.0F);
            }
        }
    }

    @Override
    public boolean canAttack(LivingEntity livingEntity) {
        if (this.lastGroundHitTick + 20 < this.tickCount)
            return super.canAttack(livingEntity);
        return false;
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity pEntity) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        this.playSound(ModSounds.MAGMOID_ATTACK.get());
        pEntity.setSecondsOnFire(4);
        return super.doHurtTarget(pEntity);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if(pId == 4) {
            this.attackAnimationState.start(this.tickCount);
        }
        if(pId == 33) {
            this.groundHitAnimationState.start(this.tickCount);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        GroundPathNavigation groundPathNavigation = new GroundPathNavigation(this, level);
        groundPathNavigation.setAvoidSun(false);

        return groundPathNavigation;
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