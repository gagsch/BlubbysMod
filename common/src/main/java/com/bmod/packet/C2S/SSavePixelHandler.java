package com.bmod.packet.C2S;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.registry.block.custom.PixelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SSavePixelHandler {
    public static void handle(Level level, Player player, BlockPos blockPos, int r, int g, int b) {
        if (level.getBlockEntity(blockPos) instanceof PixelBlockEntity pixel && player instanceof ServerPlayer)
        {
            double x = blockPos.getX();
            double y = blockPos.getY();
            double z = blockPos.getZ();

            if (player.getEyePosition().distanceToSqr(x + 0.5f, y, z + 0.5f) < 9 * 9
                    && pixel.isOwner(player)) {

                pixel.setColor(r, g, b);
            }
        }
    }
}