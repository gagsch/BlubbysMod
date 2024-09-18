package me.blubby.bmod.common.world.feature.configured;

import com.google.common.collect.ImmutableList;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.block.ModBlocks;
import me.blubby.bmod.common.world.feature.placed.TreePlacedFeatures;
import me.blubby.bmod.core.util.WoodUtils;
import net.minecraft.core.Registry;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static me.blubby.bmod.core.util.WoodUtils.*;

public class TreeConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);


    public static final RegistryObject<ConfiguredFeature<?, ?>> DREADWOOD =
            CONFIGURED_FEATURES.register("dreadwood", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(WoodUtils.DREADWOOD).get()),
                            new DarkOakTrunkPlacer(15, 1, 0),
                            BlockStateProvider.simple(leaves(WoodUtils.DREADWOOD).get()),
                            new RandomSpreadFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 95),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .decorators(ImmutableList.of(
                                    TrunkVineDecorator.INSTANCE,
                                    new LeaveVineDecorator(0.15F)))
                            .dirt(BlockStateProvider.simple(ModBlocks.DARK_SOIL.get()))
                            .ignoreVines().build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DREADWOOD_SPAWN =
            CONFIGURED_FEATURES.register("dreadwood_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            TreePlacedFeatures.DREADWOOD_CHECKED.getHolder().get(),
                            0.5F)), TreePlacedFeatures.DREADWOOD_CHECKED.getHolder().get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EBON =
            CONFIGURED_FEATURES.register("ebon", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(WoodUtils.EBON).get()),
                            new DarkOakTrunkPlacer(7, 3, 2),
                            BlockStateProvider.simple(leaves(WoodUtils.EBON).get()),
                            new DarkOakFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .dirt(BlockStateProvider.simple(ModBlocks.DARK_SOIL.get()))
                            .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EBON_SPAWN =
            CONFIGURED_FEATURES.register("ebon_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            TreePlacedFeatures.EBON_CHECKED.getHolder().get(),
                            0.5F)), TreePlacedFeatures.EBON_CHECKED.getHolder().get())));
}
