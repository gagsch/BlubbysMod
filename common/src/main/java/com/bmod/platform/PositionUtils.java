package com.bmod.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;

public class PositionUtils {

    @ExpectPlatform
    public static void savePositionToPlayer(BlockPos pos, Player player, boolean isBlydim) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static BlockPos loadPositionFromPlayer(Player player, boolean isBlydim) {
        throw new AssertionError();
    }
}
