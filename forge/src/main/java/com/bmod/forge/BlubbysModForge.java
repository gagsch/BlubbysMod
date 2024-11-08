package com.bmod.forge;

import com.bmod.BlubbysMod;
import com.bmod.registry.attribute.ModAttributes;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.entity.client.NightmareGatewayBlockEntityRenderer;
import com.bmod.registry.menu.AccessoryScreen;
import com.bmod.registry.menu.WorkshopScreen;
import com.bmod.registry.menu.VoidBundleScreen;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.bmod.registry.menu.ModMenus.*;

@Mod(BlubbysMod.MOD_ID)
public final class BlubbysModForge {

    public BlubbysModForge(FMLJavaModLoadingContext modLoadingContext) {
        IEventBus modEventBus = modLoadingContext.getModEventBus();
        EventBuses.registerModEventBus(BlubbysMod.MOD_ID, modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::setup);

        ModAttributes.ATTRIBUTES.register();

        BlubbysMod.init();
    }

    private void setup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(ModBlockEntityTypes.NIGHTMARE_GATEWAY_ENTITY_TYPE.get(), NightmareGatewayBlockEntityRenderer::new);
        });

        MenuScreens.register(VOID_BUNDLE_MENU_TYPE.get(), VoidBundleScreen::new);
        MenuScreens.register(WORKSHOP_MENU.get(), WorkshopScreen::new);
        MenuScreens.register(ACCESSORY_MENU.get(), AccessoryScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }

}