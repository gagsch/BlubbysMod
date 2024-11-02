package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.container.AccessoryMenu;
import com.bmod.registry.menu.container.VoidBundleMenu;
import com.bmod.registry.menu.container.EnrichmentTableMenu;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<VoidBundleMenu>> VOID_BUNDLE_MENU_TYPE = MENUS.register("enderchest", () -> MenuRegistry.of(VoidBundleMenu::new));
    public static final RegistrySupplier<MenuType<EnrichmentTableMenu>> ENRICHMENT_TABLE_MENU = MENUS.register("enrichment_table", () -> MenuRegistry.of(EnrichmentTableMenu::new));
    public static final RegistrySupplier<MenuType<AccessoryMenu>> ACCESSORY_MENU = MENUS.register("accessory_menu", () -> MenuRegistry.of(AccessoryMenu::new));
}
