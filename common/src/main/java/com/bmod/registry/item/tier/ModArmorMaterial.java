package com.bmod.registry.item.tier;

import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public enum ModArmorMaterial implements ArmorMaterial {
    DIVINE("divine", 80, new int[]{3, 7, 7, 3}, 20, ModSounds.EQUIP_MOD_ARMOR_1, 2.5F, 0.05f, () -> {
        return Ingredient.of(ModItems.DIVINE_ALLOY.get());
    }),
    SHROOMITE("shroomite", 80, new int[]{5, 8, 7, 5}, 13, ModSounds.EQUIP_MOD_ARMOR_1, 4F, 0.1f, () -> {
        return Ingredient.of(ModItems.SHROOMITE_INGOT.get());
    });

    private final String name;
    private final int maxDamageFactor;
    private final int[] slotProtections;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] slotProtections, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.slotProtections = slotProtections;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {

        return slotProtections[slot.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent.get();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}