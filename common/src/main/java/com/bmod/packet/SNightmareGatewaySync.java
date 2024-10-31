package com.bmod.packet;

import com.bmod.registry.block.block_entity.custom.NightmareGatewayBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SNightmareGatewaySync {
    public static void handle(MinecraftServer server, ResourceLocation location, BlockPos blockPos, boolean hasGem, Player player)
    {
        ResourceKey<Level> levelResourceKey = Level.OVERWORLD;

        for (ResourceKey<Level> key : server.levelKeys()) {
            if (key.location() == location) {
                levelResourceKey = key;
                break;
            }
        }

        ServerLevel level = server.getLevel(levelResourceKey);

        if (level.getBlockEntity(blockPos) instanceof NightmareGatewayBlockEntity blockEntity && blockEntity.hasGem != hasGem)
        {
            if (player instanceof ServerPlayer sPlayer)
            {
                new S2CSyncNightmareGatewayMessage(blockPos, blockEntity.hasGem).sendTo(sPlayer);
            }
        }
    }
}
