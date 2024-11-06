package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;

public class HastyChiselItem extends BaseAccessoryItem {
    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 0, true, false));
    }
}
