package me.blubby.bmod.content.world.feature.tree;

import me.blubby.bmod.content.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class MiniYggdrasilTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?,?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive)
    {
        return ModConfiguredFeatures.YGGDRASIL.getHolder().get();
    }
}
