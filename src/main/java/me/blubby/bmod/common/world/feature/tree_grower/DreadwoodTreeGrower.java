package me.blubby.bmod.common.world.feature.tree_grower;

import me.blubby.bmod.common.world.feature.configured.TreeConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class DreadwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?,?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive)
    {
        return TreeConfiguredFeatures.DREADWOOD.getHolder().get();
    }
}
