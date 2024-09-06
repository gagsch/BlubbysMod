package me.blubby.bmod.utils;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemUtils {
    public static Item getItemFromId(String itemName) {
        ResourceLocation itemId = new ResourceLocation(Blubby_sModOfDoom.MOD_ID, itemName);
        return ForgeRegistries.ITEMS.getValue(itemId);
    }
}
