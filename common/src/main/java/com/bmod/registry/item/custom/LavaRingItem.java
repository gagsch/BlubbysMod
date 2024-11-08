package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class LavaRingItem extends BaseAccessoryItem {
    @Override
    public void accessoryTick(ServerLevel level, ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10, 0, true, false));
    }
}
