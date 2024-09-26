package com.bmod.registry.menu.container;

import com.bmod.registry.menu.ModMenus;
import com.bmod.util.enrichmentCraftingUtils.EnrichmentCraftingUtils;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnrichmentTableContainer extends AbstractContainerMenu {

    private final SimpleContainer craftSlots;
    private ItemStack output;
    protected final ResultContainer resultSlots = new ResultContainer();

    public EnrichmentTableContainer(int windowId, Inventory inventory) {
        super(ModMenus.ENRICHMENT_TABLE_MENU.get(), windowId);
        this.craftSlots = new SimpleContainer(10) {
            public void setChanged() {
                super.setChanged();
                EnrichmentTableContainer.this.slotsChanged(this);
            }
        };

        int j;
        int k;
        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 3; ++k) {
                this.addSlot(new Slot(craftSlots, k + j * 3, 47 + k * 18, 17 + j * 18));
            }
        }

        this.addSlot(new Slot(craftSlots, 9, 19, 35));
        this.addSlot(new Slot(resultSlots,0, 141, 35) {
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            public boolean mayPickup(Player player) {
                return EnrichmentTableContainer.this.mayPickup();
            }

            public void onTake(Player player, ItemStack itemStack) {
                EnrichmentTableContainer.this.onTake(player, itemStack);
            }
        });

        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, j, 8 + j * 18, 142));
        }

        this.slotsChanged(craftSlots);
    }

    public void createResult() {
        List<Object> inputItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            inputItems.add(this.craftSlots.getItem(i).getItem());
        }

        this.output = EnrichmentCraftingUtils.getResult(inputItems);

        this.resultSlots.setItem(0, this.output);
    }

    public void onTake(Player player, ItemStack itemStack) {

        itemStack.onCraftedBy(player.level, player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(player);

        for (int i = 0; i < 9; i++) {
            ItemStack slotStack = this.craftSlots.getItem(i);
            if (!slotStack.isEmpty()) {
                slotStack.shrink(1);
                this.craftSlots.setItem(i, slotStack.isEmpty() ? ItemStack.EMPTY : slotStack);
            }
        }

        this.createResult();
    }

    protected boolean mayPickup(/*Player player, boolean hasItem*/) {
        return this.output != null;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemStack = stackInSlot.copy();

            int craftSlotsContainerSize = this.craftSlots.getContainerSize();
            int inventoryStartIndex = craftSlotsContainerSize + 1;
            int inventoryEndIndex = inventoryStartIndex + 36;

            if (index == craftSlotsContainerSize) {
                if (!this.moveItemStackTo(stackInSlot, inventoryStartIndex, inventoryEndIndex, true)) {
                    return ItemStack.EMPTY;
                }

                EnrichmentTableContainer.this.onTake(player, stackInSlot);

            } else if (index < craftSlotsContainerSize) {
                if (!this.moveItemStackTo(stackInSlot, inventoryStartIndex, inventoryEndIndex, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stackInSlot, 0, craftSlotsContainerSize, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            this.createResult();
        }

        return itemStack;
    }


    @Override
    public void slotsChanged(Container arg) {
        super.slotsChanged(arg);
        if (arg == this.craftSlots) {
            this.createResult();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, this.craftSlots);
        clearContainer(player, this.resultSlots);
    }
}
