package me.blubby.bmod.content.world.feature;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.content.blocks.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> COSMIC_OAK =
            CONFIGURED_FEATURES.register("cosmic_oak", () ->
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.COSMIC_OAK_LOG.get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(ModBlocks.COSMIC_OAK_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(ModBlocks.TEKTITE.get())).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> COSMIC_OAK_SPAWN =
            CONFIGURED_FEATURES.register("cosmic_oak_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.COSMIC_OAK_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.COSMIC_OAK_CHECKED.getHolder().get())));

    public static void register(IEventBus eventBus)
    {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
