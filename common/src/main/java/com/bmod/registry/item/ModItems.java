package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.item.custom.*;
import com.bmod.registry.item.tier.ModArmorMaterial;
import com.bmod.registry.item.tier.ModItemTier;
import com.bmod.util.ItemUtils;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

import static com.bmod.registry.item.custom.ToolTipItem.*;
import static com.bmod.registry.item.custom.InventoryItem.*;

public class ModItems  {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ITEM_REGISTRY);
    
    public static final Supplier<Item>
    BLUBBY_COIN = ITEMS.register("blubby_coin",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            () -> new ChronosClockItem(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ENDER_BUNDLE = ITEMS.register("ender_bundle",
            () -> new EnderBundleItem(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1))),

    LUCKY_ROCK = ITEMS.register("lucky_rock",
            () -> new InventoryItem(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1), inventoryItems.LuckyRock, ToolTips.lucky_rock)),

    TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
            () -> new ToolTipItem(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1), ToolTips.totem_of_dreams)),

    BUBBLE_WAND = ITEMS.register("bubble_wand",
            BubbleWandItem::new),

    HOT_PEPPER = ITEMS.register("hot_pepper",
            () -> new HotPepperItem(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HOT_PEPPER_CROP.get(), new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ROTTEN_APPLE = ITEMS.register("rotten_apple",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationMod(0F)
                            .effect(new MobEffectInstance(MobEffects.HUNGER, 1200, 1), 1F)
                            .effect(new MobEffectInstance(MobEffects.POISON, 1200, 1), 1F)
                            .alwaysEat()
                            .build()))),

    // Materials
    WARDEN_CORE = ITEMS.register("warden_core",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    GUARDIAN_CORE = ITEMS.register("guardian_core",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SOUL_DUST = ITEMS.register("soul_powder",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ECHOING_SOUL_DUST = ITEMS.register("echoing_soul_dust",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SOUL_FRAGMENT = ITEMS.register("soul_fragment",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    VILE_BLOOD = ITEMS.register("vile_blood",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    LEATHER_SCRAP = ITEMS.register("leather_scrap",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Scarlite Stuff
    SCARLITE_CHUNK = ITEMS.register("scarlite_chunk",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SCARLITE_INGOT = ITEMS.register("scarlite_ingot",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Divine Stuff
    DIVINE_ALLOY = ITEMS.register("divine_alloy",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Necrium Stuff
    NECRIUM_CHUNK = ITEMS.register("necrium_chunk",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    NECRIUM_INGOT = ITEMS.register("necrium_ingot",
            () -> new Item(new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Armor
    DIVINE_SWORD = ItemUtils.sword(ModItemTier.DIVINE, 3, 0f),
            DIVINE_PICKAXE = ItemUtils.pickaxe(ModItemTier.DIVINE, -1, 0),
            DIVINE_AXE = ItemUtils.axe(ModItemTier.DIVINE, 5, 0f),
            DIVINE_SHOVEL = ItemUtils.shovel(ModItemTier.DIVINE, 0, 0),
            DIVINE_HOE = ItemUtils.hoe(ModItemTier.DIVINE, -5, 0),
            DIVINE_HELMET = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.HEAD),
            DIVINE_CHESTPLATE = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.CHEST),
            DIVINE_LEGGINGS = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.LEGS),
            DIVINE_BOOTS = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.FEET),

    SCARLITE_SWORD = ItemUtils.sword(ModItemTier.SCARLITE, 6, -0.4f),
            SCARLITE_PICKAXE = ItemUtils.pickaxe(ModItemTier.SCARLITE, -1, 0),
            SCARLITE_AXE = ItemUtils.axe(ModItemTier.SCARLITE, 13, -0.2f),
            SCARLITE_SHOVEL = ItemUtils.shovel(ModItemTier.SCARLITE, 0, 0),
            SCARLITE_HOE = ItemUtils.hoe(ModItemTier.SCARLITE, -9, 0),
            SCARLITE_HELMET = ItemUtils.armor(ModArmorMaterial.SCARLITE, EquipmentSlot.HEAD),
            SCARLITE_CHESTPLATE = ItemUtils.armor(ModArmorMaterial.SCARLITE, EquipmentSlot.CHEST),
            SCARLITE_LEGGINGS = ItemUtils.armor(ModArmorMaterial.SCARLITE, EquipmentSlot.LEGS),
            SCARLITE_BOOTS = ItemUtils.armor(ModArmorMaterial.SCARLITE, EquipmentSlot.FEET),

    // Essences and Souls
    ESSENCE_BLESSED = ItemUtils.essence("blessed"),
            ESSENCE_DARKNESS = ItemUtils.essence("darkness"),
            ESSENCE_DAY = ItemUtils.essence("day"),
            ESSENCE_DEATH = ItemUtils.essence("death"),
            ESSENCE_END = ItemUtils.essence("end"),
            ESSENCE_OVERWORLD = ItemUtils.essence("earth"),
            ESSENCE_ENERGY = ItemUtils.essence("energy"),
            ESSENCE_FLAMES = ItemUtils.essence("flames"),
            ESSENCE_HAVOC = ItemUtils.essence("havoc"),
            ESSENCE_INFINITY = ItemUtils.essence("infinity"),
            ESSENCE_LIFE = ItemUtils.essence("life"),
            ESSENCE_LIGHT = ItemUtils.essence("light"),
            ESSENCE_NIGHTMARE_REALM = ItemUtils.essence("nightmare_realm"),
            ESSENCE_NETHER = ItemUtils.essence("nether"),
            ESSENCE_NIGHT = ItemUtils.essence("night"),
            ESSENCE_PLANETS = ItemUtils.essence("planets"),
            ESSENCE_SEA = ItemUtils.essence("sea"),
            ESSENCE_STARS = ItemUtils.essence("stars"),
            ESSENCE_STONE = ItemUtils.essence("stone"),
            ESSENCE_TUNDRA = ItemUtils.essence("tundra"),
            ESSENCE_VOID = ItemUtils.essence("void"),
            SOUL_BALANCE = ItemUtils.soul("balance"),
            SOUL_DIMENSIONS = ItemUtils.soul("dimensions"),
            SOUL_ELEMENTS = ItemUtils.soul("elements"),
            SOUL_SPACE = ItemUtils.soul("space"),
            SOUL_TIME = ItemUtils.soul("time"),
            SOUL_INFINITY = ItemUtils.soul("infinity"),

    // Spawn Eggs
    ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntities.ROT_FLY.get(), 0x382946, 0xa41717, new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    BEHEMOTH_SPAWN_EGG = ITEMS.register("behemoth_spawn_egg",
            () -> new SpawnEggItem(ModEntities.BEHEMOTH.get(), 0x442266, 0x111133, new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SNOW_FLINX_SPAWN_EGG = ITEMS.register("snow_flinx_spawn_egg",
            () -> new SpawnEggItem(ModEntities.SNOW_FLINX.get(), 0xddeeee, 0xfad2ac, new Item.Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
}