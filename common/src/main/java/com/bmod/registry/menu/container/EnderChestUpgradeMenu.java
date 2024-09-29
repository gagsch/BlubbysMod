package com.bmod.registry.menu.container;

import com.bmod.util.ContainerUtils;
import com.bmod.registry.menu.ModMenus;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnderChestUpgradeMenu extends AbstractContainerMenu {

    /*
        I have literally no clue what I am looking at.
        Must have been on some type of drug making this
        and other container related stuff.

        New duplication methods may arise with this.
    */

    private final SimpleContainer loot;

    public EnderChestUpgradeMenu(int windowId, Inventory playerInventory, SimpleContainer loot) {
        super(ModMenus.ENDER_CHEST_UPGRADE_MENU.get(), windowId);
        this.loot = loot;

        ContainerUtils.loadContainerFromPlayer(loot, playerInventory.player, "EnderChestUpgradeLoot");

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (i < 3) {
                    this.addSlot(new Slot(playerInventory.player.getEnderChestInventory(), j + i * 9, 8 + j * 18, 18 + i * 18));
                }
                else {
                    this.addSlot(new Slot(loot, j + i * 9 - 27, 8 + j * 18, 18 + i * 18));
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 198));
        }
    }

    public EnderChestUpgradeMenu(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(54));
    }


    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemStack = stackInSlot.copy();

            int lootContainerSize = this.loot.getContainerSize();
            int playerInventoryEnd = lootContainerSize + 36;

            if (index < lootContainerSize) {
                if (!this.moveItemStackTo(stackInSlot, lootContainerSize, playerInventoryEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < playerInventoryEnd) {
                if (!this.moveItemStackTo(stackInSlot, 0, lootContainerSize, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);

        ContainerUtils.saveContainerToPlayer(this.loot, player, "EnderChestUpgradeLoot");
    }
}
