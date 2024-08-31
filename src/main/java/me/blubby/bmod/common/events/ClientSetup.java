package me.blubby.bmod.common.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.armor.EndPortalArmorLayer;
import me.blubby.bmod.common.entity.client.BehemothModel;
import me.blubby.bmod.common.entity.client.ModModelLayers;
import me.blubby.bmod.common.entity.client.RotFlyModel;
import me.blubby.bmod.common.entity.client.SnowFlinxModel;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        event.getSkins().forEach(skin -> {
            PlayerRenderer renderer = event.getSkin(skin);
            if (renderer != null) {
                renderer.addLayer(new EndPortalArmorLayer<>(renderer));
            }
        });
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ROT_FLY_LAYER, RotFlyModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.BEHEMOTH_LAYER, BehemothModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SNOW_FLINX_LAYER, SnowFlinxModel::createBodyLayer);
    }
}