package me.blubby.bmod.common.events;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.client.RotFlyRenderer;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import me.blubby.bmod.datagen.DataGenerators;
import me.blubby.bmod.server.container.EnderChestUpgradeContainer;
import me.blubby.bmod.client.gui.EnderChestUpgradeScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Blubby_sModOfDoom.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<MenuType<EnderChestUpgradeContainer>> ENDER_CHEST_UPGRADE_MENU = MENU_TYPES.register("enderchest", () ->
            IForgeMenuType.create((windowId, inv, data) -> new EnderChestUpgradeContainer(windowId, inv))
    );

    public static void onCommonSetup(FMLCommonSetupEvent event) {
    }

    // Client setup method
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ENDER_CHEST_UPGRADE_MENU.get(), EnderChestUpgradeScreen::new);
        EntityRenderers.register(ModEntities.ROT_FLY.get(), RotFlyRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ROT_FLY.get(), RotFlyEntity.createAttributes().build());
    }

    public static void registerEventHandlers(IEventBus modEventBus) {
        MENU_TYPES.register(modEventBus);
        modEventBus.addListener(ModEvents::onCommonSetup);
        modEventBus.addListener(ModEvents::onClientSetup);
        modEventBus.addListener(ClientSetup::onAddLayers);
        modEventBus.addListener(DataGenerators::gatherData);
    }


    private static<T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> menu) {
        return MENU_TYPES.register(name, () -> new MenuType<>(menu));
    }
}