package com.bmod.util;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.item.tier.ModItemTier;
import com.bmod.registry.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;

import java.util.Objects;
import java.util.function.Supplier;

public class ItemUtils {
    public static Item getItemFromId(String itemId) {
        String[] stuff = itemId.split(":");

        if (stuff.length == 2)
        {
            return Registry.ITEM.get(new ResourceLocation(stuff[0], stuff[1]));
        }
        return Registry.ITEM.get(new ResourceLocation(BlubbysMod.MOD_ID, itemId));
    }

    public static Item getItemFromId(String itemId, String ID) {
        return Objects.equals(ID, "") ? Registry.ITEM.get(new ResourceLocation(itemId)) : Registry.ITEM.get(new ResourceLocation(ID, itemId));
    }

    public static Supplier<Item> essence(String name) {
        return ModItems.ITEMS.register("essence_"+name, () -> new Item(new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(-1).stacksTo(64)));
    }

    public static Supplier<Item> soul(String name) {
        return ModItems.ITEMS.register("soul_"+name, () -> new Item(new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(-1).stacksTo(1)));
    }

    public static Supplier<Item> sword(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_sword",
                () -> new SwordItem(type, damage, attackSpeed - 2.4F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static Supplier<Item> pickaxe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_pickaxe",
                () -> new PickaxeItem(type, damage, attackSpeed - 2.8F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static Supplier<Item> axe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_axe",
                () -> new AxeItem(type, damage, attackSpeed - 3.2f, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static Supplier<Item> shovel(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_shovel",
                () -> new ShovelItem(type, damage, attackSpeed - 3.0F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static Supplier<Item> hoe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_hoe",
                () -> new HoeItem(type, damage, attackSpeed - 3.0F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static Supplier<Item> armor(ArmorMaterial type, EquipmentSlot slot) {
        String slotName = "none";

        slotName = slot == EquipmentSlot.HEAD ? "_helmet" : slotName;
        slotName = slot == EquipmentSlot.CHEST ? "_chestplate" : slotName;
        slotName = slot == EquipmentSlot.LEGS ? "_leggings" : slotName;
        slotName = slot == EquipmentSlot.FEET ? "_boots" : slotName;

        return ModItems.ITEMS.register(type.getName() + slotName,
                () -> new ArmorItem(type, slot, new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(type.getDurabilityForSlot(slot))));
    }
}
