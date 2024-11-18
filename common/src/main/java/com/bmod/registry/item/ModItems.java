package com.bmod.registry.item;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.custom.*;
import com.bmod.registry.item.tier.ModArmorMaterial;
import com.bmod.registry.item.tier.ModItemTier;
import com.bmod.util.ItemUtils;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

import static com.bmod.registry.item.custom.ToolTipItem.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.ITEM_REGISTRY);
    
    public static final Supplier<Item> BLUBBY_COIN = ITEMS.register("blubby_coin",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> ANCIENT_GUIDE_BOOK = ITEMS.register("ancient_guide_book", AncientGuideBookItem::new);

    // Special Items
    public static final Supplier<Item> BUBBLE_WAND = ITEMS.register("bubble_wand", BubbleWandItem::new);
    public static final Supplier<Item> CHRONOS_CLOCK = ITEMS.register("chronos_clock", ChronosClockItem::new);
    public static final Supplier<Item> CHRONOS_STOPWATCH = ITEMS.register("chronos_stopwatch", ChronosStopwatchItem::new);
    public static final Supplier<Item> CURSED_GEM = ITEMS.register("cursed_gem", CursedGemItem::new);
    public static final Supplier<Item> VOID_BUNDLE = ITEMS.register("ender_bundle", VoidBundleItem::new);
    public static final Supplier<Item> HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HOT_PEPPER_CROP.get(), new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> TOTEM_OF_DREAMS = ITEMS.register("totem_of_dreaming",
            () -> new ToolTipItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(1)));
    public static final Supplier<Item> WIND_ROCKET = ITEMS.register("wind_rocket", WindRocketItem::new);

    // Accessories
    public static final Supplier<Item> IRON_RING = ITEMS.register("iron_ring", BaseAccessoryItem::new);
    public static final Supplier<Item> LEATHER_GLOVES = ITEMS.register("leather_gloves", BaseAccessoryItem::new);
    public static final Supplier<Item> CHAIN_NECKLACE = ITEMS.register("chain_necklace", BaseAccessoryItem::new);
    public static final Supplier<Item> LUCKY_ROCK = ITEMS.register("lucky_rock", BaseAccessoryItem::new);
    public static final Supplier<Item> HASTY_CHISEL = ITEMS.register("hasty_chisel", HastyChiselItem::new);
    public static final Supplier<Item> LUCKY_CHISEL = ITEMS.register("lucky_chisel", LuckyChiselItem::new);
    public static final Supplier<Item> MYSTIC_EMBER = ITEMS.register("mystic_ember", BaseAccessoryItem::new);
    public static final Supplier<Item> LAVA_RING = ITEMS.register("lava_ring", LavaRingItem::new);
    public static final Supplier<Item> MYSTIC_MOLTEN_RING = ITEMS.register("mystic_molten_ring", LavaRingItem::new);
    public static final Supplier<Item> ETERNAL_SATCHEL = ITEMS.register("eternal_satchel", BaseAccessoryItem::new);
    public static final Supplier<Item> VAMPIRE_GLOVES = ITEMS.register("vampire_gloves", BaseAccessoryItem::new);
    public static final Supplier<Item> DEMON_GLOVES = ITEMS.register("demon_gloves", DemonGloveItem::new);
    public static final Supplier<Item> BAND_OF_REGENERATION = ITEMS.register("band_of_regeneration", BandOfRegenerationItem::new);
    public static final Supplier<Item> HEART_NECKLACE = ITEMS.register("heart_necklace", HeartNecklaceItem::new);
    public static final Supplier<Item> DRAGON_HEART_NECKLACE = ITEMS.register("dragon_heart_necklace", DragonHeartNecklaceItem::new);
    public static final Supplier<Item> NECROMANCY_101 = ITEMS.register("necromancy_101", BaseAccessoryItem::new);

    // Food
    public static final Supplier<Item> HOT_PEPPER = ITEMS.register("hot_pepper",
            () -> new HotPepperItem(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> ROTTEN_APPLE = ITEMS.register("rotten_apple",
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
                            .build())));

    // Materials
    public static final Supplier<Item> WARDEN_TENDRIL = ITEMS.register("warden_tendril",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> GUARDIAN_CORE = ITEMS.register("guardian_core",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> WITHER_SPINE = ITEMS.register("wither_spine",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> DRAGON_HEART = ITEMS.register("dragon_heart",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> HEART_OF_THE_ABYSS = ITEMS.register("heart_of_the_abyss",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> SOUL_DUST = ITEMS.register("soul_powder",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> FAIRY_DUST = ITEMS.register("fairy_dust",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> SOUL_FRAGMENT = ITEMS.register("soul_fragment",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> VILE_BLOOD = ITEMS.register("vile_blood",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> LEATHER_SCRAP = ITEMS.register("leather_scrap",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> TIME_GEAR = ITEMS.register("time_gear",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> MOLTEN_SLAG = ITEMS.register("molten_slag",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> BEHEMOTH_TOOTH = ITEMS.register("behemoth_tooth",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));

    // Chunks and Ingots
    public static final Supplier<Item> DIVINE_ALLOY = ITEMS.register("divine_alloy",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> NECRIUM_CHUNK = ITEMS.register("necrium_chunk",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> NECRIUM_INGOT = ITEMS.register("necrium_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item>  DREADIUM_CHUNK = ITEMS.register("dreadium_chunk",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> DREADIUM_INGOT = ITEMS.register("dreadium_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> SHROOMITE_INGOT = ITEMS.register("shroomite_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()));
    public static final Supplier<Item> VOLCANIC_INGOT = ITEMS.register("volcanic_ingot",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)
                    .fireResistant()) {
        @Override
        public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
            target.setSecondsOnFire(3);
            return super.hurtEnemy(itemStack, target, attacker);
        }
    });
    public static final Supplier<Item> BLUEPRINT = ITEMS.register("blueprint", BlueprintItem::new);
    public static final Supplier<Item> HANDLE = ITEMS.register("handle",
            () -> new Item(new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));

    // Gear
    public static final Supplier<Item> DIVINE_SWORD = ItemUtils.sword(ModItemTier.DIVINE, 3, 0f);
    public static final Supplier<Item> DIVINE_PICKAXE = ItemUtils.pickaxe(ModItemTier.DIVINE, -1, 0);
    public static final Supplier<Item> DIVINE_AXE = ItemUtils.axe(ModItemTier.DIVINE, 5, 0f);
    public static final Supplier<Item> DIVINE_SHOVEL = ItemUtils.shovel(ModItemTier.DIVINE, 0, 0);
    public static final Supplier<Item> DIVINE_HOE = ItemUtils.hoe(ModItemTier.DIVINE, -5, 0);
    public static final Supplier<Item> DIVINE_HELMET = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.HEAD);
    public static final Supplier<Item> DIVINE_CHESTPLATE = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.CHEST);
    public static final Supplier<Item> DIVINE_LEGGINGS = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.LEGS);
    public static final Supplier<Item> DIVINE_BOOTS = ItemUtils.armor(ModArmorMaterial.DIVINE, EquipmentSlot.FEET);
    public static final Supplier<Item> SHROOMITE_SWORD = ItemUtils.sword(ModItemTier.SHROOMITE, 7, -0.4f);
    public static final Supplier<Item> SHROOMITE_SHOVEL = ItemUtils.shovel(ModItemTier.SHROOMITE, 0, 0);
    public static final Supplier<Item> SHROOMITE_PICKAXE = ItemUtils.pickaxe(ModItemTier.SHROOMITE, -1, 0);
    public static final Supplier<Item> SHROOMITE_AXE = ItemUtils.axe(ModItemTier.SHROOMITE, 13, -0.2f);
    public static final Supplier<Item> SHROOMITE_HOE = ItemUtils.hoe(ModItemTier.SHROOMITE, -9, 0);
    public static final Supplier<Item> SHROOMITE_HELMET = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.HEAD);
    public static final Supplier<Item> SHROOMITE_CHESTPLATE = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.CHEST);
    public static final Supplier<Item> SHROOMITE_LEGGINGS = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.LEGS);
    public static final Supplier<Item> SHROOMITE_BOOTS = ItemUtils.armor(ModArmorMaterial.SHROOMITE, EquipmentSlot.FEET);
    public static final Supplier<Item> VOLCANIC_SWORD = ITEMS.register("volcanic_sword", () -> new VolcanicSwordItem(6, 0));
    public static final Supplier<Item> VOLCANIC_MACE = ITEMS.register("volcanic_mace", () -> new VolcanicSwordItem(-7, -0.8f));
    public static final Supplier<Item> NECROMANCY_STAFF = ITEMS.register("necromancy_staff", NecromancyStaffItem::new);
    public static final Supplier<Item> REAVER_FANG = ITEMS.register("reaver_fang",
            () -> new SwordItem(ModItemTier.REAVER, 0, 2.0f, new Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(1337)) {
                @Override
                public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
                    ToolTipItem.makeTooltip(level, components, "reaver_fang");
                }
            });

    // Spawn Eggs
    public static final Supplier<Item> ROT_FLY_SPAWN_EGG = ITEMS.register("rot_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.ROT_FLY.get(), 0x382946, 0xA41717, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> SPORE_FLY_SPAWN_EGG = ITEMS.register("spore_fly_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.SPORE_FLY.get(), 0x520F12, 0xB18C8C, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> BEHEMOTH_SPAWN_EGG = ITEMS.register("behemoth_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.BEHEMOTH.get(), 0x442266, 0x040410, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> SNOW_FLINX_SPAWN_EGG = ITEMS.register("snow_flinx_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.SNOW_FLINX.get(), 0xDDEEEE, 0xFAD2AC, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> LEECH_SPAWN_EGG = ITEMS.register("leech_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.LEECH.get(), 0x3C1B22, 0x6B3640, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
    public static final Supplier<Item> DARK_FAIRY_SPAWN_EGG = ITEMS.register("dark_fairy_spawn_egg",
            () -> new SpawnEggItem(ModEntityTypes.DARK_FAIRY.get(), 0xA08989, 0xCC6767, new Properties()
                    .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                    .durability(-1)
                    .stacksTo(64)));
}