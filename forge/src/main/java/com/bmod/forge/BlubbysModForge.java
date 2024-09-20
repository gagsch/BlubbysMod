package com.bmod.forge;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.VoidBundleMenu;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.bmod.registry.menu.ModMenus.ENDER_CHEST_UPGRADE_MENU;

@Mod(BlubbysMod.MOD_ID)
public final class BlubbysModForge {

    public BlubbysModForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(BlubbysMod.MOD_ID, modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::setup);

        BlubbysMod.init();
    }

    private void setup(FMLClientSetupEvent event) {
        MenuScreens.register(ENDER_CHEST_UPGRADE_MENU.get(), VoidBundleMenu::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }

}