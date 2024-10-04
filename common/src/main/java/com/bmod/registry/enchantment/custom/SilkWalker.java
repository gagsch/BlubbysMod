package com.bmod.registry.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SilkWalker extends Enchantment {
    public SilkWalker(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Override
    public int getMinCost(int level) {
        return 10 + (level - 1) * 7;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ArmorItem armorItem)
        {
            return armorItem.getSlot() == EquipmentSlot.FEET;
        }
        return false;
    }
}
