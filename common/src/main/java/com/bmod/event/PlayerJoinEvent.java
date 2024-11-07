package com.bmod.event;

import com.bmod.registry.item.ModItems;
import com.bmod.util.worlddata.ModData;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class PlayerJoinEvent {
    public static void initialize()
    {
        PlayerEvent.PLAYER_JOIN.register((player) -> {
            ModData modData = ModData.getCustomWorldData(Objects.requireNonNull(Objects.requireNonNull(player.getServer()).getLevel(Level.OVERWORLD)));

            if (!modData.getPlayerTags(player.getUUID()).contains("hasJoined"))
            {
                modData.putBoolean(player.getUUID(), "hasJoined", true);
                player.addItem(new ItemStack(ModItems.ANCIENT_GUIDE_BOOK.get()));
            }
        });
    }
}
