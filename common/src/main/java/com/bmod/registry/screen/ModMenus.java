package com.bmod.registry.screen;

import com.bmod.BlubbysMod;
import com.bmod.registry.screen.menu.AccessoryMenu;
import com.bmod.registry.screen.menu.VoidBundleMenu;
import com.bmod.registry.screen.menu.WorkshopMenu;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<VoidBundleMenu>> VOID_BUNDLE_MENU_TYPE = MENUS.register("enderchest", () -> MenuRegistry.of(VoidBundleMenu::new));
    public static final RegistrySupplier<MenuType<WorkshopMenu>> WORKSHOP_MENU = MENUS.register("enrichment_table", () -> MenuRegistry.of(WorkshopMenu::new));
    public static final RegistrySupplier<MenuType<AccessoryMenu>> ACCESSORY_MENU = MENUS.register("accessory_menu", () -> MenuRegistry.of(AccessoryMenu::new));
}
