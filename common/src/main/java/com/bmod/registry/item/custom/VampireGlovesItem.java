package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class VampireGlovesItem extends ToolTipItem implements IAccessoryItem {
    public VampireGlovesItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {
    }
}
