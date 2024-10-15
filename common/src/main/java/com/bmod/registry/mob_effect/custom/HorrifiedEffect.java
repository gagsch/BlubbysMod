package com.bmod.registry.mob_effect.custom;

import com.bmod.registry.mob_effect.ModMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class HorrifiedEffect extends MobEffect {

    protected HorrifiedEffect() {
        super(MobEffectCategory.HARMFUL, 0x551255);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        if (livingEntity.level.dimension() == Level.OVERWORLD) {
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.CARDIAC_ARREST.get(), 1, 0, false, false));
        }
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }
}
