package com.bmod.util;

import com.bmod.registry.item.ModItems;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ContainerUtils {

    public static void saveContainerToPlayer(Container container, Player player, String key) {
        CompoundTag playerData = ((IEntityDataSaver)player).blubbysmod$getPersistentData();

        ListTag itemList = new ListTag();

        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putInt("slot", i);
                stack.save(itemTag);
                itemList.add(itemTag);
            }
        }

        playerData.put(key, itemList);
    }

    public static void loadContainerFromPlayer(Container container, Player player, String key) {
        CompoundTag playerData = ((IEntityDataSaver)player).blubbysmod$getPersistentData();

        ListTag itemList = playerData.getList(key, Tag.TAG_COMPOUND);

        for (int i = 0; i < itemList.size(); i++) {
            CompoundTag itemTag = itemList.getCompound(i);
            int slot = itemTag.getInt("slot");
            ItemStack stack = ItemStack.of(itemTag);
            container.setItem(slot, stack);
        }
    }

    public static boolean playerAccessoriesHasItem(Player player, Item item)
    {
        SimpleContainer container = new SimpleContainer(5);
        ContainerUtils.loadContainerFromPlayer(container, player, "accessories");
        return container.hasAnyMatching((itemStack) -> itemStack.is(item));
    }
}