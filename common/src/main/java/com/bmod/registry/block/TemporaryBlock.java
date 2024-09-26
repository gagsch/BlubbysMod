package com.bmod.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TemporaryBlock extends Block {

    private final int lifespan;
    private final Block block;

    public TemporaryBlock(Properties properties, Block setToBlock, int lifespan) {
        super(properties);
        this.block = setToBlock;
        this.lifespan = lifespan;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, lifespan);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, block.defaultBlockState());
    }
}
