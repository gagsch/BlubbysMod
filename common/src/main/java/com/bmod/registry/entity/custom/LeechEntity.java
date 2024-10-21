package com.bmod.registry.entity.custom;

import com.bmod.packet.S2CEntityRidingMessage;
import com.bmod.registry.ModSounds;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class LeechEntity extends WaterAnimal implements Enemy {

    public final AnimationState ridingAnimationState = new AnimationState();

    public LeechEntity(EntityType<? extends WaterAnimal> type, Level world) {
        super(type, world);

        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5D)
                .add(Attributes.JUMP_STRENGTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 2D)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 0)
                .add(Attributes.KNOCKBACK_RESISTANCE, -1);
    }

    @Override
    protected void handleAirSupply(int i) { }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.25D;
    }

    @Override
    public boolean canBeLeashed(Player arg) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0, 10));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new LeechSwimGoal(this, 1D));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isPassenger())
        {
            Entity vehicle = this.getVehicle();

            if (vehicle != null && vehicle.isAlive() && !this.isDeadOrDying())
            {
                vehicle.hurt(DamageSource.GENERIC, 3);
                this.setYRot(vehicle.getYRot());

                if (!this.ridingAnimationState.isStarted()) {
                    this.ridingAnimationState.start(1);
                    if (vehicle instanceof ServerPlayer player) {
                        new S2CEntityRidingMessage(player, this, true).sendToLevel(player.getLevel());
                    }
                }
            }
        }
    }

    @Override
    public void travel(Vec3 vec3) {
        LivingEntity target = this.getTarget();

        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));

            Vec3 deltaMovement = this.getDeltaMovement();

            if (target == null || !target.isInWater()) {
                this.setDeltaMovement(deltaMovement.x, Math.max(deltaMovement.y - 0.005, -0.01), deltaMovement.z);
            }
        } else {
            super.travel(vec3);
        }
    }


    @Override
    public void stopRiding() {
        this.ridingAnimationState.stop();

        if (this.getVehicle() instanceof ServerPlayer player)
        {
            new S2CEntityRidingMessage(player, this, false).sendToLevel(player.getLevel());
        }

        super.stopRiding();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.LEECH_AMBIENCE.get();
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.LEECH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.LEECH_DEATH.get();
    }

    static class LeechSwimGoal extends Goal {
        private final LeechEntity leech;
        private final double speed;

        public LeechSwimGoal(LeechEntity leech, double speed) {
            this.leech = leech;
            this.speed = speed;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return leech.getTarget() != null;
        }

        @Override
        public void tick() {
            LivingEntity target = leech.getTarget();
            if (target == null){
                leech.getNavigation().setCanFloat(leech.isInWater());
                return;
            }

            if (leech.level.isWaterAt(target.blockPosition().below()) || target.isInWater()) {
                if (leech.isInWater() && target.position().y > leech.position().y)
                {
                    leech.getNavigation().setCanFloat(true);

                    if (leech.distanceTo(target) < 5)
                        leech.setDeltaMovement(leech.getDeltaMovement().x, .15, leech.getDeltaMovement().z);
                }
                else
                    leech.getNavigation().setCanFloat(false);

                leech.lookAt(target, 0, 0);
                leech.getNavigation().moveTo(target, speed);
            }

            if (leech.getBoundingBox().intersects(target.getBoundingBox()) && !target.isVehicle() && !target.isDeadOrDying()) {
                if (target instanceof ServerPlayer player && !player.isCreative())
                {
                    leech.startRiding(target);
                }
            }
        }
    }
}