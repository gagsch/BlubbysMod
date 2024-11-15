package com.bmod.registry.item.tier;

import com.bmod.registry.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public enum ModItemTier implements Tier {
    DIVINE("divine", 4, 1777, 9.0f, 3.5f, 25, () -> {
        return Ingredient.of(ModItems.DIVINE_ALLOY.get());
    }),
    SHROOMITE("shroomite", 6, 3666, 15.0f, 6.0f, 16, () -> {
        return Ingredient.of(ModItems.SHROOMITE_INGOT.get());
    }),
    VOLCANIC("volcanic", 6, 2435, 12.5f, 12.0f, 10, () -> {
        return Ingredient.of(ModItems.VOLCANIC_INGOT.get());
    });

    private final String name;
    private final Supplier<Ingredient> repairIngredient;
    private final int enchantmentValue;
    private final float attackDamage;
    private final float speed;
    private final int uses;
    private final int level;

    ModItemTier(String name, int level, int uses, float speed, float attackDamage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}