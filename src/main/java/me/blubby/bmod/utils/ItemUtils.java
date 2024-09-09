package me.blubby.bmod.utils;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.armor.ModArmorItem;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemUtils {
    public static Item getItemFromId(String itemName) {
        ResourceLocation itemId = new ResourceLocation(Blubby_sModOfDoom.MOD_ID, itemName);
        return ForgeRegistries.ITEMS.getValue(itemId);
    }

    public static RegistryObject<Item> essence(String name) {
        return ModItems.ITEMS.register("essence_"+name, () -> new Item(ModItems._misc));
    }

    public static RegistryObject<Item> soul(String name) {
        return ModItems.ITEMS.register("soul_"+name, () -> new Item(ModItems._misc));
    }

    public static RegistryObject<Item> equipment(ArmorMaterial type, EquipmentSlot slot) {
        String slotName = "none";

        slotName = slot == EquipmentSlot.HEAD ? "_helmet" : slotName;
        slotName = slot == EquipmentSlot.CHEST ? "_chestplate" : slotName;
        slotName = slot == EquipmentSlot.LEGS ? "_leggings" : slotName;
        slotName = slot == EquipmentSlot.FEET ? "_boots" : slotName;

        return ModItems.ITEMS.register(type.getName() + slotName,
                () -> new ModArmorItem(type, slot, ModItems._combat.durability(type.getDurabilityForSlot(slot))));
    }
}
