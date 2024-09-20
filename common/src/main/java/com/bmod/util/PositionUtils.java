package com.bmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class PositionUtils {

    public static void savePositionToPlayer(BlockPos pos, Player player) {
        CompoundTag compoundTag = new CompoundTag();

        CompoundTag posTag = new CompoundTag();
        posTag.putInt("X", pos.getX());
        posTag.putInt("Y", pos.getY());
        posTag.putInt("Z", pos.getZ());

        compoundTag.put("SavedPosition"+player.getStringUUID(), posTag);
    }

    public static BlockPos loadPositionFromPlayer(Player player) {
        CompoundTag compoundTag = new CompoundTag();

        if (compoundTag.contains("SavedPosition", Tag.TAG_COMPOUND)) {
            CompoundTag posTag = compoundTag.getCompound("SavedPosition"+player.getStringUUID());
            int x = posTag.getInt("X");
            int y = posTag.getInt("Y");
            int z = posTag.getInt("Z");
            return new BlockPos(x, y, z);
        }
        return new BlockPos(0, 120, 0);
    }
}
