package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.util.ContainerUtils;
import com.bmod.util.world_util.DataUtils;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class PlayerJoinEvent {
    public static void initialize()
    {
        PlayerEvent.PLAYER_JOIN.register((player) -> {

            if (player.getInventory().isEmpty())
            {
                player.addItem(new ItemStack(ModItems.ANCIENT_GUIDE_BOOK.get()));
            }

            Container container = new SimpleContainer(5);
            ContainerUtils.loadContainerFromPlayer(container, player, "accessories");
            ContainerUtils.saveAccessoriesToPlayer(container, player);
        });
    }
}
