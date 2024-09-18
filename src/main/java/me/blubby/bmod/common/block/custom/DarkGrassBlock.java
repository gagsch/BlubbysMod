package me.blubby.bmod.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class DarkGrassBlock extends Block implements BonemealableBlock {

    public DarkGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_53692_, BlockPos p_53693_, BlockState p_53694_, boolean p_53695_) {
        return p_53692_.getBlockState(p_53693_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_221275_, RandomSource p_221276_, BlockPos p_221277_, BlockState p_221278_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_) {
        BlockPos $$4 = p_221272_.above();
        BlockState $$5 = Blocks.GRASS.defaultBlockState();

        label46:
        for (int $$6 = 0; $$6 < 128; ++$$6) {
            BlockPos $$7 = $$4;

            for (int $$8 = 0; $$8 < $$6 / 16; ++$$8) {
                $$7 = $$7.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3) - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
                if (!p_221270_.getBlockState($$7.below()).is(this) || p_221270_.getBlockState($$7).isCollisionShapeFullBlock(p_221270_, $$7)) {
                    continue label46;
                }
            }

            BlockState $$9 = p_221270_.getBlockState($$7);
            if ($$9.is($$5.getBlock()) && p_221271_.nextInt(10) == 0) {
                ((BonemealableBlock) $$5.getBlock()).performBonemeal(p_221270_, p_221271_, $$7, $$9);
            }

            if ($$9.isAir()) {
                Holder $$12;
                if (p_221271_.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> $$10 = ((Biome) p_221270_.getBiome($$7).value()).getGenerationSettings().getFlowerFeatures();
                    if ($$10.isEmpty()) {
                        continue;
                    }

                    $$12 = ((RandomPatchConfiguration) ((ConfiguredFeature) $$10.get(0)).config()).feature();
                } else {
                    $$12 = VegetationPlacements.GRASS_BONEMEAL;
                }

                ((PlacedFeature) $$12.value()).place(p_221270_, p_221270_.getChunkSource().getGenerator(), p_221271_, $$7);
            }
        }
    }
}
