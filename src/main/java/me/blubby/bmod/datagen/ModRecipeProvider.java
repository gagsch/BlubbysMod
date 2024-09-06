package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.blocks.custom.ModWood;
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
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static me.blubby.bmod.common.blocks.custom.ModWood.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
    {
        buildAllSouls(consumer);
        buildAllEssences(consumer);

        woodTypeBuilder(consumer, COSMIC_OAK);
        woodTypeBuilder(consumer, EBON);

        ShapedRecipeBuilder
                .shaped(ModItems.TOTEM_OF_DREAMS.get())
                .define('b', ModItems.DIVINE_INGOT.get())
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

        upgradeArmorTypeBuilder(consumer, ModArmorMaterial.DIVINE, ModArmorMaterial.COSMILITE, ModItems.COSMILITE_INGOT.get());
        upgradeArmorTypeBuilder(consumer, ModArmorMaterial.DIVINE, ModArmorMaterial.NIGHTMARE, ModItems.NIGHTMARE_INGOT.get());

        SimpleCookingRecipeBuilder
                .smoking(Ingredient.of(Items.SOUL_SAND),
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
                .shaped(ModItems.NIGHTMARE_INGOT.get())
                .define('#', ModItems.ECHOING_SOUL_DUST.get())
                .define('n', Items.NETHERITE_INGOT)
                .pattern("###")
                .pattern("#n#")
                .pattern("###")
                .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(consumer);

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

        ShapedRecipeBuilder
                .shaped(ModItems.ECHOING_SOUL_DUST.get(), 4)
                .define('#', ModItems.SOUL_DUST.get())
                .define('E', Items.ECHO_SHARD)
                .pattern("###")
                .pattern("#E#")
                .pattern("###")
                .unlockedBy("has_echo_shard", has(Items.ECHO_SHARD))
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

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModItems.DIVINE_ALLOY.get()),
                        ModItems.DIVINE_INGOT.get(),
                        2,
                        200)
                .unlockedBy("has_blessed_alloy", has(ModItems.DIVINE_ALLOY.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_HELMET.get(), 1)
                .define('b', ModItems.DIVINE_INGOT.get())
                .define('D', Items.DIAMOND_HELMET)
                .define('g', Items.GOLD_INGOT)
                .define('#', Items.FEATHER)
                .pattern("#g#")
                .pattern("bDb")
                .unlockedBy("has_diamond", has(Items.DIAMOND_HELMET))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_CHESTPLATE.get(), 1)
                .define('b', ModItems.DIVINE_INGOT.get())
                .define('D', Items.DIAMOND_CHESTPLATE)
                .define('g', Items.GOLD_INGOT)
                .pattern("b b")
                .pattern("bDb")
                .pattern("gbg")
                .unlockedBy("has_diamond", has(Items.DIAMOND_CHESTPLATE))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_LEGGINGS.get(), 1)
                .define('b', ModItems.DIVINE_INGOT.get())
                .define('D', Items.DIAMOND_LEGGINGS)
                .define('g', Items.GOLD_INGOT)
                .pattern("bDb")
                .pattern("g g")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_LEGGINGS))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.DIVINE_BOOTS.get(), 1)
                .define('b', ModItems.DIVINE_INGOT.get())
                .define('D', Items.DIAMOND_BOOTS)
                .define('g', Items.GOLD_INGOT)
                .pattern("gDg")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_BOOTS))
                .save(consumer);
    }

    protected void upgradeArmorTypeBuilder(Consumer<FinishedRecipe> consumer, ArmorMaterial inputArmorMaterial, ArmorMaterial outputArmorMaterial, ItemLike upgradeIngot) {
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
                        Ingredient.of(upgradeIngot),
                        outputHelmet)
                .unlocks("has_ingot", has(upgradeIngot))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_helmet"));

        UpgradeRecipeBuilder
                .smithing(
                        inputChest,
                        Ingredient.of(upgradeIngot),
                        outputChest)
                .unlocks("has_ingot", has(upgradeIngot))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_chestplate"));

        UpgradeRecipeBuilder
                .smithing(
                        inputLeggings,
                        Ingredient.of(upgradeIngot),
                        outputLeggings)
                .unlocks("has_ingot", has(upgradeIngot))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_leggings"));

        UpgradeRecipeBuilder
                .smithing(
                        inputBoots,
                        Ingredient.of(upgradeIngot),
                        outputBoots)
                .unlocks("has_ingot", has(upgradeIngot))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, outputArmorMaterial.getName() + "_boots"));
    }

    protected void woodTypeBuilder(Consumer<FinishedRecipe> consumer, String woodName) {
        ItemLike log = log(woodName).get();
        ItemLike wood = wood(woodName).get();
        ItemLike strippedLog = strippedLog(woodName).get();
        ItemLike strippedWood = strippedWood(woodName).get();

        planksFromLogs(consumer, planks(woodName).get(), ModWood.registerLogItemTag(woodName));

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
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        soulCrossBuilder(ModItems.SOUL_ELEMENTS.get(),
                ModItems.ESSENCE_SEA.get(),
                ModItems.ESSENCE_FLAMES.get(),
                ModItems.ESSENCE_TUNDRA.get(),
                ModItems.ESSENCE_STONE.get())
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        soulCrossBuilder(ModItems.SOUL_BALANCE.get(),
                ModItems.ESSENCE_BLESSED.get(),
                ModItems.ESSENCE_DOOM.get(),
                ModItems.ESSENCE_LIGHT.get(),
                ModItems.ESSENCE_DARKNESS.get())
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        soulCrossBuilder(ModItems.SOUL_TIME.get(),
                ModItems.ESSENCE_LIFE.get(),
                ModItems.ESSENCE_DEATH.get(),
                ModItems.ESSENCE_NIGHT.get(),
                ModItems.ESSENCE_DAY.get())
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        soulCrossBuilder(ModItems.SOUL_SPACE.get(),
                ModItems.ESSENCE_PLANETS.get(),
                ModItems.ESSENCE_STARS.get(),
                ModItems.ESSENCE_ENERGY.get(),
                ModItems.ESSENCE_VOID.get())
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);

        soulCrossBuilder(ModItems.SOUL_DIMENSIONS.get(),
                ModItems.ESSENCE_OVERWORLD.get(),
                ModItems.ESSENCE_NETHER.get(),
                ModItems.ESSENCE_END.get(),
                ModItems.ESSENCE_CONVERGENCE.get())
                .unlockedBy("has_soul_fragment", has(ModItems.SOUL_FRAGMENT.get()))
                .save(consumer);
    }

    protected void buildAllEssences(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
            .shaped(ModItems.ESSENCE_BLESSED.get(), 1)
            .define('#', Items.POPPY)
            .define('E', Items.EMERALD)
            .define('T', Items.TOTEM_OF_UNDYING)
            .define('D', Items.DANDELION)
            .pattern("#ED")
            .pattern("ETE")
            .pattern("DE#")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder
            .shaped(ModItems.ESSENCE_DARKNESS.get(), 1)
            .define('#', Items.SCULK)
            .define('E', Items.ENDER_EYE)
            .define('Z', Items.ECHO_SHARD)
            .pattern(" # ")
            .pattern("EZE")
            .pattern(" # ")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_DAY.get(), 1)
                .define('s', Items.SUNFLOWER)
                .define('d', ModItems.DIVINE_INGOT.get())
                .pattern(" d ")
                .pattern("dsd")
                .pattern(" d ")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.ESSENCE_DEATH.get(), 1)
                .requires(Items.WITHER_ROSE)
                .requires(Items.WITHER_SKELETON_SKULL)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.GHAST_TEAR)
                .requires(Items.BONE)
                .requires(ModItems.SOUL_DUST.get(), 4)
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_DOOM.get(), 1)
                .define('#', Items.BLAZE_POWDER)
                .define('T', Items.TNT)
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('E', Items.END_CRYSTAL)
                .pattern("#T#")
                .pattern("WEW")
                .pattern("#T#")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(ModItems.ESSENCE_OVERWORLD.get(), 1)
                .define('B', ModItems.ESSENCE_SEA.get())
                .define('W', ModItems.ESSENCE_PLANETS.get())
                .define('H', ModItems.ESSENCE_LIFE.get())
                .pattern("BWH")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_ENERGY.get(), 1)
                .define('#', Items.REDSTONE)
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('A', Items.AMETHYST_SHARD)
                .define('L', Items.LIGHTNING_ROD)
                .pattern("#P#")
                .pattern("ALA")
                .pattern("#P#")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_FLAMES.get(), 1)
                .define('/', Items.BLAZE_ROD)
                .define('N', Items.NETHERITE_SCRAP)
                .define('F', Items.FIRE_CHARGE)
                .define('L', Items.LAVA_BUCKET)
                .pattern("/N/")
                .pattern("FLF")
                .pattern("/N/")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_INFINITY.get(), 1)
                .define('#', Items.QUARTZ)
                .define('E', Items.ENCHANTED_GOLDEN_APPLE)
                .define('B', ModItems.GUARDIAN_CORE.get())
                .pattern("#E#")
                .pattern("EBE")
                .pattern("#E#")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_LIFE.get(), 1)
                .define('e', Items.ENCHANTED_GOLDEN_APPLE)
                .define('t', Items.TOTEM_OF_UNDYING)
                .define('s', Items.OAK_SAPLING)
                .pattern(" e ")
                .pattern("tst")
                .pattern(" e ")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_LIGHT.get(), 1)
                .define('/', Items.BLAZE_ROD)
                .define('B', Items.BLAZE_POWDER)
                .define('S', Items.SEA_LANTERN)
                .pattern(" / ")
                .pattern("BSB")
                .pattern(" / ")
                .unlockedBy("has_air", has(Items.AIR))
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
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_NIGHT.get(), 1)
                .define('p', Items.PHANTOM_MEMBRANE)
                .define('n', ModItems.NIGHTMARE_INGOT.get())
                .pattern(" p ")
                .pattern("pnp")
                .pattern(" p ")
                .unlockedBy("has_air", has(Items.AIR))
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
                .unlockedBy("has_air", has(Items.AIR))
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
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_TUNDRA.get(), 1)
                .define('#', Items.BLUE_ICE)
                .define('P', Items.POWDER_SNOW_BUCKET)
                .define('b', ModItems.DIVINE_INGOT.get())
                .pattern(" # ")
                .pattern("PbP")
                .pattern(" # ")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_VOID.get(), 1)
                .define('#', Items.CRYING_OBSIDIAN)
                .define('S', Items.SHULKER_SHELL)
                .define('E', Items.ENDER_EYE)
                .define('G', Items.GLASS_BOTTLE)
                .pattern("#S#")
                .pattern("EGE")
                .pattern("#S#")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_PLANETS.get(), 1)
                .define('E', ModItems.ESSENCE_STONE.get())
                .define('C', Items.COBBLESTONE)
                .define('N', Items.NETHERITE_SCRAP)
                .pattern("NCN")
                .pattern("CEC")
                .pattern("NCN")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        UpgradeRecipeBuilder
                .smithing(
                        Ingredient.of(Items.NETHER_STAR),
                        Ingredient.of(ModItems.ESSENCE_LIGHT.get()),
                        ModItems.ESSENCE_STARS.get())
                .unlocks("has_air", has(Items.AIR))
                .save(consumer, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, ModItems.ESSENCE_STARS.getId().getPath()));

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_CONVERGENCE.get(), 1)
                .define('N', ModItems.NECRIUM_CHUNK.get())
                .define('C', ModItems.COSMILITE_INGOT.get())
                .define('T', ModBlocks.COMPRESSED_TEKTITE.get().asItem())
                .pattern("TTT")
                .pattern("NCN")
                .pattern("TTT")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.ESSENCE_END.get(), 1)
                .define('E', Items.ENDER_EYE)
                .define('C', Items.END_CRYSTAL)
                .define('S', Items.END_STONE)
                .pattern("CSC")
                .pattern("SES")
                .pattern("CSC")
                .unlockedBy("has_air", has(Items.AIR))
                .save(consumer);
    }

    protected static RecipeBuilder soulCrossBuilder(ItemLike output, ItemLike item1, ItemLike item2, ItemLike item3, ItemLike item4) {
        return ShapedRecipeBuilder
                .shaped(output, 1)
                .define('S', Ingredient.of(ModItems.SOUL_FRAGMENT.get()))
                .define('1', item1)
                .define('2', item2)
                .define('3', item3)
                .define('4', item4)
                .pattern(" 3 ")
                .pattern("1S2")
                .pattern(" 4 ");
    }
}