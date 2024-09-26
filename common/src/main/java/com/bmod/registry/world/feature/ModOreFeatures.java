package com.bmod.registry.world.feature;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ModOreFeatures {

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> RUBY_ORES;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPSLATE_RUBY_ORES;

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> SCARLITE_ORES;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPER_SCARLITE_ORES;

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> NECRIUM_ORES;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPER_NECRIUM_ORES;


    public static Holder<PlacedFeature> RUBY_ORE_PLACED;
    public static Holder<PlacedFeature> DEEPSLATE_RUBY_ORE_PLACED;

    public static Holder<PlacedFeature> SCARLITE_ORE_PLACED;
    public static Holder<PlacedFeature> DEEPER_SCARLITE_ORE_PLACED;
    public static Holder<PlacedFeature> DEEPER_SCARLITE_ORE_RARE_PLACED;

    public static Holder<PlacedFeature> NECRIUM_ORE_PLACED;
    public static Holder<PlacedFeature> DEEPER_NECRIUM_ORE_PLACED;

    public static void initialize() {

        LifecycleEvent.SETUP.register(() -> {
            RUBY_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":ruby_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.get().defaultBlockState(), 5));
            DEEPSLATE_RUBY_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":deepslate_ruby_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_RUBY_ORE.get().defaultBlockState(), 5));

            SCARLITE_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":scarlite_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.SCARLITE_ORE.get().defaultBlockState(), 6));
            DEEPER_SCARLITE_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":deeper_scarlite_ores", Feature.ORE,
                    new OreConfiguration(new BlockMatchTest(ModBlocks.DEEPERSLATE.get()), ModBlocks.DEEPERSLATE_SCARLITE_ORE.get().defaultBlockState(), 8));

            NECRIUM_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":necrium_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.NECRIUM_ORE.get().defaultBlockState(), 6));
            DEEPER_NECRIUM_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":deeper_necrium_ores", Feature.ORE,
                    new OreConfiguration(new BlockMatchTest(ModBlocks.DEEPERSLATE.get()), ModBlocks.DEEPERSLATE_NECRIUM_ORE.get().defaultBlockState(), 6));


            RUBY_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":ruby_ore_placed", RUBY_ORES,
                    List.of(
                            CountPlacement.of(4),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)
                            )
                    )
            );

            DEEPSLATE_RUBY_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":deepslate_ruby_ore_placed", DEEPSLATE_RUBY_ORES,
                    List.of(
                            CountPlacement.of(4),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)
                            )
                    )
            );

            SCARLITE_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":scarlite_ore_placed", SCARLITE_ORES,
                    List.of(
                            CountPlacement.of(3),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(35)
                            )
                    )
            );

            DEEPER_SCARLITE_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":deeper_scarlite_ore_placed", DEEPER_SCARLITE_ORES,
                    List.of(
                            CountPlacement.of(5),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)
                            )
                    )
            );

            DEEPER_SCARLITE_ORE_RARE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":deeper_scarlite_ore_placed_rare", DEEPER_SCARLITE_ORES,
                    List.of(
                            CountPlacement.of(2),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)
                            )
                    )
            );

            NECRIUM_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":necrium_ore_placed", NECRIUM_ORES,
                    List.of(
                            CountPlacement.of(4),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(55)
                            )
                    )
            );

            DEEPER_NECRIUM_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":deeper_necrium_ore_placed", DEEPER_NECRIUM_ORES,
                    List.of(
                            CountPlacement.of(6),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)
                            )
                    )
            );
        });
    }
}
