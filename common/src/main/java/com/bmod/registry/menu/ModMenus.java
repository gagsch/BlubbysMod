package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.container.EnderChestUpgradeMenu;
import com.bmod.registry.menu.container.EnrichmentTableMenu;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<EnderChestUpgradeMenu>> ENDER_CHEST_UPGRADE_MENU = MENUS.register("enderchest", () -> MenuRegistry.of(EnderChestUpgradeMenu::new));
    public static final RegistrySupplier<MenuType<EnrichmentTableMenu>> ENRICHMENT_TABLE_MENU = MENUS.register("enrichment_table", () -> MenuRegistry.of(EnrichmentTableMenu::new));
}
