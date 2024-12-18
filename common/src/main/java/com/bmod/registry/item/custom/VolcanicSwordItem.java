package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.item.tier.ModItemTier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

public class VolcanicSwordItem extends SwordItem {
    public VolcanicSwordItem(int damage, float speed) {
        this(new Properties().tab(ModCreativeTab.BLUBBYS_MOD)
                        .durability(ModItemTier.VOLCANIC.getUses())
                        .fireResistant(),
                damage, speed);
    }

    public VolcanicSwordItem(Properties properties, int damage, float speed) {
        super(ModItemTier.VOLCANIC, damage, speed - 2.4f, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        target.setSecondsOnFire(3);
        return super.hurtEnemy(itemStack, target, attacker);
    }
}
