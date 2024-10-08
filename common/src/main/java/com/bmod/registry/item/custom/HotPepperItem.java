package com.bmod.registry.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.Nullable;

public class HotPepperItem extends ToolTipItem {
    public HotPepperItem(Properties properties) {
        super(properties, tooltips.HOT_PEPPER);
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Nullable
    @Override
    public FoodProperties getFoodProperties() {
        return new FoodProperties.Builder()
                .nutrition(3)
                .saturationMod(3F)
                .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1), 1F)
                .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0), 1F)
                .alwaysEat()
                .build();
    }
}