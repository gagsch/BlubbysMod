package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.custom.*;
import com.bmod.registry.item.tier.ModArmorMaterial;
import com.bmod.registry.item.tier.ModItemTier;
import com.bmod.util.ItemUtils;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
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
    
    public static final Supplier<Item> BLUBBY_COIN = ITEMS.register("blubby_coin",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ANCIENT_RECIPE_PAGE = ITEMS.register("ancient_recipe_page",
            AncientRecipePageItem::new),

    ANCIENT_RECIPE_BOOK = ITEMS.register("ancient_recipe_book",
            AncientRecipeBookItem::new),

    BUBBLE_WAND = ITEMS.register("bubble_wand",
            BubbleWandItem::new),

    CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            () -> new ChronosClockItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1))),

    CURSED_GEM = ITEMS.register("cursed_gem",
            CursedGemItem::new),

    ENDER_BUNDLE = ITEMS.register("ender_bundle",
            () -> new EnderBundleItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1))),

    HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HOT_PEPPER_CROP.get(), new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    LUCKY_ROCK = ITEMS.register("lucky_rock",
            () -> new InventoryItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1), inventoryItems.LUCKY_ROCK)),

    TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
            () -> new ToolTipItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1))),

    VOODOO_DOLL = ITEMS.register("cursed_doll",
            VoodooDollItem::new),

    // Food
    HOT_PEPPER = ITEMS.register("hot_pepper",
            () -> new HotPepperItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ROTTEN_APPLE = ITEMS.register("rotten_apple",
            () -> new Item(new Properties()
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
    WARDEN_TENDRIL = ITEMS.register("warden_tendril",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    GUARDIAN_CORE = ITEMS.register("guardian_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    WITHER_SPINE = ITEMS.register("wither_spine",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    DRAGON_HEART = ITEMS.register("dragon_heart",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SOUL_DUST = ITEMS.register("soul_powder",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SOUL_FRAGMENT = ITEMS.register("soul_fragment",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    RUBY = ITEMS.register("ruby",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SOUL_INFUSED_RUBY = ITEMS.register("soul_infused_ruby",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    IRON_CORE = ITEMS.register("iron_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    GOLDEN_CORE = ITEMS.register("golden_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    DIAMOND_CORE = ITEMS.register("diamond_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    DIVINE_CORE = ITEMS.register("divine_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    NETHERITE_CORE = ITEMS.register("netherite_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    DREADIUM_CORE = ITEMS.register("dreadium_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    VILE_BLOOD = ITEMS.register("vile_blood",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    LEATHER_SCRAP = ITEMS.register("leather_scrap",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Dreadium Stuff
    DREADIUM_CHUNK = ITEMS.register("dreadium_chunk",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),
    DREADIUM_INGOT = ITEMS.register("dreadium_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Dreadium Branches
    SHROOMITE_INGOT = ITEMS.register("shroomite_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Divine Stuff
    DIVINE_ALLOY = ITEMS.register("divine_alloy",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    // Necrium Stuff
    NECRIUM_CHUNK = ITEMS.register("necrium_chunk",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),
    NECRIUM_INGOT = ITEMS.register("necrium_ingot",
            () -> new Item(new Properties()
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

    SHROOMITE_SWORD = ItemUtils.sword(ModItemTier.SHROOMITE, 6, -0.4f),
            SHROOMITE_PICKAXE = ItemUtils.pickaxe(ModItemTier.SHROOMITE, -1, 0),
            SHROOMITE_AXE = ItemUtils.axe(ModItemTier.SHROOMITE, 13, -0.2f),
            SHROOMITE_SHOVEL = ItemUtils.shovel(ModItemTier.SHROOMITE, 0, 0),
            SHROOMITE_HOE = ItemUtils.hoe(ModItemTier.SHROOMITE, -9, 0),
            SHROOMITE_HELMET = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.HEAD),
            SHROOMITE_CHESTPLATE = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.CHEST),
            SHROOMITE_LEGGINGS = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.LEGS),
            SHROOMITE_BOOTS = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.FEET),

    // Essences
    ESSENCE_CHANGE = ItemUtils.essence("change"),
            ESSENCE_CONTINUITY = ItemUtils.essence("continuity"),
            ESSENCE_DARKNESS = ItemUtils.essence("darkness"),
            ESSENCE_DAY = ItemUtils.essence("day"),
            ESSENCE_DEATH = ItemUtils.essence("death"),
            ESSENCE_END = ItemUtils.essence("end"),
            ESSENCE_OVERWORLD = ItemUtils.essence("earth"),
            ESSENCE_ENERGY = ItemUtils.essence("energy"),
            ESSENCE_FLAMES = ItemUtils.essence("flames"),
            ESSENCE_INFINITY = ItemUtils.essence("infinity"),
            ESSENCE_LIFE = ItemUtils.essence("life"),
            ESSENCE_LIGHT = ItemUtils.essence("light"), //
            ESSENCE_NIGHTMARES = ItemUtils.essence("nightmares"), // Night-Vision Goggles
            ESSENCE_NETHER = ItemUtils.essence("nether"),
            ESSENCE_NIGHT = ItemUtils.essence("night"),
            ESSENCE_PLANETS = ItemUtils.essence("planets"),
            ESSENCE_SEA = ItemUtils.essence("sea"), // Bubble Wand - Places bubble blocks
            ESSENCE_STARS = ItemUtils.essence("stars"), //
            ESSENCE_STONE = ItemUtils.essence("stone"), // Lucky Rock - +1 ore drop besides solid drops
            ESSENCE_VOID = ItemUtils.essence("void"), // Void Bundle - Persistent Double Chest
            ESSENCE_WIND = ItemUtils.essence("wind"), // Air Rocket - Infinite Rocket for Elytra, Essence Wind + 2 Rocket + Soul of Infinity
    // Souls
    SOUL_BALANCE = ItemUtils.soul("balance"),
            SOUL_ELEMENTS = ItemUtils.soul("elements"), //
            SOUL_SPACE = ItemUtils.soul("space"),
                //
            SOUL_TIME = ItemUtils.soul("time"),
                // Chronos' Clock - Skips time,
                // Chronos' Stopwatch - On right click, saves your position and then sends you back when clicked again,
            SOUL_INFINITY = ItemUtils.soul("infinity"),

    // Spawn Eggs
    ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.ROT_FLY.get(), 0x382946, 0xA41717, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    BEHEMOTH_SPAWN_EGG = ITEMS.register("behemoth_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.BEHEMOTH.get(), 0x442266, 0x040410, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SNOW_FLINX_SPAWN_EGG = ITEMS.register("snow_flinx_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.SNOW_FLINX.get(), 0xDDEEEE, 0xFAD2AC, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    LEECH_SPAWN_EGG = ITEMS.register("leech_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.LEECH.get(), 0x3C1B22, 0x6B3640, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
}