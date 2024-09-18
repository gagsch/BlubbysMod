package me.blubby.bmod.common.tier;

import me.blubby.bmod.common.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public enum ModItemTier implements Tier {
    DIVINE("divine", 5, 2777, 10.0F, 6.0F, 25, () -> Ingredient.of(ModItems.DIVINE_ALLOY.get())),
    SCARLITE("scarlite", 7, 5000, 20f, 9.0F, 40, () -> Ingredient.of(ModItems.SCARLITE_INGOT.get()));

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