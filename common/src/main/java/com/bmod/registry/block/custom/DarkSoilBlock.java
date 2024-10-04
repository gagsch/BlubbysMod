package com.bmod.registry.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DarkSoilBlock extends Block {

    public DarkSoilBlock(Properties properties) {
        super(properties);
    }

    public static boolean canBeGrass(ServerLevel level, BlockPos blockPos) {
        BlockPos blockPosAbove = blockPos.above();
        BlockState blockAbove = level.getBlockState(blockPosAbove);

        return !blockAbove.isCollisionShapeFullBlock(level, blockPosAbove);
    }
}
