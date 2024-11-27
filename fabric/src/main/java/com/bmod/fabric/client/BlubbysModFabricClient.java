package com.bmod.fabric.client;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.client.renderer.DimensionGatewayBlockEntityRenderer;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.registry.screen.AccessoryScreen;
import com.bmod.registry.screen.WorkshopScreen;
import com.bmod.registry.screen.VoidBundleScreen;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import static com.bmod.registry.screen.ModMenus.*;
import static com.bmod.util.WoodUtils.*;

public final class BlubbysModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorHandlerRegistry.registerBlockColors(((blockState, blockAndTintGetter, blockPos, i) -> {
            if (blockAndTintGetter.getBlockEntity(blockPos) instanceof PixelBlockEntity pixel) {
                return (pixel.getColor()[0] << 16) | (pixel.getColor()[1] << 8) | pixel.getColor()[2];
            }
            return 0x000000;
        }), ModBlocks.PIXEL_BLOCK);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOT_PEPPER_CROP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLEAM_SHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(sapling(DREADWOOD).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(sapling(EBON).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(trapdoor(EBON).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIMENSION_GATEWAY.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUBBLE_BLOCK.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.UNDERWATER_REDSTONE_WIRE.get(), RenderType.cutout());

        BlockEntityRenderers.register(ModBlockEntityTypes.DIMENSION_GATEWAY_ENTITY_TYPE.get(), DimensionGatewayBlockEntityRenderer::new);

        MenuScreens.register(VOID_BUNDLE_MENU_TYPE.get(), VoidBundleScreen::new);
        MenuScreens.register(WORKSHOP_MENU.get(), WorkshopScreen::new);
        MenuScreens.register(ACCESSORY_MENU.get(), AccessoryScreen::new);
    }
}
