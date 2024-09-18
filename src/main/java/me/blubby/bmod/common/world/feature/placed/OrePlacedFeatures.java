package me.blubby.bmod.common.world.feature.placed;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.world.feature.configured.OreConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class OrePlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Blubby_sModOfDoom.MOD_ID);


    public static final RegistryObject<PlacedFeature> SCARLITE_ORE_PLACED = PLACED_FEATURES.register("scarlite_ore_placed",
            () -> new PlacedFeature(OreConfiguredFeatures.SCARLITE_ORE.getHolder().get(), commonOrePlacement(12,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(250)))));

    public static final RegistryObject<PlacedFeature> SCARLITE_ORE_PLACED_RARE = PLACED_FEATURES.register("scarlite_ore_placed_rare",
            () -> new PlacedFeature(OreConfiguredFeatures.SCARLITE_ORE.getHolder().get(), rareOrePlacement(4,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(250)))));

    public static final RegistryObject<PlacedFeature> NECRIUM_ORE_PLACED = PLACED_FEATURES.register("necrium_ore_placed",
            () -> new PlacedFeature(OreConfiguredFeatures.NECRIUM_ORE.getHolder().get(), commonOrePlacement(12,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(250)))));


    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) { return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome()); }
    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) { return orePlacement(CountPlacement.of(p_195344_), p_195345_); }
    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) { return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_); }
}
