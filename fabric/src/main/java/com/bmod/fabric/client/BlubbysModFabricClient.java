package com.bmod.fabric.client;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.entity.client.NightmareGatewayBlockEntityRenderer;
import com.bmod.registry.menu.EnrichmentTableScreen;
import com.bmod.registry.menu.VoidBundleScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import static com.bmod.registry.menu.ModMenus.ENDER_CHEST_UPGRADE_MENU;
import static com.bmod.registry.menu.ModMenus.ENRICHMENT_TABLE_MENU;
import static com.bmod.util.WoodUtils.*;

public final class BlubbysModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLEAM_SHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(sapling(DREADWOOD).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(sapling(EBON).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(trapdoor(EBON).get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NIGHTMARE_GATEWAY.get(), RenderType.translucent());

        BlockEntityRenderers.register(ModBlockEntityTypes.NIGHTMARE_GATEWAY_ENTITY_TYPE.get(), NightmareGatewayBlockEntityRenderer::new);

        MenuScreens.register(ENDER_CHEST_UPGRADE_MENU.get(), VoidBundleScreen::new);
        MenuScreens.register(ENRICHMENT_TABLE_MENU.get(), EnrichmentTableScreen::new);
    }
}
