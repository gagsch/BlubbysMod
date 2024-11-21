package com.bmod.packet.C2S;

import com.bmod.registry.menu.container.AccessoryMenu;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

public class SOpenAccessoryMenuHandler {
    public static void handle(ServerPlayer player) {
        MenuRegistry.openMenu(player, new MenuProvider() {
            @Override
            public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player playerEntity) {
                return new AccessoryMenu(id, playerInventory);
            }

            @Override
            public @NotNull Component getDisplayName() {
                return Component.literal("Accessories");
            }
        });
    }
}
