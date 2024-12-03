package com.bmod.forge;

import com.bmod.BlubbysMod;
import com.bmod.registry.attribute.ModAttributes;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.client.renderer.DimensionGatewayBlockEntityRenderer;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.registry.screen.AccessoryScreen;
import com.bmod.registry.screen.WorkshopScreen;
import com.bmod.registry.screen.VoidBundleScreen;
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.bmod.registry.screen.ModMenus.*;

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
            BlockEntityRenderers.register(ModBlockEntityTypes.DIMENSION_GATEWAY_ENTITY_TYPE.get(), DimensionGatewayBlockEntityRenderer::new);
        });

        ColorHandlerRegistry.registerBlockColors(((blockState, blockAndTintGetter, blockPos, i) -> {
            if (blockAndTintGetter.getBlockEntity(blockPos) instanceof PixelBlockEntity pixel) {
                return (pixel.getColor()[0] << 16) | (pixel.getColor()[1] << 8) | pixel.getColor()[2];
            }
            return 0xFFFFFF;
        }), ModBlocks.PIXEL_BLOCK);

        MenuScreens.register(VOID_BUNDLE_MENU_TYPE.get(), VoidBundleScreen::new);
        MenuScreens.register(WORKSHOP_MENU.get(), WorkshopScreen::new);
        MenuScreens.register(ACCESSORY_MENU.get(), AccessoryScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }

}