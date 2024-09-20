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

import java.util.List;

public class ModOreFeatures {

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> SCARLITE_ORES;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> NECRIUM_ORES;

    public static Holder<PlacedFeature> SCARLITE_ORE_PLACED;
    public static Holder<PlacedFeature> SCARLITE_ORE_RARE_PLACED;
    public static Holder<PlacedFeature> NECRIUM_ORE_PLACED;

    public static void initialize() {
        LifecycleEvent.SETUP.register(() -> {
            SCARLITE_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":scarlite_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.SCARLITE_ORE.get().defaultBlockState(), 33));

            NECRIUM_ORES = FeatureUtils.register(BlubbysMod.MOD_ID + ":necrium_ores", Feature.ORE,
                    new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.NECRIUM_ORE.get().defaultBlockState(), 33));

            SCARLITE_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":scarlite_ore_placed", SCARLITE_ORES,
                    List.of(
                            CountPlacement.of(4),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(200)
                            )
                    )
            );

            SCARLITE_ORE_RARE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":scarlite_ore_placed_rare", SCARLITE_ORES,
                    List.of(
                            CountPlacement.of(1),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(200)
                            )
                    )
            );

            NECRIUM_ORE_PLACED = PlacementUtils.register(BlubbysMod.MOD_ID + ":necrium_ore_placed", NECRIUM_ORES,
                    List.of(
                            CountPlacement.of(4),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(200)
                            )
                    )
            );
        });
    }
}
