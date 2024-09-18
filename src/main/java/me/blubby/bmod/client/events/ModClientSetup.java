package me.blubby.bmod.client.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.client.gui.EnderChestUpgradeScreen;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.client.*;
import me.blubby.bmod.common.container.EnderChestUpgradeContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientSetup {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<MenuType<EnderChestUpgradeContainer>> ENDER_CHEST_UPGRADE_MENU = MENU_TYPES.register("enderchest", () ->
            IForgeMenuType.create((windowId, inv, data) -> new EnderChestUpgradeContainer(windowId, inv))
    );

    public static void ModClientSetup(IEventBus modEventBus) {
        modEventBus.addListener(ModClientSetup::onClientSetup);
        modEventBus.addListener(RenderOverlayEvent::onRenderGui);
        ModClientSetup.MENU_TYPES.register(modEventBus);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ENDER_CHEST_UPGRADE_MENU.get(), EnderChestUpgradeScreen::new);

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

    private static<T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> menu) {
        return MENU_TYPES.register(name, () -> new MenuType<>(menu));
    }
}