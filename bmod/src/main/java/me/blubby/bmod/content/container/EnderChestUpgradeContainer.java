package me.blubby.bmod.content.container;

import me.blubby.bmod.events.ModEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnderChestUpgradeContainer extends AbstractContainerMenu {
    private final SimpleContainer loot;
    private final Player player;

    public EnderChestUpgradeContainer(int windowId, Inventory playerInventory, SimpleContainer loot) {
        super(ModEvents.ENDER_CHEST_UPGRADE_MENU.get(), windowId);
        this.loot = loot;
        this.player = playerInventory.player;

        ContainerUtils.loadContainerFromPlayer(loot, this.player, "EnderChestUpgradeLoot");

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

    public EnderChestUpgradeContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new SecondEnderChestContainer(54));
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemStack = stackInSlot.copy();

            int lootContainerSize = this.loot.getContainerSize(); // This should be 54
            int playerInventoryStart = lootContainerSize; // This should be 54
            int playerInventoryEnd = playerInventoryStart + 36; // Player inventory + hotbar (27+9 = 36)

            if (index < lootContainerSize) { // If the slot is within the loot container
                if (!this.moveItemStackTo(stackInSlot, playerInventoryStart, playerInventoryEnd, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= playerInventoryStart && index < playerInventoryEnd) { // If the slot is within the player inventory
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
    public void removed(Player player) {
        super.removed(player);

        ContainerUtils.saveContainerToPlayer(this.loot, player, "EnderChestUpgradeLoot");
    }
}
