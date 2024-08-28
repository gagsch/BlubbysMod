package me.blubby.bmod.common.item;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.armor.ModArmorItem;
import me.blubby.bmod.common.item.custom.BlackHoleItem;
import me.blubby.bmod.common.item.custom.InventoryItem;
import me.blubby.bmod.common.item.custom.InventoryItem.inventoryItems;
import me.blubby.bmod.common.tier.ModArmorMaterial;
import me.blubby.bmod.common.item.custom.ChronosClockItem;
import me.blubby.bmod.common.item.custom.EnderBundleItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Blubby_sModOfDoom.MOD_ID);

    static Item.Properties _misc = new Item.Properties().tab(CreativeModeTab.TAB_MISC).durability(-1).stacksTo(64);
    static Item.Properties _combat = new Item.Properties().tab(CreativeModeTab.TAB_COMBAT);

    public static final RegistryObject<Item> BLUBBY_COIN = ITEMS.register("blubby_coin", () -> new Item(_misc));


    // Special Items
    public static final RegistryObject<Item> CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            () -> new ChronosClockItem(_misc));
    public static final RegistryObject<Item> ENDER_BUNDLE = ITEMS.register("ender_bundle",
            () -> new EnderBundleItem(_misc));
    public static final RegistryObject<Item> BLACK_HOLE = ITEMS.register("black_hole",
            () -> new BlackHoleItem(_misc));
    public static final RegistryObject<Item> LUCKY_ROCK = ITEMS.register("lucky_rock",
            () -> new InventoryItem(_misc, inventoryItems.LuckyRock));


    // Materials
    public static final RegistryObject<Item> WARDEN_CORE = ITEMS.register("warden_core", () -> new Item(_misc));
    public static final RegistryObject<Item> GUARDIAN_CORE = ITEMS.register("guardian_core", () -> new Item(_misc));
    public static final RegistryObject<Item> HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_DUST = ITEMS.register("soul_powder", () -> new Item(_misc));
    public static final RegistryObject<Item> ECHOING_SOUL_DUST = ITEMS.register("echoing_soul_dust", () -> new Item(_misc));
    public static final RegistryObject<Item> CONCENTRATED_DARK_MATTER = ITEMS.register("concentrated_dark_matter", () -> new Item(_misc));
    public static final RegistryObject<Item> LEATHER_SCRAP = ITEMS.register("leather_scrap", () -> new Item(_misc));


    // Ingots and Uncooked Ingots
    public static final RegistryObject<Item> BLESSED_SOUL_ALLOY = ITEMS.register("blessed_soul_alloy", () -> new Item(_misc));
    public static final RegistryObject<Item> BLESSED_INGOT = ITEMS.register("blessed_ingot", () -> new Item(_misc));
    public static final RegistryObject<Item> NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot", () -> new Item(_misc));
    public static final RegistryObject<Item> NECRIUM_CHUNK = ITEMS.register("necrium_chunk", () -> new Item(_misc));
    public static final RegistryObject<Item> COSMILITE_CHUNK = ITEMS.register("cosmilite_chunk", () -> new Item(_misc));
    public static final RegistryObject<Item> COSMILITE_INGOT = ITEMS.register("cosmilite_ingot", () -> new Item(_misc));


    // Armor
    // Nightmare Armor
    public static final RegistryObject<Item> NIGHTMARE_HELMET = ITEMS.register("nightmare_helmet",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.HEAD, _combat.durability(66 * 6)));
    public static final RegistryObject<Item> NIGHTMARE_CHESTPLATE = ITEMS.register("nightmare_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.CHEST, _combat.durability(66 * 13)));
    public static final RegistryObject<Item> NIGHTMARE_LEGGINGS = ITEMS.register("nightmare_leggings",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.LEGS, _combat.durability(66 * 13)));
    public static final RegistryObject<Item> NIGHTMARE_BOOTS = ITEMS.register("nightmare_boots",
            () -> new ModArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.FEET, _combat.durability(66 * 6)));
    // Cosmilite Armor
    public static final RegistryObject<Item> COSMILITE_HELMET = ITEMS.register("cosmilite_helmet",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.HEAD, _combat.durability(80 * 8)));
    public static final RegistryObject<Item> COSMILITE_CHESTPLATE = ITEMS.register("cosmilite_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.CHEST, _combat.durability(80 * 18)));
    public static final RegistryObject<Item> COSMILITE_LEGGINGS = ITEMS.register("cosmilite_leggings",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.LEGS, _combat.durability(80 * 16)));
    public static final RegistryObject<Item> COSMILITE_BOOTS = ITEMS.register("cosmilite_boots",
            () -> new ModArmorItem(ModArmorMaterial.COSMILITE, EquipmentSlot.FEET, _combat.durability(80 * 8)));
    // Divine Armor
    public static final RegistryObject<Item> BLESSED_HELMET = ITEMS.register("blessed_helmet",
            () -> new ModArmorItem(ModArmorMaterial.BLESSED, EquipmentSlot.HEAD, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> BLESSED_CHESTPLATE = ITEMS.register("blessed_chestplate",
            () -> new ModArmorItem(ModArmorMaterial.BLESSED, EquipmentSlot.CHEST, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> BLESSED_LEGGINGS = ITEMS.register("blessed_leggings",
            () -> new ModArmorItem(ModArmorMaterial.BLESSED, EquipmentSlot.LEGS, _combat.durability(77 * 7)));
    public static final RegistryObject<Item> BLESSED_BOOTS = ITEMS.register("blessed_boots",
            () -> new ModArmorItem(ModArmorMaterial.BLESSED, EquipmentSlot.FEET, _combat.durability(77 * 7)));


    // Essence
    public static final RegistryObject<Item> ESSENCE_BLESSED = ITEMS.register("essence_blessed", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DARKNESS = ITEMS.register("essence_darkness", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DAY = ITEMS.register("essence_day", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DEATH = ITEMS.register("essence_death", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_DOOM = ITEMS.register("essence_doom", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_EARTH = ITEMS.register("essence_earth", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_ENERGY = ITEMS.register("essence_energy", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_FLAMES = ITEMS.register("essence_flames", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_INFINITY = ITEMS.register("essence_infinity", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_LIFE = ITEMS.register("essence_life", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_LIGHT = ITEMS.register("essence_light", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_NETHER = ITEMS.register("essence_nether", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_NIGHT = ITEMS.register("essence_night", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_SEA = ITEMS.register("essence_sea", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_STONE = ITEMS.register("essence_stone", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_TUNDRA = ITEMS.register("essence_tundra", () -> new Item(_misc));
    public static final RegistryObject<Item> ESSENCE_VOID = ITEMS.register("essence_void", () -> new Item(_misc));


    // Souls
    public static final RegistryObject<Item> SOUL_BALANCE = ITEMS.register("soul_balance", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_ELEMENTS = ITEMS.register("soul_elements", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_SPACE = ITEMS.register("soul_space", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_TIME = ITEMS.register("soul_time", () -> new Item(_misc));
    public static final RegistryObject<Item> SOUL_INFINITY = ITEMS.register("soul_infinity", () -> new Item(_misc));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}