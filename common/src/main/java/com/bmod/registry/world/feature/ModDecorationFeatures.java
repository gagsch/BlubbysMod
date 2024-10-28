package com.bmod.registry.world.feature;

import com.bmod.BlubbysMod;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.WouldSurvivePredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModDecorationFeatures {

    public static Holder<PlacedFeature> COBWEB_PATCH_PLACED;

    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> COBWEB_PATCH;

    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            COBWEB_PATCH = FeatureUtils.register(BlubbysMod.MOD_ID + ":cobweb_patch", Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(64, 8, 8, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.COBWEB)))));

            COBWEB_PATCH_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":cobweb_patch_placed", COBWEB_PATCH,
                    List.of(
                            CountPlacement.of(192),
                            InSquarePlacement.spread(),
                            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                            BiomeFilter.biome()
                    )
            );
        });
    }
}
