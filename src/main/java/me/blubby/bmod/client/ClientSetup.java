package me.blubby.bmod.client;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.client.layers.EndPortalArmorLayer;
import me.blubby.bmod.client.layers.EndPortalDimensionalPortalLayer;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.client.*;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        event.getSkins().forEach(skin -> {
            PlayerRenderer renderer = event.getSkin(skin);
            if (renderer != null) {
                renderer.addLayer(new EndPortalArmorLayer<>(renderer));
            }
        });

        EntityType<? extends DimensionTeleporterEntity> DimensionTeleporterEntity = ModEntities.DIMENSION_TELEPORTER.get();
        DimensionTeleporterRenderer renderer = event.getRenderer(DimensionTeleporterEntity);
        renderer.addLayer(new EndPortalDimensionalPortalLayer<>(renderer));
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.DIMENSION_TELEPORTER_LAYER, DimensionTeleporterModel::createBodyLayer);
    }
}