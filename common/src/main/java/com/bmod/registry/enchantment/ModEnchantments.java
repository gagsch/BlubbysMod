package com.bmod.registry.enchantment;

import com.bmod.BlubbysMod;
import com.bmod.registry.enchantment.custom.SilkWalker;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Supplier;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ENCHANTMENT_REGISTRY);

    public static final  Supplier<Enchantment> WEB_WALKER = ENCHANTMENTS.register("web_walker",
            () -> new SilkWalker(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET}));
}
