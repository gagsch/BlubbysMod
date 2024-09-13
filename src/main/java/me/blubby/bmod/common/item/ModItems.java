package me.blubby.bmod.common.item;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.item.custom.*;
import me.blubby.bmod.common.item.custom.InventoryItem.inventoryItems;
import me.blubby.bmod.common.tier.ModArmorMaterial;
import me.blubby.bmod.common.tier.ModItemTier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.blubby.bmod.common.item.custom.ToolTipItem.ToolTips.*;
import static me.blubby.bmod.utils.ItemUtils.*;

public class ModItems  {
    public static final DeferredRegister<Item> ITEMS
            = DeferredRegister.create(ForgeRegistries.ITEMS, Blubby_sModOfDoom.MOD_ID);

    // Tags
    public static final TagKey<Item>
            bossCores = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "boss_cores"));

    // Items
    public static final RegistryObject<Item>
            BLUBBY_COIN = ITEMS.register("blubby_coin",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            // Special Items
            CHRONOS_CLOCK = ITEMS.register("chronos_clock",
                    () -> new ChronosClockItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(1)
                            .durability(-1))),

            ENDER_BUNDLE = ITEMS.register("ender_bundle",
                    () -> new EnderBundleItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(1)
                            .durability(-1))),

            RIFT_KEY = ITEMS.register("rift_key",
                    () -> new DimensionItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(1)
                            .durability(-1))),

            LUCKY_ROCK = ITEMS.register("lucky_rock",
                    () -> new InventoryItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(1)
                            .durability(-1), inventoryItems.LuckyRock, lucky_rock)),

            TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
                    () -> new ToolTipItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(1)
                            .durability(-1), totem_of_dreams)),

            BUBBLE_WAND = ITEMS.register("bubble_wand",
                    () -> new BubbleWandItem()),

            HOT_PEPPER = ITEMS.register("hot_pepper",
                    () -> new ToolTipItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1)
                            .food(new FoodProperties.Builder()
                                    .nutrition(3)
                                    .saturationMod(3F)
                                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1), 1F)
                                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0), 1F)
                                    .alwaysEat()
                                    .build()), hot_pepper)),

            HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds",
                    () -> new ItemNameBlockItem(ModBlocks.HOT_PEPPER_CROP.get(), new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),



            // Materials
            WARDEN_CORE = ITEMS.register("warden_core",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            GUARDIAN_CORE = ITEMS.register("guardian_core",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            SOUL_DUST = ITEMS.register("soul_powder",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            ECHOING_SOUL_DUST = ITEMS.register("echoing_soul_dust",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            SOUL_FRAGMENT = ITEMS.register("soul_fragment",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            CONCENTRATED_DARK_MATTER = ITEMS.register("concentrated_dark_matter",
                    () -> new ToolTipItem(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1), concentrated_dark_matter)),

            LEATHER_SCRAP = ITEMS.register("leather_scrap",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),



            // Nightmare Stuff
            NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),



            // Cosmilite Stuff
            COSMILITE_CHUNK = ITEMS.register("cosmilite_chunk",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            COSMILITE_INGOT = ITEMS.register("cosmilite_ingot",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),



            // Divine Stuff
            DIVINE_ALLOY = ITEMS.register("divine_alloy",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            // Necrium Stuff
            NECRIUM_CHUNK = ITEMS.register("necrium_chunk",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),
            NECRIUM_INGOT = ITEMS.register("necrium_ingot",
                    () -> new Item(new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),

            // Armor
            DIVINE_SWORD = sword(ModItemTier.DIVINE, 3, 0f),
            DIVINE_PICKAXE = pickaxe(ModItemTier.DIVINE, -1, 0),
            DIVINE_AXE = axe(ModItemTier.DIVINE, 5, 0f),
            DIVINE_SHOVEL = shovel(ModItemTier.DIVINE, 0, 0),
            DIVINE_HOE = hoe(ModItemTier.DIVINE, -5, 0),
            DIVINE_HELMET = armor(ModArmorMaterial.DIVINE, EquipmentSlot.HEAD),
            DIVINE_CHESTPLATE = armor(ModArmorMaterial.DIVINE, EquipmentSlot.CHEST),
            DIVINE_LEGGINGS = armor(ModArmorMaterial.DIVINE, EquipmentSlot.LEGS),
            DIVINE_BOOTS = armor(ModArmorMaterial.DIVINE, EquipmentSlot.FEET),

            COSMILITE_SWORD = sword(ModItemTier.COSMILITE, 6, -0.4f),
            COSMILITE_PICKAXE = pickaxe(ModItemTier.COSMILITE, -1, 0),
            COSMILITE_AXE = axe(ModItemTier.COSMILITE, 13, -0.2f),
            COSMILITE_SHOVEL = shovel(ModItemTier.COSMILITE, 0, 0),
            COSMILITE_HOE = hoe(ModItemTier.COSMILITE, -9, 0),
            COSMILITE_HELMET = armor(ModArmorMaterial.COSMILITE, EquipmentSlot.HEAD),
            COSMILITE_CHESTPLATE = armor(ModArmorMaterial.COSMILITE, EquipmentSlot.CHEST),
            COSMILITE_LEGGINGS = armor(ModArmorMaterial.COSMILITE, EquipmentSlot.LEGS),
            COSMILITE_BOOTS = armor(ModArmorMaterial.COSMILITE, EquipmentSlot.FEET),

            NIGHTMARE_HELMET = armor(ModArmorMaterial.NIGHTMARE, EquipmentSlot.HEAD),
            NIGHTMARE_CHESTPLATE = armor(ModArmorMaterial.NIGHTMARE, EquipmentSlot.CHEST),
            NIGHTMARE_LEGGINGS = armor(ModArmorMaterial.NIGHTMARE, EquipmentSlot.LEGS),
            NIGHTMARE_BOOTS = armor(ModArmorMaterial.NIGHTMARE, EquipmentSlot.FEET),

            // Essences and Souls
            ESSENCE_BLESSED = essence("blessed"),
            ESSENCE_CONVERGENCE = essence("convergence"),
            ESSENCE_DARKNESS = essence("darkness"),
            ESSENCE_DAY = essence("day"),
            ESSENCE_DEATH = essence("death"),
            ESSENCE_END = essence("end"),
            ESSENCE_OVERWORLD = essence("earth"),
            ESSENCE_ENERGY = essence("energy"),
            ESSENCE_FLAMES = essence("flames"),
            ESSENCE_HAVOC = essence("havoc"),
            ESSENCE_INFINITY = essence("infinity"),
            ESSENCE_LIFE = essence("life"),
            ESSENCE_LIGHT = essence("light"),
            ESSENCE_NETHER = essence("nether"),
            ESSENCE_NIGHT = essence("night"),
            ESSENCE_PLANETS = essence("planets"),
            ESSENCE_SEA = essence("sea"),
            ESSENCE_STARS = essence("stars"),
            ESSENCE_STONE = essence("stone"),
            ESSENCE_TUNDRA = essence("tundra"),
            ESSENCE_VOID = essence("void"),
            SOUL_BALANCE = soul("balance"),
            SOUL_DIMENSIONS = soul("dimensions"),
            SOUL_ELEMENTS = soul("elements"),
            SOUL_SPACE = soul("space"),
            SOUL_TIME = soul("time"),
            SOUL_INFINITY = soul("infinity"),

            // Spawn Eggs
            ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.ROT_FLY, 0x382946, 0xa41717, new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),
            BEHEMOTH_SPAWN_EGG = ITEMS.register("behemoth_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.BEHEMOTH, 0x442266, 0x111133, new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1))),
            SNOW_FLINX_SPAWN_EGG = ITEMS.register("snow_flinx_spawn_egg",
                    () -> new ForgeSpawnEggItem(ModEntities.SNOW_FLINX, 0xddeeee, 0xfad2ac, new Item.Properties()
                            .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                            .stacksTo(64)
                            .durability(-1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}