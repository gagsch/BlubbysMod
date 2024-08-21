package me.blubby.bmod.common.armor;

import me.blubby.bmod.Blubby_sModOfDoom;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class ArmorItem extends net.minecraft.world.item.ArmorItem {
    public ArmorItem(ArmorMaterial material, EquipmentSlot type, Properties properties) {
        super(material, type, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format("%s:textures/models/armor/%s_layer_%s.png", Blubby_sModOfDoom.MOD_ID, this.getMaterial().getName(), slot == EquipmentSlot.LEGS ? 2 : 1);
    }
}
