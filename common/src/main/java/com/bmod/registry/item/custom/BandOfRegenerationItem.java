package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class BandOfRegenerationItem extends BaseAccessoryItem {
    public int tick = 0;

    @Override
    public void accessoryTick(ServerLevel level, ServerPlayer player) {
        tick++;
        if (player.getHealth() < player.getMaxHealth() && tick >= 50) {
            tick = 0;
            player.heal(1);
        }
    }
}