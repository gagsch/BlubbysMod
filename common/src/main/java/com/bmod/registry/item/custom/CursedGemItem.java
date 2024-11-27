package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;

public class CursedGemItem extends ToolTipItem {
    public CursedGemItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_MOD)
                .durability(-1)
                .stacksTo(1));
    }
}
