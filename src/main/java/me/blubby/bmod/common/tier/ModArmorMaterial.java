package me.blubby.bmod.common.tier;

import me.blubby.bmod.common.events.BlubbySoundEvent;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public enum ModArmorMaterial implements ArmorMaterial {
    NIGHTMARE("nightmare", 66 , new int[]{6, 13, 13, 6}, 30, BlubbySoundEvent.EQUIP_NIGHTMARE, 4F, () -> Ingredient.of(ModItems.NIGHTMARE_INGOT.get())),
    BLESSED("blessed", 70 , new int[]{7, 7, 7, 7}, 25, BlubbySoundEvent.EQUIP_NIGHTMARE, 3F, () -> Ingredient.of(ModItems.BLESSED_INGOT.get())),
    COSMILITE("cosmilite", 80 , new int[]{8, 16, 18, 8}, 40, BlubbySoundEvent.EQUIP_NIGHTMARE, 8F, () -> Ingredient.of(ModItems.COSMILITE_INGOT.get()));

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
    @OnlyIn(Dist.CLIENT)
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