package me.blubby.bmod.common.world.feature.placed;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.world.feature.configured.TreeConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static me.blubby.bmod.core.util.WoodUtils.*;

public class TreePlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);


    public static final RegistryObject<PlacedFeature> DREADWOOD_CHECKED = PLACED_FEATURES.register("dreadwood_checked",
            () -> new PlacedFeature(TreeConfiguredFeatures.DREADWOOD.getHolder().get(),
                    List.of(
                            PlacementUtils.filteredByBlockSurvival(sapling(DREADWOOD).get()),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                    )));

    public static final RegistryObject<PlacedFeature> DREADWOOD_PLACED = PLACED_FEATURES.register("dreadwood_placed",
            () -> new PlacedFeature(TreeConfiguredFeatures.DREADWOOD_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2)
            )));


    public static final RegistryObject<PlacedFeature> EBON_CHECKED = PLACED_FEATURES.register("ebon_checked",
            () -> new PlacedFeature(TreeConfiguredFeatures.EBON.getHolder().get(),
                    List.of(
                            PlacementUtils.filteredByBlockSurvival(sapling(EBON).get()),
                            PlacementUtils.HEIGHTMAP_WORLD_SURFACE
                    )));

    public static final RegistryObject<PlacedFeature> EBON_PLACED = PLACED_FEATURES.register("ebon_placed",
            () -> new PlacedFeature(TreeConfiguredFeatures.EBON_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2)
            )));
}
