package com.bmod.platform.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;

public class PositionUtilsImpl {

    public static void savePositionToPlayer(BlockPos pos, Player player, boolean isBlydim) {
        CompoundTag playerData = player.getPersistentData();

        CompoundTag posTag = new CompoundTag();
        posTag.putInt("X", pos.getX());
        posTag.putInt("Y", pos.getY());
        posTag.putInt("Z", pos.getZ());

        if (isBlydim) {
            playerData.put("Blydim", posTag);
        } else {
            playerData.put("Overworld", posTag);
        }
    }

    public static BlockPos loadPositionFromPlayer(Player player, boolean isBlydim) {
        CompoundTag playerData = player.getPersistentData();
        String key = isBlydim ? "Blydim" : "Overworld";

        if (playerData.contains(key, Tag.TAG_COMPOUND)) {
            CompoundTag posTag = playerData.getCompound(key);
            int x = posTag.getInt("X");
            int y = posTag.getInt("Y");
            int z = posTag.getInt("Z");
            return new BlockPos(x, y, z);
        }
        return new BlockPos(0, 120, 0);
    }
}
