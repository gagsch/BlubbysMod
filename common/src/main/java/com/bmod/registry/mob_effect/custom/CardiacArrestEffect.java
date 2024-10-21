package com.bmod.registry.mob_effect.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CardiacArrestEffect extends MobEffect {

    protected CardiacArrestEffect() {
        super(MobEffectCategory.HARMFUL, 0x551212);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.hurt(DamageSource.OUT_OF_WORLD, 5);
    }

    @Override
    public boolean isDurationEffectTick(int remainingTicks, int frequency) {
        return remainingTicks == 2;
    }
}
