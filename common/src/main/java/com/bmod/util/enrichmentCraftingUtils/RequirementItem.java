package com.bmod.util.enrichmentCraftingUtils;

import net.minecraft.world.item.Item;

public class RequirementItem {
    Item item;

    public RequirementItem(Item item)
    {
        this.item = item;
    }

    public Item getRequirement()
    {
        return item;
    }
}
