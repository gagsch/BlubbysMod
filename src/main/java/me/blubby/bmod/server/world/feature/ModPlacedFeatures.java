package me.blubby.bmod.server.world.feature;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static me.blubby.bmod.utils.WoodUtils.*;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<PlacedFeature> COSMIC_OAK_CHECKED = PLACED_FEATURES.register("cosmic_oak_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.COSMIC_OAK.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(sapling(COSMIC_OAK).get()))));

    public static final RegistryObject<PlacedFeature> COSMIC_OAK_PLACED = PLACED_FEATURES.register("cosmic_oak_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.COSMIC_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> EBON_CHECKED = PLACED_FEATURES.register("ebon_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.EBON.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(sapling(EBON).get()))));

    public static final RegistryObject<PlacedFeature> EBON_PLACED = PLACED_FEATURES.register("ebon_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.EBON_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> COSMILITE_ORE_PLACED = PLACED_FEATURES.register("cosmilite_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.COSMILITE_ORE.getHolder().get(), commonOrePlacement(10,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(100)))));

    public static final RegistryObject<PlacedFeature> COSMILITE_ORE_PLACED_RARE = PLACED_FEATURES.register("cosmilite_ore_placed_rare",
            () -> new PlacedFeature(ModConfiguredFeatures.COSMILITE_ORE.getHolder().get(), rareOrePlacement(1,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(100)))));

    public static final RegistryObject<PlacedFeature> NECRIUM_ORE_PLACED = PLACED_FEATURES.register("necrium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NECRIUM_ORE.getHolder().get(), commonOrePlacement(6,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(100)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}