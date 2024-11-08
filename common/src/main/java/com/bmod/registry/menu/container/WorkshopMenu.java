package com.bmod.registry.menu.container;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.menu.ModMenus;
import com.bmod.registry.recipe.WorkshopRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class WorkshopMenu extends AbstractContainerMenu {

    public static final int BLUEPRINT_SLOT = 0; // What would be the blueprint, Example: Accessory Blueprint
    public static final int BASE_SLOT = 1; // What will be the base, Example: Ring
    public static final int PRIMARY_ADDITION_SLOT = 2; // What will be the main addition, Example: Netherite Ingot
    public static final int SECONDARY_ADDITION_SLOT = 3; // What will be the second addition, Example: Lava Bucket
    public static final int TERNARY_ADDITION_SLOT = 4; // What will be the third addition, Example: Ruby
    public static final int OUTPUT_SLOT = 5; // Slot for the item crafted, Example: Lava Ring (Will be used as an example for other comments)

    protected final ResultContainer resultSlots = new ResultContainer();
    private final SimpleContainer craftSlots;
    private final ContainerLevelAccess access;
    private final Player player;

    public WorkshopMenu(int windowId, Inventory inventory) {
        this(windowId, inventory, ContainerLevelAccess.NULL);
    }

    public WorkshopMenu(int windowId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenus.WORKSHOP_MENU.get(), windowId);

        this.player = inventory.player;
        this.access = access;
        this.craftSlots = new SimpleContainer(5) {
            public void setChanged() {
                super.setChanged();
                WorkshopMenu.this.slotsChanged(this);
            }
        };

        int j, k;

        this.addSlot(new Slot(resultSlots,0, 80,48) {
            public boolean mayPlace(ItemStack itemStack) {
                return false;
            }

            public boolean mayPickup(Player player) {
                return WorkshopMenu.this.mayPickup();
            }

            public void onTake(Player player, ItemStack itemStack) {
                WorkshopMenu.this.onTake(itemStack);
            }
        });

        this.addSlot(new Slot(craftSlots, 0, 44, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(ModItems.BLUEPRINT.get());
            }
        });
        for(j = 1; j < 5; ++j) {
            this.addSlot(new Slot(craftSlots, j, 44 + j * 18, 18));
        }

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

    public void createResult(Level level, SimpleContainer simpleContainer, ResultContainer resultContainer, Player player) {
        if (player instanceof ServerPlayer) {
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<WorkshopRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(WorkshopRecipe.Type.INSTANCE, simpleContainer, level);

            if (optional.isPresent()) {
                WorkshopRecipe workshopRecipe = optional.get();
                itemStack = workshopRecipe.assemble(simpleContainer);
            }

            resultContainer.setItem(0, itemStack);
            this.setRemoteSlot(OUTPUT_SLOT, itemStack);
        }
    }

    public void onTake(ItemStack itemStack) {

        itemStack.onCraftedBy(this.player.level, this.player, itemStack.getCount());
        this.resultSlots.awardUsedRecipes(this.player);

        for (int i = 1; i < 5; i++) {
            ItemStack slotStack = this.craftSlots.getItem(i);
            if (!slotStack.isEmpty()) {
                slotStack.shrink(1);
                this.craftSlots.setItem(i, slotStack.isEmpty() ? ItemStack.EMPTY : slotStack);
            }
        }

        this.createResult(this.player.level, this.craftSlots, this.resultSlots, this.player);
    }

    protected boolean mayPickup() {
        return resultSlots.getItem(0) != ItemStack.EMPTY && this.player instanceof ServerPlayer;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemStack = stackInSlot.copy();

            int craftSlotsContainerSize = this.craftSlots.getContainerSize() + 1;
            int inventoryEndIndex = craftSlotsContainerSize + 36;

            if (index == craftSlotsContainerSize) {
                if (!this.moveItemStackTo(stackInSlot, craftSlotsContainerSize, inventoryEndIndex, true)) {
                    return ItemStack.EMPTY;
                }

                WorkshopMenu.this.onTake(stackInSlot);

            } else if (index < craftSlotsContainerSize) {
                if (!this.moveItemStackTo(stackInSlot, craftSlotsContainerSize, inventoryEndIndex, true)) {
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

            this.createResult(this.player.level, this.craftSlots, this.resultSlots, this.player);
        }

        return itemStack;
    }


    @Override
    public void slotsChanged(Container container) {
        this.access.execute((level, blockPos) -> slotChangedCraftingGrid(level, this.craftSlots, this.resultSlots));
    }

    public void slotChangedCraftingGrid(Level level, SimpleContainer craftSlots, ResultContainer resultSlots)
    {
        if (!level.isClientSide) {
            if (craftSlots == this.craftSlots) {
                this.createResult(level, craftSlots, resultSlots, this.player);
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, this.player, ModBlocks.WORKSHOP.get());
    }

    @Override
    public void removed(Player player) {
        super.removed(this.player);
        this.access.execute((level, blockPos) -> {
            this.clearContainer(this.player, this.craftSlots);
        });
    }
}