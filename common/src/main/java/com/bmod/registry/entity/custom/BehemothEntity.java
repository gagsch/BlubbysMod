package com.bmod.registry.entity.custom;

import com.bmod.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class BehemothEntity extends Monster {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BehemothEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();

    public BehemothEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.maxUpStep = 1f;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 65D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5f)
                .add(Attributes.ATTACK_DAMAGE, 10f)
                .add(Attributes.ARMOR, 14)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.JUMP_STRENGTH, 1)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true) );
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static boolean spawn(EntityType<? extends Monster> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        if (levelAccessor instanceof Level level)
        {
            return level.getEntities(new BehemothEntity(entityType, level), new AABB(blockPos).inflate(12)).isEmpty();
        }

        return false;
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