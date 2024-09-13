package me.blubby.bmod.utils;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.armor.ModArmorItem;
import me.blubby.bmod.common.item.ModCreativeTab;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.common.tier.ModItemTier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemUtils {
    public static Item getItemFromId(String itemName) {
        ResourceLocation itemId = new ResourceLocation(Blubby_sModOfDoom.MOD_ID, itemName);
        return ForgeRegistries.ITEMS.getValue(itemId);
    }

    public static RegistryObject<Item> essence(String name) {
        return ModItems.ITEMS.register("essence_"+name, () -> new Item(new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(-1).stacksTo(64)));
    }

    public static RegistryObject<Item> soul(String name) {
        return ModItems.ITEMS.register("soul_"+name, () -> new Item(new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(-1).stacksTo(64)));
    }

    public static RegistryObject<Item> sword(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_sword",
                () -> new SwordItem(type, damage, attackSpeed - 2.4F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static RegistryObject<Item> pickaxe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_pickaxe",
                () -> new PickaxeItem(type, damage, attackSpeed - 2.8F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static RegistryObject<Item> axe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_axe",
                () -> new AxeItem(type, damage, attackSpeed - 3.2f, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static RegistryObject<Item> shovel(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_shovel",
                () -> new ShovelItem(type, damage, attackSpeed - 3.0F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static RegistryObject<Item> hoe(ModItemTier type, int damage, float attackSpeed) {
        return ModItems.ITEMS.register(type.getName() + "_hoe",
                () -> new HoeItem(type, damage, attackSpeed - 3.0F, new Item.Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .stacksTo(1)
                        .durability(type.getUses())));
    }

    public static RegistryObject<Item> armor(ArmorMaterial type, EquipmentSlot slot) {
        String slotName = "none";

        slotName = slot == EquipmentSlot.HEAD ? "_helmet" : slotName;
        slotName = slot == EquipmentSlot.CHEST ? "_chestplate" : slotName;
        slotName = slot == EquipmentSlot.LEGS ? "_leggings" : slotName;
        slotName = slot == EquipmentSlot.FEET ? "_boots" : slotName;

        return ModItems.ITEMS.register(type.getName() + slotName,
                () -> new ModArmorItem(type, slot, new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(type.getDurabilityForSlot(slot))));
    }
}
