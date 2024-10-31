package com.bmod.registry.world.feature;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModVegetationFeatures {

    public static Holder<PlacedFeature> GLEAM_SHROOM_PATCH_PLACED;
    public static Holder<PlacedFeature> GLEAM_SHROOM_PATCH_ABUNDANT_PLACED;
    public static Holder<PlacedFeature> GLEAM_SHROOM_PATCH_RARE_PLACED;

    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> GLEAM_SHROOM_PATCH;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> GLEAM_SHROOM_ABUNDANT_PATCH;


    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            GLEAM_SHROOM_PATCH = FeatureUtils.register(BlubbysMod.MOD_ID + ":gleam_shroom_patch", Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(2, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GLEAM_SHROOM.get())))));

            GLEAM_SHROOM_ABUNDANT_PATCH = FeatureUtils.register(BlubbysMod.MOD_ID + ":gleam_shroom_abundant_patch", Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(64, 6, 6, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GLEAM_SHROOM.get())))));

            GLEAM_SHROOM_PATCH_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":gleam_shroom_patch_placed", GLEAM_SHROOM_PATCH,
                    List.of(
                            CountPlacement.of(3),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            );

            GLEAM_SHROOM_PATCH_ABUNDANT_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":gleam_shroom_patch_abundant_placed", GLEAM_SHROOM_ABUNDANT_PATCH,
                    List.of(
                            CountPlacement.of(96),
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-59), VerticalAnchor.absolute(30)),
                            InSquarePlacement.spread(),
                            BiomeFilter.biome()
                    )
            );

            GLEAM_SHROOM_PATCH_RARE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":gleam_shroom_patch_rare_placed", GLEAM_SHROOM_PATCH,
                    List.of(
                            CountPlacement.of(1),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            );
        });
    }
}
