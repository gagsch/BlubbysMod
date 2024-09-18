package me.blubby.bmod.common.world.feature.configured;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class VegetationConfiguredFeature {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> GRASS_PATCH = CONFIGURED_FEATURES.register("grass_patch",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS))))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> GLEAM_SHROOM_PATCH = CONFIGURED_FEATURES.register("gleam_shroom_patch",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(2, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.GLEAM_SHROOM.get()))))));
}