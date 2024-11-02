package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public interface IAccessoryItem {
    void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player);
}
