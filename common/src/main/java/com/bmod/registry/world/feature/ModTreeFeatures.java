package com.bmod.registry.world.feature;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.util.WoodUtils;
import com.google.common.collect.ImmutableList;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static com.bmod.util.WoodUtils.*;

public class ModTreeFeatures {

    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> DREADWOOD;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> EBON;
    public static Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> DREADWOOD_SPAWN;
    public static Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> EBON_SPAWN;

    public static Holder<PlacedFeature> DREADWOOD_CHECKED;
    public static Holder<PlacedFeature> EBON_CHECKED;
    public static Holder<PlacedFeature> DREADWOOD_PLACED;
    public static Holder<PlacedFeature> EBON_PLACED;

    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            DREADWOOD = FeatureUtils.register(BlubbysMod.MOD_ID + ":dreadwood", Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(WoodUtils.DREADWOOD).get()),
                            new DarkOakTrunkPlacer(15, 1, 0),
                            BlockStateProvider.simple(leaves(WoodUtils.DREADWOOD).get()),
                            new RandomSpreadFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 95),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .decorators(ImmutableList.of(
                                    TrunkVineDecorator.INSTANCE,
                                    new LeaveVineDecorator(0.15F)))
                            .dirt(BlockStateProvider.simple(ModBlocks.DARK_SOIL.get()))
                            .ignoreVines().build());

            EBON = FeatureUtils.register(BlubbysMod.MOD_ID + ":ebon", Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(WoodUtils.EBON).get()),
                            new DarkOakTrunkPlacer(7, 3, 2),
                            BlockStateProvider.simple(leaves(WoodUtils.EBON).get()),
                            new DarkOakFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .dirt(BlockStateProvider.simple(ModBlocks.DARK_SOIL.get()))
                            .build());

            DREADWOOD_CHECKED = PlacementUtils.register(BlubbysMod.MOD_ID + ":dreadwood_checked", DREADWOOD,
                    List.of(
                            PlacementUtils.filteredByBlockSurvival(sapling(WoodUtils.DREADWOOD).get()),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                    ));

            EBON_CHECKED = PlacementUtils.register(BlubbysMod.MOD_ID + ":ebon_checked", EBON,
                    List.of(
                            PlacementUtils.filteredByBlockSurvival(sapling(WoodUtils.EBON).get()),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                    ));

            DREADWOOD_SPAWN = FeatureUtils.register(BlubbysMod.MOD_ID + ":dreadwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            DREADWOOD_CHECKED,
                            0.5F)), DREADWOOD_CHECKED));

            EBON_SPAWN = FeatureUtils.register(BlubbysMod.MOD_ID + ":ebon_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            EBON_CHECKED,
                            0.5F)), EBON_CHECKED));

            DREADWOOD_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":dreadwood_placed", DREADWOOD_SPAWN,
                    VegetationPlacements.treePlacement(
                            PlacementUtils.countExtra(3, 0.1f, 2)
                    )
            );

            EBON_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":ebon_placed", EBON_SPAWN,
                    VegetationPlacements.treePlacement(
                            PlacementUtils.countExtra(3, 0.1f, 2)
                    )
            );
        });
    }
}
