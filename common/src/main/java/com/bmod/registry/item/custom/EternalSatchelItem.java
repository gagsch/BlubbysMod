package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class EternalSatchelItem extends ToolTipItem implements IAccessoryItem {

    public EternalSatchelItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {

    }
}
