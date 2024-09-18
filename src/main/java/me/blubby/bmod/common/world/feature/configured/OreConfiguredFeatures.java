package me.blubby.bmod.common.world.feature.configured;

import com.google.common.base.Suppliers;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;


public class OreConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> SCARLITE_ORES = Suppliers.memoize(
            () -> List.of(OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), ModBlocks.SCARLITE_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SCARLITE_ORE = CONFIGURED_FEATURES.register("scarlite_ore_placed",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SCARLITE_ORES.get(), 9)));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NECRIUM_ORES = Suppliers.memoize(
            () -> List.of(OreConfiguration.target(new BlockMatchTest(Blocks.DEEPSLATE), ModBlocks.NECRIUM_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> NECRIUM_ORE = CONFIGURED_FEATURES.register("necrium_ore_placed",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NECRIUM_ORES.get(), 9)));

}
