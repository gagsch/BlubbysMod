package me.blubby.bmod.content.item;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.content.armor.BlubbyArmorItem;
import me.blubby.bmod.content.armor.ModArmorMaterial;
import me.blubby.bmod.content.item.custom.ChronosClockItem;
import me.blubby.bmod.content.item.custom.EnderDimensionalChest;
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

    public static final RegistryObject<Item> BLUBBY_COIN = ITEMS.register("blubby_coin",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CHRONOS_CLOCK = ITEMS.register("chronos_clock",
            () -> new ChronosClockItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ENDER_BUNDLE = ITEMS.register("ender_bundle",
            () -> new EnderDimensionalChest(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_BLESSED = ITEMS.register("essence_blessed",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_DARKNESS = ITEMS.register("essence_darkness",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_DAY = ITEMS.register("essence_day",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_DEATH = ITEMS.register("essence_death",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_DOOM = ITEMS.register("essence_doom",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_EARTH = ITEMS.register("essence_earth",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_ENERGY = ITEMS.register("essence_energy",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_FLAMES = ITEMS.register("essence_flames",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_INFINITY = ITEMS.register("essence_infinity",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_LIFE = ITEMS.register("essence_life",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_LIGHT = ITEMS.register("essence_light",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_NETHER = ITEMS.register("essence_nether",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_NIGHT = ITEMS.register("essence_night",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_SEA = ITEMS.register("essence_sea",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_STONE = ITEMS.register("essence_stone",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_TUNDRA = ITEMS.register("essence_tundra",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ESSENCE_VOID = ITEMS.register("essence_void",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_BALANCE = ITEMS.register("soul_balance",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_ELEMENTS = ITEMS.register("soul_elements",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_SPACE = ITEMS.register("soul_space",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_TIME = ITEMS.register("soul_time",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_INFINITY = ITEMS.register("soul_infinity",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> WARDEN_CORE = ITEMS.register("warden_core",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> GUARDIAN_CORE = ITEMS.register("guardian_core",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> SOUL_DUST = ITEMS.register("soul_powder",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BLESSED_SOUL_ALLOY = ITEMS.register("blessed_soul_alloy",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BLESSED_INGOT = ITEMS.register("blessed_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ECHOING_SOUL_DUST = ITEMS.register("echoing_soul_dust",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> COSMILITE_CHUNK = ITEMS.register("cosmilite_chunk",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> COSMILITE_INGOT = ITEMS.register("cosmilite_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NIGHTMARE_HELMET = ITEMS.register("nightmare_helmet",
            () -> new BlubbyArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NIGHTMARE_CHESTPLATE = ITEMS.register("nightmare_chestplate",
            () -> new BlubbyArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NIGHTMARE_LEGGINGS = ITEMS.register("nightmare_leggings",
            () -> new BlubbyArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> NIGHTMARE_BOOTS = ITEMS.register("nightmare_boots",
            () -> new BlubbyArmorItem(ModArmorMaterial.NIGHTMARE, EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}