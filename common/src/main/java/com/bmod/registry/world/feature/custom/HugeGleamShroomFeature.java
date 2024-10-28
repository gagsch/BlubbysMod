package com.bmod.registry.world.feature.custom;

import com.bmod.registry.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HugeGleamShroomFeature extends Feature<NoneFeatureConfiguration> {

    public HugeGleamShroomFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        int height = 3 + random.nextInt(2);

        if (!world.hasChunkAt(pos) || world.getBlockState(pos.below()) != ModBlocks.MYCELIUM_DEEPERSLATE.get().defaultBlockState()) {
            return false;
        }

        for (int y = 0; y < height; y++) {
            if (!world.isEmptyBlock(pos.above(y)))
            {
                return false;
            }
        }

        for (int y = 0; y < height; y++) {
            world.setBlock(pos.above(y), Blocks.MUSHROOM_STEM.defaultBlockState(), 3);
        }

        BlockPos topPos = pos.above(height);

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (!(Math.abs(dx) + Math.abs(dz) > 2)) {
                    world.setBlock(topPos.offset(dx, 0, dz), ModBlocks.GLEAM_SHROOM_BLOCK.get().defaultBlockState(), 3);
                }
                if ((Math.abs(dx) == 2 || Math.abs(dz) == 2) && !(Math.abs(dx) == 2 && Math.abs(dz) == 2)) {
                    world.setBlock(topPos.offset(dx, -1, dz), ModBlocks.GLEAM_SHROOM_BLOCK.get().defaultBlockState(), 3);
                    world.setBlock(topPos.offset(dx, -2, dz), ModBlocks.GLEAM_SHROOM_BLOCK.get().defaultBlockState(), 3);
                }
            }
        }

        return true;
    }
}