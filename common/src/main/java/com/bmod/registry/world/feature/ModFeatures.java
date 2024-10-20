package com.bmod.registry.world.feature;

import com.bmod.registry.ModTags;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModFeatures {

    public static void init()
    {
        ModOreFeatures.initialize();
        ModTreeFeatures.initialize();
        ModVegetationFeatures.initialize();
        ModDecorationFeatures.initialize();

        ModTags.initialize();

        LifecycleEvent.SETUP.register(() -> {
            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(BiomeTags.IS_OVERWORLD)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.RUBY_ORE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPSLATE_RUBY_ORE_PLACED);
                }
            });

            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(ModTags.IS_WEEPING_FOREST)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModTreeFeatures.DREADWOOD_PLACED);

                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationFeatures.GLEAM_SHROOM_PATCH_RARE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_RIVER);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.KELP_COLD);

                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPER_DREADIUM_ORE_RARE_PLACED);
                }
            });

            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(ModTags.IS_NECROSIS_WEALD)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModTreeFeatures.EBON_PLACED);

                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegetationFeatures.GLEAM_SHROOM_PATCH_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_JUNGLE);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.KELP_WARM);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPER_DREADIUM_ORE_RARE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.NECRIUM_ORE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPER_NECRIUM_ORE_PLACED);
                }
            });

            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(ModTags.IS_SPIDER_DEN)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);

                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModDecorationFeatures.COBWEB_PATCH_PLACED);
                }
            });

            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(ModTags.IS_PLAINS)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DREADIUM_ORE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPER_DREADIUM_ORE_PLACED);
                }
            });

            BiomeModifications.addProperties((ctx, mutable) -> {
                if (ctx.hasTag(ModTags.IS_STONE_PLAINS)) {
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DREADIUM_ORE_PLACED);
                    mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModOreFeatures.DEEPER_DREADIUM_ORE_PLACED);
                }
            });
        });
    }
}
