package com.bmod.fabric;

import com.bmod.BlubbysMod;
import net.fabricmc.api.ModInitializer;

public final class BlubbysModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BlubbysMod.init();
    }
}
