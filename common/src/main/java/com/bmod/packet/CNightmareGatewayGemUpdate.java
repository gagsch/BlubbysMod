package com.bmod.packet;

import com.bmod.registry.block.block_entity.custom.NightmareGatewayBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

@Environment(EnvType.CLIENT)
public class CNightmareGatewayGemUpdate {
    public static void handle(BlockPos blockPos, boolean hasGem)
    {
        BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(blockPos);
        if (blockEntity instanceof NightmareGatewayBlockEntity newBlockEntity)
        {
            newBlockEntity.hasGem = hasGem;
        }
    }
}
