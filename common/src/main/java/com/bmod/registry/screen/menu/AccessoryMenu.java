package com.bmod.registry.screen.menu;

import com.bmod.registry.item.custom.AccessoryItem;
import com.bmod.util.ContainerUtils;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AccessoryMenu extends AbstractContainerMenu {
    private final Container accessoryMenu;

    public AccessoryMenu(int i, Inventory inventory) {
        this(i, inventory, new SimpleContainer(5));
    }

    public AccessoryMenu(int i, Inventory inventory, Container container) {
        super(MenuType.HOPPER, i);
        this.accessoryMenu = container;

        ContainerUtils.loadContainerFromPlayer(accessoryMenu, inventory.player, "accessories");

        checkContainerSize(container, 5);
        accessoryMenu.startOpen(inventory.player);

        int k;
        for(k = 0; k < 5; ++k) {
            this.addSlot(new Slot(accessoryMenu, k, 44 + k * 18, 20) {
                @Override
                public boolean mayPlace(ItemStack itemStack) {
                    return itemStack.getItem() instanceof AccessoryItem && accessoryMenu.countItem(itemStack.getItem()) == 0;
                }
            });
        }

        for(k = 0; k < 3; ++k) {
            for(int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + k * 9 + 9, 8 + l * 18, k * 18 + 51));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 109));
        }

    }

    public boolean stillValid(Player player) {
        return this.accessoryMenu.stillValid(player);
    }

    public @NotNull ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (i < this.accessoryMenu.getContainerSize()) {
                if (!this.moveItemStackTo(itemStack2, this.accessoryMenu.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack2, 0, this.accessoryMenu.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.accessoryMenu.stopOpen(player);
        ContainerUtils.saveAccessoriesToPlayer(this.accessoryMenu, player);
    }
}
