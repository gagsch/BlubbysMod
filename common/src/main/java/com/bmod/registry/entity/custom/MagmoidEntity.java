package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import com.bmod.util.TickHandlerUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MagmoidEntity extends Monster {
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState groundHitAnimationState = new AnimationState();

    private Vec3 pos;
    private int lastGroundHitTick = -31;
    private final Supplier<List<Entity>> nearbyEntities = () -> this.level.getEntities(this, this.getBoundingBox().inflate(4))
            .stream()
            .filter((entity) -> friendly(entity))
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
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, (livingEntity -> !this.isEntityStationary(livingEntity))));
    }

    public boolean isEntityStationary(@Nullable LivingEntity livingEntity) {
        if (livingEntity == null) {
            return true;
        } else if (friendly(livingEntity)) {
            return true;
        }

        TickHandlerUtils.startCountdown(3, () -> {
            this.pos = new Vec3(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        });

        return pos == null || livingEntity.isCrouching() || pos.distanceTo(livingEntity.position()) < 0.01;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount % 10 == 0 && this.getTarget() != null && isEntityStationary(this.getTarget())) {
            this.setTarget(null);
        }
        if (!this.level.isClientSide()) {
            if (this.tickCount % 40 == 0 && this.level.random.nextInt(6) == 0) {
                List<Entity> entities = nearbyEntities.get();

                if (!entities.isEmpty())
                {
                    this.playSound(ModSounds.MAGMOID_ATTACK.get());
                    this.lastGroundHitTick = this.tickCount;
                    this.level.broadcastEntityEvent(this, (byte) 33);
                }
            }
            if (this.lastGroundHitTick + 15 == this.tickCount)
            {
                List<Entity> entities = nearbyEntities.get();

                damageEntities(entities);
                this.playSound(SoundEvents.GENERIC_EXPLODE);
            }
        }
    }

    public boolean friendly(Entity entity)
    {
        return entity instanceof Blaze || entity instanceof MagmoidEntity || (entity instanceof LivingEntity livingEntity && livingEntity.isDeadOrDying()) || (entity instanceof Player player && player.isCreative());
    }

    public void damageEntities(List<Entity> entities) {
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity && entity != this) {
                livingEntity.hurt(DamageSource.mobAttack(this), 10.0F);
            }
        }
    }

    @Override
    public boolean canAttack(LivingEntity livingEntity) {
        return this.lastGroundHitTick + 20 < this.tickCount;
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