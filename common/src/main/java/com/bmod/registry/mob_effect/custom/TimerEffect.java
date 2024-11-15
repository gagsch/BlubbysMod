package com.bmod.registry.mob_effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class TimerEffect extends MobEffect {

    protected TimerEffect() {
        super(MobEffectCategory.NEUTRAL, 0xFFFF00);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        livingEntity.kill();
    }

    @Override
    public boolean isDurationEffectTick(int currentTick, int frequency) {
        return currentTick == 1;
    }
}
