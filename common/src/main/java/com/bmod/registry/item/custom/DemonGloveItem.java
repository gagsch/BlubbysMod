package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class DemonGloveItem extends LavaRingItem {
    public int tick = 0;

    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        tick++;
        if(player.isOnFire() && tick >= 40) {
            tick = 0;
            player.heal(1);
        }
        super.serverAccessoryTick(level, player);
    }
}
