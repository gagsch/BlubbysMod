package com.bmod.packet.C2S;

import com.bmod.registry.block.block_entity.custom.FrogExecutorBlockEntity;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SSaveCodeHandler {
    public static void handle(Level level, Player player, BlockPos blockPos, String code) {
        if (level.getBlockEntity(blockPos) instanceof FrogExecutorBlockEntity frog && player instanceof ServerPlayer)
        {
            double x = blockPos.getX();
            double y = blockPos.getY();
            double z = blockPos.getZ();

            if (player.getEyePosition().distanceToSqr(x + 0.5f, y, z + 0.5f) < 9 * 9
                    && frog.isOwner(player)) {

                frog.setCode(level, code);
            }
        }
    }
}
