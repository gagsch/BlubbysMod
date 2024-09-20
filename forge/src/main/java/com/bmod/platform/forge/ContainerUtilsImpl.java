package com.bmod.platform.forge;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ContainerUtilsImpl {

    public static void saveContainerToPlayer(SimpleContainer container, Player player, String key) {
        CompoundTag playerData = player.getPersistentData();
        ListTag itemList = new ListTag();

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("Slot", i);
                stack.save(itemTag);
                itemList.add(itemTag);
            }
        }

        playerData.put(key, itemList);
    }

    public static void loadContainerFromPlayer(SimpleContainer container, Player player, String key) {
        CompoundTag playerData = player.getPersistentData();
        ListTag itemList = playerData.getList(key, Tag.TAG_COMPOUND);

        for (int i = 0; i < itemList.size(); i++) {
            CompoundTag itemTag = itemList.getCompound(i);
            int slot = itemTag.getInt("Slot");
            ItemStack stack = ItemStack.of(itemTag);
            container.setItem(slot, stack);
        }
    }
}