package me.blubby.bmod.common.world.feature;

import me.blubby.bmod.common.world.feature.configured.OreConfiguredFeatures;
import me.blubby.bmod.common.world.feature.configured.TreeConfiguredFeatures;
import me.blubby.bmod.common.world.feature.configured.VegetationConfiguredFeature;
import me.blubby.bmod.common.world.feature.placed.OrePlacedFeatures;
import me.blubby.bmod.common.world.feature.placed.TreePlacedFeatures;
import me.blubby.bmod.common.world.feature.placed.VegetationPlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModFeatures {
    public static void register(IEventBus eventBus) {
        TreeConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        OreConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        VegetationConfiguredFeature.CONFIGURED_FEATURES.register(eventBus);

        TreePlacedFeatures.PLACED_FEATURES.register(eventBus);
        OrePlacedFeatures.PLACED_FEATURES.register(eventBus);
        VegetationPlacedFeature.PLACED_FEATURES.register(eventBus);
    }
}
