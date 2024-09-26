package com.bmod.registry.block;

import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DarkGrassBlock extends DarkSoilBlock implements BonemealableBlock {

    public DarkGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!canBeGrass(serverLevel, blockPos))
        {
            serverLevel.setBlockAndUpdate(blockPos, ModBlocks.DARK_SOIL.get().defaultBlockState());
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, @NotNull BlockState blockState, boolean valid) {
        return blockGetter.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource randomSource, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel serverLevel, @NotNull RandomSource randomSource, BlockPos blockPos, @NotNull BlockState blockState) {

        BlockPos blockPosAbove = blockPos.above();
        BlockState grassState = Blocks.GRASS.defaultBlockState();

        label46:
        for (int attempt = 0; attempt < 128; ++attempt) {

            BlockPos currentPos = blockPosAbove;

            for (int offsetCount = 0; offsetCount < attempt / 16; ++offsetCount) {

                currentPos = currentPos.offset(randomSource.nextInt(3) - 1, (randomSource.nextInt(3) - 1) * randomSource.nextInt(3) / 2, randomSource.nextInt(3) - 1);
                if (!serverLevel.getBlockState(currentPos.below()).is(this) || serverLevel.getBlockState(currentPos).isCollisionShapeFullBlock(serverLevel, currentPos)) {
                    continue label46;
                }

            }

            BlockState currentState = serverLevel.getBlockState(currentPos);

            if (currentState.is(grassState.getBlock()) && randomSource.nextInt(10) == 0) {
                ((BonemealableBlock) grassState.getBlock()).performBonemeal(serverLevel, randomSource, currentPos, currentState);
            }

            if (currentState.isAir()) {
                VegetationPlacements.GRASS_BONEMEAL.value().place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, currentPos);
            }
        }
    }
}
