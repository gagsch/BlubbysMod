package com.bmod.registry.entity.custom;

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
import org.jetbrains.annotations.NotNull;

public class LeechEntity extends WaterAnimal implements Enemy {
    public ServerPlayer latchedPlayer;

    public LeechEntity(EntityType<? extends WaterAnimal> type, Level world) {
        super(type, world);

        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return WaterAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5D)
                .add(Attributes.MOVEMENT_SPEED, 2D)
                .add(Attributes.JUMP_STRENGTH, 1D)
                .add(Attributes.ATTACK_DAMAGE, 1f)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 0)
                .add(Attributes.KNOCKBACK_RESISTANCE, -1);
    }

    @Override
    protected void handleAirSupply(int i) {

    }

    @Override
    public boolean isPushedByFluid() {
        return false;
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
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2000000476837158D, true));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        this.getNavigation().setCanFloat(this.isInWater());

        if(latchedPlayer != null) {
            this.startRiding(latchedPlayer);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof ServerPlayer player)
        {
            latchedPlayer = player;
        }

        return super.doHurtTarget(entity);
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
        }

        @Override
        public boolean canUse() {
            return this.leech.getTarget() != null;
        }

        @Override
        public void tick() {
            LivingEntity target = this.leech.getTarget();
            if (target != null) {
                this.leech.getNavigation().moveTo(target, speed);
            }
        }
    }
}