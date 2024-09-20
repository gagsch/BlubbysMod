package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.container.EnderChestUpgradeContainer;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<EnderChestUpgradeContainer>> ENDER_CHEST_UPGRADE_MENU = MENUS.register("enderchest", () -> MenuRegistry.of(EnderChestUpgradeContainer::new));
}
