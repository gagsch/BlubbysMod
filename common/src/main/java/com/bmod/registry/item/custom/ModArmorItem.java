package com.bmod.registry.item.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(ArmorMaterial material, EquipmentSlot type, Properties properties) {
        super(material, type, properties);
    }

    /*@Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot) {
        throw new AssertionError();
    }*/
}
