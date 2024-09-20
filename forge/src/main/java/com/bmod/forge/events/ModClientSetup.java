package com.bmod.forge.events;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.entity.client.*;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BlubbysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup {
    public static void ModClientSetup(IEventBus modEventBus) {
        modEventBus.addListener(ModClientSetup::onClientSetup);
        modEventBus.addListener(RenderOverlayEvent::onRenderGui);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.ROT_FLY.get(), RotFlyRenderer::new);
        EntityRenderers.register(ModEntities.BEHEMOTH.get(), BehemothRenderer::new);
        EntityRenderers.register(ModEntities.SNOW_FLINX.get(), SnowFlinxRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
    }
}