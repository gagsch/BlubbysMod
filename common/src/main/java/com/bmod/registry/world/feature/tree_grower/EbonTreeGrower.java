package com.bmod.registry.world.feature.tree_grower;

import com.bmod.registry.world.feature.ModTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

public class EbonTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<? extends ConfiguredFeature<?,?>> getConfiguredFeature(@NotNull RandomSource pRandom, boolean pLargeHive)
    {
        return ModTreeFeatures.EBON;
    }
}
