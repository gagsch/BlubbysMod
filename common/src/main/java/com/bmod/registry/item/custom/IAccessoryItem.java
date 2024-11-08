package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item.Properties;

public interface IAccessoryItem {
    Properties DEFAULT_ACCESSORY_PROPERTIES = new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1);

    void accessoryTick(ServerLevel level, ServerPlayer player);
}
