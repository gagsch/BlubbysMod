package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RotFlyEntity extends Animal implements NeutralMob, FlyingAnimal {

    public final AnimationState flyingAnimationState = new AnimationState();
    private int flyingAnimationTimeout = 0;

    public RotFlyEntity(EntityType<? extends Animal> type, Level world) {
        super(type, world);

        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = new LookControl(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f)
                .add(Attributes.FLYING_SPEED, 0.5f);
    }

    public static boolean spawn(EntityType<? extends Mob> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 1D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.399999976158142, true));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.ROTTEN_FLESH), false));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level.isClientSide()) {
            setupAnimationStates();
        }
    }

    @Override
    protected @NotNull FlyingPathNavigation createNavigation(Level level) {
        return new FlyingPathNavigation(this, level);
    }

    private void setupAnimationStates() {
        if(this.flyingAnimationTimeout <= 0) {
            this.flyingAnimationTimeout = this.random.nextInt(40) + 80;
            this.flyingAnimationState.start(this.tickCount);
        } else {
            --this.flyingAnimationTimeout;
        }
    }

    public String getAnimationState() {
        return "flying";
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

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) { }

    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable UUID uuid) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }
}