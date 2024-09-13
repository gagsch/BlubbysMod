package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.tier.ModItemTier;
import me.blubby.bmod.utils.WoodUtils;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.common.tier.ModArmorMaterial;
import me.blubby.bmod.utils.ItemUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Arrays;
import java.util.function.Consumer;

import static me.blubby.bmod.utils.WoodUtils.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        swordBuilder(consumer, ModItems.DIVINE_SWORD.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        pickaxeBuilder(consumer, ModItems.DIVINE_PICKAXE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        axeBuilder(consumer, ModItems.DIVINE_AXE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        shovelBuilder(consumer, ModItems.DIVINE_SHOVEL.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        hoeBuilder(consumer, ModItems.DIVINE_HOE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);

        upgradeToolsBuilder(consumer, ModItemTier.DIVINE, ModItemTier.COSMILITE, null);

        upgradeArmorTypeBuilder(consumer, ModArmorMaterial.DIVINE, ModArmorMaterial.COSMILITE, null);
        upgradeArmorTypeBuilder(consumer, ModArmorMaterial.DIVINE, ModArmorMaterial.NIGHTMARE, null);

        buildAllSouls(consumer);
        buildAllEssences(consumer);

        woodTypeBuilder(consumer, COSMIC_OAK);
        woodTypeBuilder(consumer, EBON);

        ShapelessRecipeBuilder.shapeless(ModItems.BUBBLE_WAND.get().asItem())
                .requires(Items.WATER_BUCKET)
                .requires(ModItems.ESSENCE_SEA.get())
                .unlockedBy("has_essence_sea", has(ModItems.ESSENCE_SEA.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.HOT_PEPPER_SEEDS.get(), 4)
                .define('s', Tags.Items.SEEDS)
                .define('F', ModItems.ESSENCE_FLAMES.get())
                .pattern(" s ")
                .pattern("sFs")
                .pattern(" s ")
                .unlockedBy("has_flames", has(ModItems.ESSENCE_FLAMES.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.TOTEM_OF_DREAMS.get())
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('L', ModItems.ESSENCE_LIFE.get())
                .define('D', ModItems.ESSENCE_DEATH.get())
                .define('t', Items.TOTEM_OF_UNDYING)
                .pattern(" b ")
                .pattern("LtD")
                .pattern(" b ")
                .unlockedBy("has_totem_of_undying", has(Items.TOTEM_OF_UNDYING))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smoking(Ingredient.of(Items.ROTTEN_FLESH),
                        ModItems.LEATHER_SCRAP.get(),
                        2,
                        100)
                .unlockedBy("has_rotten_flesh", has(Items.ROTTEN_FLESH))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .blasting(Ingredient.of(Items.SOUL_SAND),
                        ModItems.SOUL_DUST.get(),
                        1,
                        75)
                .unlockedBy("has_soul_sand", has(Items.SOUL_SAND))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.LEATHER,1)
                .requires(ModItems.LEATHER_SCRAP.get(), 4)
                .unlockedBy("has_leather_scrap", has(ModItems.LEATHER_SCRAP.get()))
                .save(consumer);

        UpgradeRecipeBuilder
                .smithing(Ingredient.of(Items.BUNDLE),
                        Ingredient.of(ModItems.ESSENCE_VOID.get()),
                        ModItems.ENDER_BUNDLE.get())
                .unlocks("has_essence_void", has(ModItems.ESSENCE_VOID.get()))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.ENDER_BUNDLE.getId().getPath()));

        UpgradeRecipeBuilder
                .smithing(Ingredient.of(ModItems.ESSENCE_STONE.get()),
                        Ingredient.of(ModItems.ESSENCE_BLESSED.get()),
                        ModItems.LUCKY_ROCK.get())
                .unlocks("has_essence_blessed", has(ModItems.ESSENCE_BLESSED.get()))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.LUCKY_ROCK.getId().getPath()));

        UpgradeRecipeBuilder
                .smithing(Ingredient.of(ModItems.SOUL_SPACE.get()),
                        Ingredient.of(ModItems.ESSENCE_INFINITY.get()),
                        ModItems.RIFT_KEY.get())
                .unlocks("has_essence_infinity", has(ModItems.ESSENCE_INFINITY.get()))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.RIFT_KEY.getId().getPath()));

        ShapedRecipeBuilder
                .shaped(ModItems.HEART_OF_THE_ABYSS.get())
                .define('a', Items.HEART_OF_THE_SEA)
                .define('b', Items.GOLD_INGOT)
                .define('c', Items.KELP)
                .define('d', Items.PRISMARINE_SHARD)
                .pattern("bbb")
                .pattern("cac")
                .pattern("ddd")
                .unlockedBy("has_heart_of_the_sea", has(Items.HEART_OF_THE_SEA))
                .save(consumer);

        UpgradeRecipeBuilder
                .smithing(Ingredient.of(Items.CLOCK),
                        Ingredient.of(ModItems.SOUL_TIME.get()),
                        ModItems.CHRONOS_CLOCK.get())
                .unlocks("has_soul_time", has(ModItems.SOUL_TIME.get()))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.CHRONOS_CLOCK.getId().getPath()));

        ShapedRecipeBuilder
                .shaped(ModItems.SOUL_FRAGMENT.get(), 2)
                .define('d', ModItems.SOUL_DUST.get())
                .define('B', ModItems.bossCores)
                .pattern(" d ")
                .pattern("dBd")
                .pattern(" d ")
                .unlockedBy("has_soul_dust", has(ModItems.SOUL_DUST.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.COSMILITE_INGOT.get(), 1)
                .define('#', ModItems.COSMILITE_CHUNK.get())
                .define('c', ModItems.CONCENTRATED_DARK_MATTER.get())
                .pattern("###")
                .pattern("#c#")
                .pattern("###")
                .unlockedBy("has_cosmilite_chunk", has(ModItems.COSMILITE_CHUNK.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModBlocks.COSMILITE_ORE.get()),
                        ModItems.COSMILITE_CHUNK.get(),
                        5,
                        300)
                .unlockedBy("has_cosmilite_ore", has(ModBlocks.COSMILITE_ORE.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_ALLOY.get(), 2)
                .define('#', Items.DIAMOND)
                .define('B', ModItems.ESSENCE_BLESSED.get())
                .define('s', ModItems.SOUL_FRAGMENT.get())
                .pattern("###")
                .pattern("BsB")
                .pattern("###")
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_HELMET.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_HELMET)
                .define('g', Items.GOLD_INGOT)
                .define('#', Items.FEATHER)
                .pattern("#g#")
                .pattern("bDb")
                .unlockedBy("has_diamond", has(Items.DIAMOND_HELMET))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_CHESTPLATE.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_CHESTPLATE)
                .define('g', Items.GOLD_INGOT)
                .pattern("b b")
                .pattern("bDb")
                .pattern("gbg")
                .unlockedBy("has_diamond", has(Items.DIAMOND_CHESTPLATE))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_LEGGINGS.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_LEGGINGS)
                .define('g', Items.GOLD_INGOT)
                .pattern("bDb")
                .pattern("g g")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_LEGGINGS))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_BOOTS.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_BOOTS)
                .define('g', Items.GOLD_INGOT)
                .pattern("gDg")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_BOOTS))
                .save(consumer);
    }

    protected void swordBuilder(Consumer<FinishedRecipe> consumer, ItemLike sword, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder
                .shaped(sword, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("B")
                .pattern("B")
                .pattern("h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void pickaxeBuilder(Consumer<FinishedRecipe> consumer, ItemLike pickaxe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder
                .shaped(pickaxe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BBB")
                .pattern(" h ")
                .pattern(" h ")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void axeBuilder(Consumer<FinishedRecipe> consumer, ItemLike axe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder
                .shaped(axe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BB")
                .pattern("Bh")
                .pattern(" h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void shovelBuilder(Consumer<FinishedRecipe> consumer, ItemLike shovel, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder
                .shaped(shovel, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("B")
                .pattern("h")
                .pattern("h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void hoeBuilder(Consumer<FinishedRecipe> consumer, ItemLike hoe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder
                .shaped(hoe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BB")
                .pattern(" h")
                .pattern(" h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void upgradeToolsBuilder(Consumer<FinishedRecipe> consumer, ModItemTier inputItemTier, ModItemTier outputItemTier, ItemLike upgradeItem) {
        if (upgradeItem == null)
        {
            upgradeItem = Arrays.stream(outputItemTier.getRepairIngredient().getItems()).toList().get(0).getItem();
        }

        Ingredient inputSword = Ingredient.of(ItemUtils.getItemFromId(inputItemTier.getName() + "_sword"));
        Ingredient inputPickaxe = Ingredient.of(ItemUtils.getItemFromId(inputItemTier.getName() + "_pickaxe"));
        Ingredient inputAxe = Ingredient.of(ItemUtils.getItemFromId(inputItemTier.getName() + "_axe"));
        Ingredient inputShovel = Ingredient.of(ItemUtils.getItemFromId(inputItemTier.getName() + "_shovel"));
        Ingredient inputHoe = Ingredient.of(ItemUtils.getItemFromId(inputItemTier.getName() + "_hoe"));

        Item outputSword = ItemUtils.getItemFromId(outputItemTier.getName() + "_sword");
        Item outputPickaxe = ItemUtils.getItemFromId(outputItemTier.getName() + "_pickaxe");
        Item outputAxe = ItemUtils.getItemFromId(outputItemTier.getName() + "_axe");
        Item outputShovel = ItemUtils.getItemFromId(outputItemTier.getName() + "_shovel");
        Item outputHoe = ItemUtils.getItemFromId(outputItemTier.getName() + "_hoe");

        UpgradeRecipeBuilder
                .smithing(
                        inputSword,
                        Ingredient.of(upgradeItem),
                        outputSword)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputItemTier.getName() + "_sword"));

        UpgradeRecipeBuilder
                .smithing(
                        inputPickaxe,
                        Ingredient.of(upgradeItem),
                        outputPickaxe)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputItemTier.getName() + "_pickaxe"));

        UpgradeRecipeBuilder
                .smithing(
                        inputAxe,
                        Ingredient.of(upgradeItem),
                        outputAxe)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputItemTier.getName() + "_axe"));

        UpgradeRecipeBuilder
                .smithing(
                        inputShovel,
                        Ingredient.of(upgradeItem),
                        outputShovel)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputItemTier.getName() + "_shovel"));

        UpgradeRecipeBuilder
                .smithing(
                        inputHoe,
                        Ingredient.of(upgradeItem),
                        outputHoe)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputItemTier.getName() + "_hoe"));
    }

    protected void upgradeArmorTypeBuilder(Consumer<FinishedRecipe> consumer, ArmorMaterial inputArmorMaterial, ArmorMaterial outputArmorMaterial, ItemLike upgradeItem) {
        if (upgradeItem == null)
        {
            upgradeItem = Arrays.stream(outputArmorMaterial.getRepairIngredient().getItems()).toList().get(0).getItem();
        }

        Ingredient inputHelmet = Ingredient.of(ItemUtils.getItemFromId(inputArmorMaterial.getName() + "_helmet"));
        Ingredient inputChest = Ingredient.of(ItemUtils.getItemFromId(inputArmorMaterial.getName() + "_chestplate"));
        Ingredient inputLeggings = Ingredient.of(ItemUtils.getItemFromId(inputArmorMaterial.getName() + "_leggings"));
        Ingredient inputBoots = Ingredient.of(ItemUtils.getItemFromId(inputArmorMaterial.getName() + "_boots"));

        Item outputHelmet = ItemUtils.getItemFromId(outputArmorMaterial.getName() + "_helmet");
        Item outputChest = ItemUtils.getItemFromId(outputArmorMaterial.getName() + "_chestplate");
        Item outputLeggings = ItemUtils.getItemFromId(outputArmorMaterial.getName() + "_leggings");
        Item outputBoots = ItemUtils.getItemFromId(outputArmorMaterial.getName() + "_boots");

        UpgradeRecipeBuilder
                .smithing(
                        inputHelmet,
                        Ingredient.of(upgradeItem),
                        outputHelmet)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_helmet"));

        UpgradeRecipeBuilder
                .smithing(
                        inputChest,
                        Ingredient.of(upgradeItem),
                        outputChest)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_chestplate"));

        UpgradeRecipeBuilder
                .smithing(
                        inputLeggings,
                        Ingredient.of(upgradeItem),
                        outputLeggings)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_leggings"));

        UpgradeRecipeBuilder
                .smithing(
                        inputBoots,
                        Ingredient.of(upgradeItem),
                        outputBoots)
                .unlocks("has_ingot", has(upgradeItem))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_boots"));
    }

    protected void woodTypeBuilder(Consumer<FinishedRecipe> consumer, String woodName) {
        ItemLike log = log(woodName).get();
        ItemLike wood = wood(woodName).get();
        ItemLike strippedLog = strippedLog(woodName).get();
        ItemLike strippedWood = strippedWood(woodName).get();

        planksFromLogs(consumer, planks(woodName).get(), WoodUtils.registerLogItemTag(woodName));

        ShapedRecipeBuilder
                .shaped(wood, 3)
                .define('L', log)
                .pattern("LL")
                .pattern("LL")
                .unlockedBy("has_log", has(log))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(strippedWood, 3)
                .define('L', strippedLog)
                .pattern("LL")
                .pattern("LL")
                .unlockedBy("has_stripped_log", has(strippedLog))
                .save(consumer);
    }

    protected void buildAllSouls(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.SOUL_INFINITY.get(),1)
                .requires(ModItems.SOUL_ELEMENTS.get())
                .requires(ModItems.SOUL_DIMENSIONS.get())
                .requires(ModItems.SOUL_SPACE.get())
                .requires(ModItems.SOUL_TIME.get())
                .requires(ModItems.SOUL_BALANCE.get())
                .requires(ModItems.ESSENCE_INFINITY.get())
                .unlockedBy("has_essence_infinity", has(ModItems.ESSENCE_INFINITY.get()))
                .save(consumer);

        soulCrossBuilder(consumer,
                ModItems.SOUL_ELEMENTS.get(),
                ModItems.ESSENCE_SEA.get(),
                ModItems.ESSENCE_FLAMES.get(),
                ModItems.ESSENCE_TUNDRA.get(),
                ModItems.ESSENCE_STONE.get());

        soulCrossBuilder(consumer,
                ModItems.SOUL_BALANCE.get(),
                ModItems.ESSENCE_BLESSED.get(),
                ModItems.ESSENCE_HAVOC.get(),
                ModItems.ESSENCE_LIGHT.get(),
                ModItems.ESSENCE_DARKNESS.get());

        soulCrossBuilder(consumer,
                ModItems.SOUL_TIME.get(),
                ModItems.ESSENCE_LIFE.get(),
                ModItems.ESSENCE_DEATH.get(),
                ModItems.ESSENCE_NIGHT.get(),
                ModItems.ESSENCE_DAY.get());

        soulCrossBuilder(consumer,
                ModItems.SOUL_SPACE.get(),
                ModItems.ESSENCE_PLANETS.get(),
                ModItems.ESSENCE_STARS.get(),
                ModItems.ESSENCE_ENERGY.get(),
                ModItems.ESSENCE_VOID.get());

        soulCrossBuilder(consumer,
                ModItems.SOUL_DIMENSIONS.get(),
                ModItems.ESSENCE_OVERWORLD.get(),
                ModItems.ESSENCE_NETHER.get(),
                ModItems.ESSENCE_END.get(),
                ModItems.ESSENCE_CONVERGENCE.get());
    }

    protected void buildAllEssences(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
            .shaped(ModItems.ESSENCE_BLESSED.get(), 1)
            .define('#', Items.POPPY)
            .define('Q', Items.QUARTZ)
            .define('T', Items.DIAMOND)
            .pattern("QTQ")
            .pattern("T#T")
            .pattern("QTQ")
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(consumer);

        ShapedRecipeBuilder
            .shaped(ModItems.ESSENCE_DARKNESS.get(), 1)
            .define('#', Items.SCULK)
            .define('E', Items.ENDER_EYE)
            .define('Z', Items.ECHO_SHARD)
            .pattern(" # ")
            .pattern("EZE")
            .pattern(" # ")
                .unlockedBy("has_air", has(Items.ECHO_SHARD))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_DAY.get(), 1)
                .define('s', Items.SUNFLOWER)
                .define('d', ModItems.DIVINE_ALLOY.get())
                .pattern(" d ")
                .pattern("dsd")
                .pattern(" d ")
                .unlockedBy("has_air", has(ModItems.DIVINE_ALLOY.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.ESSENCE_DEATH.get(), 1)
                .requires(Items.WITHER_ROSE)
                .requires(Items.WITHER_SKELETON_SKULL)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.GHAST_TEAR)
                .requires(Items.BONE)
                .requires(ModItems.SOUL_DUST.get(), 4)
                .unlockedBy("has_air", has(Items.GHAST_TEAR))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_HAVOC.get(), 1)
                .define('#', Items.BLAZE_POWDER)
                .define('T', Items.TNT)
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('E', Items.END_CRYSTAL)
                .pattern("#T#")
                .pattern("WEW")
                .pattern("#T#")
                .unlockedBy("has_air", has(Items.BLAZE_POWDER))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_OVERWORLD.get(), 1)
                .define('B', ModItems.ESSENCE_SEA.get())
                .define('W', ModItems.ESSENCE_PLANETS.get())
                .define('H', ModItems.ESSENCE_LIFE.get())
                .define('w', ModItems.WARDEN_CORE.get())
                .define('g', ModItems.GUARDIAN_CORE.get())
                .pattern(" w ")
                .pattern("BWH")
                .pattern(" g ")
                .unlockedBy("has_air", has(ModItems.ESSENCE_PLANETS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_ENERGY.get(), 1)
                .define('#', Items.REDSTONE)
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('A', Items.AMETHYST_SHARD)
                .define('L', Items.LIGHTNING_ROD)
                .pattern("#P#")
                .pattern("ALA")
                .pattern("#P#")
                .unlockedBy("has_air", has(Items.AMETHYST_SHARD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_FLAMES.get(), 1)
                .define('/', Items.BLAZE_ROD)
                .define('N', Items.NETHERITE_SCRAP)
                .define('F', Items.FIRE_CHARGE)
                .define('L', Items.LAVA_BUCKET)
                .pattern("/N/")
                .pattern("FLF")
                .pattern("/N/")
                .unlockedBy("has_air", has(Items.NETHERITE_SCRAP))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_INFINITY.get(), 1)
                .define('#', Items.QUARTZ)
                .define('E', Items.ENCHANTED_GOLDEN_APPLE)
                .define('B', ModItems.SOUL_FRAGMENT.get())
                .pattern("#E#")
                .pattern("EBE")
                .pattern("#E#")
                .unlockedBy("has_air", has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_LIFE.get(), 1)
                .define('e', Items.GOLDEN_APPLE)
                .define('t', Items.TOTEM_OF_UNDYING)
                .define('s', Items.OAK_SAPLING)
                .pattern(" e ")
                .pattern("tst")
                .pattern(" e ")
                .unlockedBy("has_air", has(Items.TOTEM_OF_UNDYING))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_LIGHT.get(), 1)
                .define('/', Items.BLAZE_ROD)
                .define('B', Items.BLAZE_POWDER)
                .define('S', Items.SEA_LANTERN)
                .pattern(" / ")
                .pattern("BSB")
                .pattern(" / ")
                .unlockedBy("has_air", has(Items.BLAZE_ROD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_NETHER.get(), 1)
                .define('#', Items.CRYING_OBSIDIAN)
                .define('S', Items.SOUL_SAND)
                .define('O', Items.OBSIDIAN)
                .define('N', Items.NETHERRACK)
                .define('B', ModItems.ESSENCE_FLAMES.get())
                .pattern("#SO")
                .pattern("NBN")
                .pattern("OS#")
                .unlockedBy("has_air", has(Items.OBSIDIAN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_NIGHT.get(), 1)
                .define('p', Items.PHANTOM_MEMBRANE)
                .define('n', ModItems.NIGHTMARE_INGOT.get())
                .pattern(" p ")
                .pattern("pnp")
                .pattern(" p ")
                .unlockedBy("has_air", has(ModItems.NIGHTMARE_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_SEA.get(), 1)
                .define('#', Items.WATER_BUCKET)
                .define('T', Items.TRIDENT)
                .define('N', Items.NAUTILUS_SHELL)
                .define('H', ModItems.HEART_OF_THE_ABYSS.get())
                .define('S', Items.SCUTE)
                .define('i', Items.IRON_INGOT)
                .define('g', Items.GOLD_INGOT)
                .pattern("#TN")
                .pattern("HSi")
                .pattern("gig")
                .unlockedBy("has_air", has(ModItems.HEART_OF_THE_ABYSS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_STONE.get(), 1)
                .define('#', Items.COAL)
                .define('c', Items.COPPER_INGOT)
                .define('i', Items.IRON_INGOT)
                .define('D', Items.DIAMOND)
                .define('S', Items.STONE)
                .define('R', Items.REDSTONE)
                .define('E', Items.EMERALD)
                .define('g', Items.GOLD_INGOT)
                .define('L', Items.LAPIS_LAZULI)
                .pattern("#ci")
                .pattern("DSR")
                .pattern("EgL")
                .unlockedBy("has_air", has(Items.DIAMOND))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_TUNDRA.get(), 1)
                .define('#', Items.BLUE_ICE)
                .define('P', Items.POWDER_SNOW_BUCKET)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .pattern(" # ")
                .pattern("PbP")
                .pattern(" # ")
                .unlockedBy("has_air", has(ModItems.DIVINE_ALLOY.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_VOID.get(), 1)
                .define('#', Items.CRYING_OBSIDIAN)
                .define('S', Items.SHULKER_SHELL)
                .define('E', Items.ENDER_EYE)
                .define('G', Items.GLASS_BOTTLE)
                .pattern("#S#")
                .pattern("EGE")
                .pattern("#S#")
                .unlockedBy("has_air", has(Items.CRYING_OBSIDIAN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_PLANETS.get(), 1)
                .define('E', ModItems.ESSENCE_STONE.get())
                .define('C', Items.COBBLESTONE)
                .define('N', Items.NETHERITE_SCRAP)
                .pattern("NCN")
                .pattern("CEC")
                .pattern("NCN")
                .unlockedBy("has_air", has(ModItems.ESSENCE_STONE.get()))
                .save(consumer);

        UpgradeRecipeBuilder
                .smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(ModItems.ESSENCE_LIGHT.get()),
                        ModItems.ESSENCE_STARS.get())
                .unlocks("has_air", has(ModItems.ESSENCE_LIGHT.get()))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.ESSENCE_STARS.getId().getPath()));

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_CONVERGENCE.get(), 1)
                .define('N', ModItems.NECRIUM_CHUNK.get())
                .define('C', ModItems.COSMILITE_INGOT.get())
                .define('T', ModBlocks.COMPRESSED_TEKTITE.get().asItem())
                .pattern("TTT")
                .pattern("NCN")
                .pattern("TTT")
                .unlockedBy("has_air", has(ModItems.COSMILITE_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_END.get(), 1)
                .define('E', Items.DRAGON_EGG)
                .define('C', Items.END_CRYSTAL)
                .define('S', Items.END_STONE)
                .pattern("CSC")
                .pattern("SES")
                .pattern("CSC")
                .unlockedBy("has_air", has(Items.DRAGON_EGG))
                .save(consumer);
    }

    protected void soulCrossBuilder(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike item1, ItemLike item2, ItemLike item3, ItemLike item4) {
        ShapedRecipeBuilder
            .shaped(output, 1)
            .define('S', Ingredient.of(ModItems.SOUL_FRAGMENT.get()))
            .define('1', item1)
            .define('2', item2)
            .define('3', item3)
            .define('4', item4)
            .pattern(" 3 ")
            .pattern("1S2")
            .pattern(" 4 ")
            .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
            .save(consumer);
    }
}
