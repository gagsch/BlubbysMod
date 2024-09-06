package me.blubby.bmod.server.world.feature;

import com.google.common.base.Suppliers;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import static me.blubby.bmod.common.blocks.custom.ModWood.*;

import me.blubby.bmod.common.blocks.custom.ModWood;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> COSMIC_OAK =
            CONFIGURED_FEATURES.register("cosmic_oak", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(ModWood.COSMIC_OAK).get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(leaves(ModWood.COSMIC_OAK).get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .dirt(BlockStateProvider.simple(ModBlocks.TEKTITE.get())).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> COSMIC_OAK_SPAWN =
            CONFIGURED_FEATURES.register("cosmic_oak_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.COSMIC_OAK_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.COSMIC_OAK_CHECKED.getHolder().get())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EBON =
            CONFIGURED_FEATURES.register("ebon", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(log(ModWood.EBON).get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(leaves(ModWood.EBON).get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .dirt(BlockStateProvider.simple(ModBlocks.TEKTITE.get())).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> EBON_SPAWN =
            CONFIGURED_FEATURES.register("ebon_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.EBON_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.EBON_CHECKED.getHolder().get())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> COSMILITE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(ModBlocks.COMPRESSED_TEKTITE.get()), ModBlocks.COSMILITE_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> COSMILITE_ORE = CONFIGURED_FEATURES.register("cosmilite_ore_placed",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(COSMILITE_ORES.get(), 9)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NECRIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(ModBlocks.COMPRESSED_TEKTITE.get()), ModBlocks.NECRIUM_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NECRIUM_ORE = CONFIGURED_FEATURES.register("necrium_ore_placed",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NECRIUM_ORES.get(), 9)));

    public static void register(IEventBus eventBus)
    {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
