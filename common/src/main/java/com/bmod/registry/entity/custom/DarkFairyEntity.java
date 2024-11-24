package com.bmod.registry.entity.custom;

import com.bmod.registry.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class DarkFairyEntity extends Monster implements FlyingAnimal {

    public final AnimationState flyingAnimationState = new AnimationState();
    private int flyingAnimationTimeout = 0;

    public DarkFairyEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = new LookControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.FOLLOW_RANGE, 16D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 1f)
                .add(Attributes.FLYING_SPEED, 0.5f);
    }

    public static boolean spawn(EntityType<? extends Monster> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        if (levelAccessor instanceof Level level)
        {
            return level.getEntities(new DarkFairyEntity(entityType, level), new AABB(blockPos).inflate(24)).isEmpty();
        }

        return false;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 1D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.399999976158142, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            if (random.nextInt(20) == 0)
            {
                double offsetX = (this.level.random.nextDouble() - 0.5) * 2;
                double offsetY = (this.level.random.nextDouble() - 0.5) * 2;
                double offsetZ = (this.level.random.nextDouble() - 0.5) * 2;

                this.level.addParticle(
                        ModParticles.FAIRY_DUST_PARTICLE.get(),
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
    protected void playStepSound(BlockPos p_27820_, BlockState p_27821_) { }

    @Override
    public boolean isFlying() {
        return true;
    }
}
