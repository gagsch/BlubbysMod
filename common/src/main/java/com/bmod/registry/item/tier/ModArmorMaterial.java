package com.bmod.registry.item.tier;

import com.bmod.registry.item.ModItems;
import com.bmod.registry.ModSounds;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public enum ModArmorMaterial implements ArmorMaterial {
    DIVINE(
            "divine",
            70,
            new int[]{7, 7, 7, 7},
            25,
            ModSounds.EQUIP_MOD_ARMOR_1,
            3F,
            () -> Ingredient.of(ModItems.DIVINE_ALLOY.get())),
    SCARLITE(
            "scarlite",
            80,
            new int[]{8, 16, 18, 8},
            40,
            ModSounds.EQUIP_MOD_ARMOR_1,
            8F,
            () -> Ingredient.of(ModItems.SCARLITE_INGOT.get()));

    private final String name;
    private final int maxDamageFactor;
    private final int[] slotProtections;
    private final int enchantability;
    private final Supplier<SoundEvent> soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] slotProtections, int enchantability, Supplier<SoundEvent> soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.slotProtections = slotProtections;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
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
        return 0;
    }
}