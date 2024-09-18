package me.blubby.bmod.common.world.feature.placed;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.world.feature.configured.VegetationConfiguredFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class VegetationPlacedFeature {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<PlacedFeature> GRASS_PATCH_PLACED = PLACED_FEATURES.register("grass_patch_placed",
            () -> new PlacedFeature(
                    VegetationConfiguredFeature.GRASS_PATCH.getHolder().get(),
                    List.of(
                            CountPlacement.of(5),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                    )
            )
    );

    public static final RegistryObject<PlacedFeature> GLEAM_SHROOM_PATCH_PLACED = PLACED_FEATURES.register("gleam_shroom_patch_placed",
            () -> new PlacedFeature(
                    VegetationConfiguredFeature.GLEAM_SHROOM_PATCH.getHolder().get(),
                    List.of(
                            CountPlacement.of(3),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            )
    );

    public static final RegistryObject<PlacedFeature> GLEAM_SHROOM_PATCH_RARE_PLACED = PLACED_FEATURES.register("gleam_shroom_patch_rare_placed",
            () -> new PlacedFeature(
                    VegetationConfiguredFeature.GLEAM_SHROOM_PATCH.getHolder().get(),
                    List.of(
                            CountPlacement.of(1),
                            InSquarePlacement.spread(),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                            BiomeFilter.biome()
                    )
            )
    );
}