package com.bmod.util;

import com.bmod.packet.S2C.S2CSyncAccessories;
import com.bmod.registry.item.custom.AccessoryItem;
import com.bmod.util.world_util.DataUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;

public class ContainerUtils {

    private static Container clientSavedAccessories = new SimpleContainer(5);

    public static void saveContainerToPlayer(Container container, Player player, String key) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return;
        }

        DataUtils modData = DataUtils.getCustomWorldData(serverPlayer.getServer().getLevel(Level.OVERWORLD));
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

        CompoundTag compoundTag = new CompoundTag();
        compoundTag.put("items", itemList);

        modData.putTag(serverPlayer.getUUID(), key, compoundTag);
    }

    public static void loadContainerFromPlayer(Container container, Player player, String key) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            for (int i = 0; i < 5; i++) {
                container.setItem(i, clientSavedAccessories.getItem(i));
            }
            return;
        }

        DataUtils modData = DataUtils.getCustomWorldData(serverPlayer.getServer().getLevel(Level.OVERWORLD));
        ListTag itemList = modData.getPlayerTags(serverPlayer.getUUID()).getCompound(key).getList("items", 10);

        for (int i = 0; i < itemList.size(); i++) {
            CompoundTag itemTag = itemList.getCompound(i);
            int slot = itemTag.getInt("slot");
            ItemStack stack = ItemStack.of(itemTag);
            container.setItem(slot, stack);
        }
    }

    public static void saveAccessoriesToPlayer(Container container, Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) {
            clientSavedAccessories = container;
            return;
        }

        saveContainerToPlayer(container, player, "accessories");
        AccessoryItem.clearAttributes(serverPlayer);

        new S2CSyncAccessories(container).sendTo(serverPlayer);
    }

    public static boolean playerHasAccessory(Player player, Item... items)
    {
        SimpleContainer container = new SimpleContainer(5);
        ContainerUtils.loadContainerFromPlayer(container, player, "accessories");
        return container.hasAnyMatching((itemStack) -> Arrays.stream(items).anyMatch(itemStack::is));
    }
}