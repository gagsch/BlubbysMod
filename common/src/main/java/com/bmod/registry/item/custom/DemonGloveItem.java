package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class DemonGloveItem extends LavaRingItem implements IAccessoryItem {
    public int tick = 0;

    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {
        tick++;
        if(player.wasOnFire && tick % 20 == 0) {
            player.heal(1);
        }
        super.accessoryTick(level, player);
    }
}
