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

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ITEM_REGISTRY);
    
    public static final Supplier<Item> BLUBBY_COIN = ITEMS.register("blubby_coin",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    ANCIENT_GUIDE_BOOK = ITEMS.register("ancient_guide_book", AncientGuideBookItem::new),

    BLUEPRINT = ITEMS.register("blueprint", BlueprintItem::new),

    HANDLE = ITEMS.register("handle",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    BUBBLE_WAND = ITEMS.register("bubble_wand",
            BubbleWandItem::new),

    CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            ChronosClockItem::new),

    CHRONOS_STOPWATCH = ITEMS.register("chronos_stopwatch",
            ChronosStopwatchItem::new),

    CURSED_GEM = ITEMS.register("cursed_gem",
            CursedGemItem::new),

    VOID_BUNDLE = ITEMS.register("ender_bundle",
            VoidBundleItem::new),

    HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HOT_PEPPER_CROP.get(), new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
            () -> new ToolTipItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1))),

    WIND_ROCKET = ITEMS.register("wind_rocket",
            WindRocketItem::new),

    // Accessories
    IRON_RING = ITEMS.register("iron_ring", BaseAccessoryItem::new),

    LEATHER_GLOVES = ITEMS.register("leather_gloves", BaseAccessoryItem::new),

    CHAIN_NECKLACE = ITEMS.register("chain_necklace", BaseAccessoryItem::new),

    LUCKY_ROCK = ITEMS.register("lucky_rock",
            BaseAccessoryItem::new),

    HASTY_CHISEL = ITEMS.register("hasty_chisel",
            HastyChiselItem::new),

    LUCKY_CHISEL = ITEMS.register("lucky_chisel",
            LuckyChiselItem::new),

    MYSTIC_EMBER = ITEMS.register("mystic_ember",
            BaseAccessoryItem::new),

    LAVA_RING = ITEMS.register("lava_ring",
            LavaRingItem::new),

    MYSTIC_MOLTEN_RING = ITEMS.register("mystic_molten_ring",
            LavaRingItem::new),

    ETERNAL_SATCHEL = ITEMS.register("eternal_satchel",
            BaseAccessoryItem::new),

    VAMPIRE_GLOVES = ITEMS.register("vampire_gloves",
            BaseAccessoryItem::new),

    DEMON_GLOVES = ITEMS.register("demon_gloves",
            DemonGloveItem::new),

    BAND_OF_REGENERATION = ITEMS.register("band_of_regeneration",
            BandOfRegenerationItem::new),

    HEART_NECKLACE = ITEMS.register("heart_necklace",
            HeartNecklaceItem::new),

    DRAGON_HEART_NECKLACE = ITEMS.register("dragon_heart_necklace",
            DragonHeartNecklaceItem::new),

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

    FAIRY_DUST = ITEMS.register("fairy_dust",
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

    TIME_GEAR = ITEMS.register("time_gear",
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

    // Spawn Eggs
    ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.ROT_FLY.get(), 0x382946, 0xA41717, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64))),

    SPORE_FLY_SPAWN_EGG = ITEMS.register("spore_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.SPORE_FLY.get(), 0x520F12, 0xB18C8C, new Properties()
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
                    .stacksTo(64))),

    DARK_FAIRY_SPAWN_EGG = ITEMS.register("dark_fairy_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.DARK_FAIRY.get(), 0xA08989, 0xCC6767, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
}