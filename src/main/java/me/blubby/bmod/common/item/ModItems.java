package me.blubby.bmod.common.item;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.armor.ModArmorItem;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.item.custom.*;
import me.blubby.bmod.common.item.custom.InventoryItem.inventoryItems;
import me.blubby.bmod.common.tier.ModArmorMaterial;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.blubby.bmod.common.item.custom.ToolTipItem.ToolTips.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Blubby_sModOfDoom.MOD_ID);

    static Item.Properties _misc = new Item.Properties().tab(CreativeModeTab.TAB_MISC).durability(-1).stacksTo(64);
    static Item.Properties _combat = new Item.Properties().tab(CreativeModeTab.TAB_COMBAT);

    public static final TagKey<Item> bossCores = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "boss_cores"));

    public static final RegistryObject<Item> BLUBBY_COIN = ITEMS.register("blubby_coin", () -> new Item(_misc));

    // Special Items
    public static final RegistryObject<Item> CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            () -> new ChronosClockItem(_misc));
    public static final RegistryObject<Item> ENDER_BUNDLE = ITEMS.register("ender_bundle",
            () -> new EnderBundleItem(_misc));
    public static final RegistryObject<Item> RIFT_KEY = ITEMS.register("rift_key",
            () -> new DimensionItem(_misc));
    public static final RegistryObject<Item> LUCKY_ROCK = ITEMS.register("lucky_rock",
            () -> new InventoryItem(_misc, inventoryItems.LuckyRock, lucky_rock));
    public static final RegistryObject<Item> TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
            () -> new ToolTipItem(_combat, totem_of_dreams));


    // Materials
    public static final RegistryObject<Item> WARDEN_CORE = ITEMS.register("warden_core", () -> new Item(_misc));
    public static final RegistryObject<Item> GUARDIAN_CORE = ITEMS.register("guardian_core", () -> new Item(_misc));
    public static final RegistryObject<Item> HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_DUST = ITEMS.register("soul_powder", () -> new Item(_misc));
    public static final RegistryObject<Item> ECHOING_SOUL_DUST = ITEMS.register("echoing_soul_dust", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_FRAGMENT = ITEMS.register("soul_fragment", () -> new Item(_misc));
    public static final RegistryObject<Item> CONCENTRATED_DARK_MATTER = ITEMS.register("concentrated_dark_matter", () -> new ToolTipItem(_misc, concentrated_dark_matter));
    public static final RegistryObject<Item> LEATHER_SCRAP = ITEMS.register("leather_scrap", () -> new Item(_misc));

    // Nightmare Stuff
    public static final RegistryObject<Item> NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot", () -> new Item(_misc));
    public static final RegistryObject<Item> NIGHTMARE_HELMET = ITEMS.register("nightmare_helmet",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.HEAD, _combat.durability(66 * 6)));
    public static final RegistryObject<Item> NIGHTMARE_CHESTPLATE = ITEMS.register("nightmare_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.CHEST, _combat.durability(66 * 13)));
    public static final RegistryObject<Item> NIGHTMARE_LEGGINGS = ITEMS.register("nightmare_leggings",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.LEGS, _combat.durability(66 * 13)));
    public static final RegistryObject<Item> NIGHTMARE_BOOTS = ITEMS.register("nightmare_boots",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.FEET, _combat.durability(66 * 6)));
    // Cosmilite Stuff
    public static final RegistryObject<Item> COSMILITE_CHUNK = ITEMS.register("cosmilite_chunk", () -> new Item(_misc));
    public static final RegistryObject<Item> COSMILITE_INGOT = ITEMS.register("cosmilite_ingot", () -> new Item(_misc));
    public static final RegistryObject<Item> COSMILITE_HELMET = ITEMS.register("cosmilite_helmet",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.HEAD, _combat.durability(80 * 8)));
    public static final RegistryObject<Item> COSMILITE_CHESTPLATE = ITEMS.register("cosmilite_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.CHEST, _combat.durability(80 * 18)));
    public static final RegistryObject<Item> COSMILITE_LEGGINGS = ITEMS.register("cosmilite_leggings",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.LEGS, _combat.durability(80 * 16)));
    public static final RegistryObject<Item> COSMILITE_BOOTS = ITEMS.register("cosmilite_boots",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.FEET, _combat.durability(80 * 8)));
    // Divine Stuff
    public static final RegistryObject<Item> DIVINE_ALLOY = ITEMS.register("divine_alloy", () -> new Item(_misc));
    public static final RegistryObject<Item> DIVINE_INGOT = ITEMS.register("divine_ingot", () -> new Item(_misc));
    public static final RegistryObject<Item> DIVINE_HELMET = ITEMS.register("divine_helmet",
            () -> new ModArmorItem(ModArmorMaterial.DIVINE, EquipmentSlot.HEAD, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> DIVINE_CHESTPLATE = ITEMS.register("divine_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.DIVINE, EquipmentSlot.CHEST, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> DIVINE_LEGGINGS = ITEMS.register("divine_leggings",
            () -> new ModArmorItem(ModArmorMaterial.DIVINE, EquipmentSlot.LEGS, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> DIVINE_BOOTS = ITEMS.register("divine_boots",
            () -> new ModArmorItem(ModArmorMaterial.DIVINE, EquipmentSlot.FEET, _combat.durability(77 * 7)));


    // Necrium Stuff
    public static final RegistryObject<Item> NECRIUM_CHUNK = ITEMS.register("necrium_chunk", () -> new Item(_misc));


    // Essence
    public static final RegistryObject<Item> ESSENCE_BLESSED = ITEMS.register("essence_blessed", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_CONVERGENCE = ITEMS.register("essence_convergence", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DARKNESS = ITEMS.register("essence_darkness", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DAY = ITEMS.register("essence_day", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DEATH = ITEMS.register("essence_death", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DOOM = ITEMS.register("essence_doom", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_END = ITEMS.register("essence_end", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_OVERWORLD = ITEMS.register("essence_earth", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_ENERGY = ITEMS.register("essence_energy", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_FLAMES = ITEMS.register("essence_flames", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_INFINITY = ITEMS.register("essence_infinity", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_LIFE = ITEMS.register("essence_life", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_LIGHT = ITEMS.register("essence_light", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_NETHER = ITEMS.register("essence_nether", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_NIGHT = ITEMS.register("essence_night", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_PLANETS = ITEMS.register("essence_planets", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_SEA = ITEMS.register("essence_sea", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_STARS = ITEMS.register("essence_stars", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_STONE = ITEMS.register("essence_stone", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_TUNDRA = ITEMS.register("essence_tundra", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_VOID = ITEMS.register("essence_void", () -> new Item(_misc));


    // Souls
    public static final RegistryObject<Item> SOUL_BALANCE = ITEMS.register("soul_balance", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_DIMENSIONS = ITEMS.register("soul_dimensions", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_ELEMENTS = ITEMS.register("soul_elements", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_SPACE = ITEMS.register("soul_space", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_TIME = ITEMS.register("soul_time", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_INFINITY = ITEMS.register("soul_infinity", () -> new Item(_misc));


    // Spawn Eggs
    public static final RegistryObject<Item> ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ROT_FLY, 0x382946, 0xa41717, _misc));
    public static final RegistryObject<Item> BEHEMOTH_SPAWN_EGG = ITEMS.register("behemoth_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BEHEMOTH, 0x442266, 0x111133, _misc));
    public static final RegistryObject<Item> SNOW_FLINX_SPAWN_EGG = ITEMS.register("snow_flinx_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SNOW_FLINX, 0xddeeee, 0xfad2ac, _misc));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}