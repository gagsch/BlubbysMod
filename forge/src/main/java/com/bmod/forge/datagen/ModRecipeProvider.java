package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.forge.datagen.workshop_recipe.WorkshopRecipeBuilder;
import com.bmod.registry.ModTags;
import com.bmod.registry.block.ModBlocks;
import com.bmod.util.WoodUtils;
import com.bmod.registry.item.ModItems;
import com.bmod.util.ItemUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.bmod.util.WoodUtils.*;
import static com.bmod.registry.item.custom.BlueprintItem.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer)
    {
        woodTypeBuilder(consumer, DREADWOOD);
        woodTypeBuilder(consumer, EBON);

        smallSmithingBuilder(consumer, Items.NETHERITE_SWORD, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_SWORD.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_PICKAXE, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_PICKAXE.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_AXE, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_AXE.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_SHOVEL, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_SHOVEL.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_HOE, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_HOE.get());

        smallSmithingBuilder(consumer, Items.NETHERITE_HELMET, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_HELMET.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_CHESTPLATE, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_CHESTPLATE.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_LEGGINGS, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_LEGGINGS.get());
        smallSmithingBuilder(consumer, Items.NETHERITE_BOOTS, ModItems.SHROOMITE_INGOT.get(), ModItems.SHROOMITE_BOOTS.get());

        swordBuilder(consumer, ModItems.DIVINE_SWORD.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        pickaxeBuilder(consumer, ModItems.DIVINE_PICKAXE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        axeBuilder(consumer, ModItems.DIVINE_AXE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        shovelBuilder(consumer, ModItems.DIVINE_SHOVEL.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);
        hoeBuilder(consumer, ModItems.DIVINE_HOE.get(), ModItems.DIVINE_ALLOY.get(), Items.BLAZE_ROD);

        ingotBlockBuilder(consumer, ModItems.DREADIUM_INGOT.get(), ModBlocks.DREADIUM_BLOCK.get());
        ingotBlockBuilder(consumer, ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get());
        ingotBlockBuilder(consumer, ModItems.SHROOMITE_INGOT.get(), ModBlocks.SHROOMITE_BLOCK.get());
        ingotBlockBuilder(consumer, ModItems.DIVINE_ALLOY.get(), ModBlocks.DIVINE_BLOCK.get());
        ingotBlockBuilder(consumer, ModItems.VOLCANIC_INGOT.get(), ModBlocks.VOLCANIC_BLOCK.get());

        WorkshopRecipeBuilder.recipe(ModItems.HASTY_CHISEL.get())
                .blueprint(ACCESSORY)
                .base(ModItems.HANDLE.get())
                .addition(Items.IRON_INGOT)
                .addition(Items.IRON_INGOT)
                .addition(Items.IRON_NUGGET)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.REAVER_FANG.get())
                .blueprint(GEAR)
                .base(ModItems.HANDLE.get())
                .addition(ModItems.BEHEMOTH_TOOTH.get())
                .addition(ModItems.NECRIUM_INGOT.get())
                .addition(ModItems.SOUL_FRAGMENT.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.VOLCANIC_SWORD.get())
                .blueprint(GEAR)
                .base(Items.NETHERITE_SWORD)
                .addition(ModItems.VOLCANIC_INGOT.get())
                .addition(ModItems.MOLTEN_SLAG.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.VOLCANIC_MACE.get())
                .blueprint(GEAR)
                .base(Items.NETHERITE_SWORD)
                .addition(ModItems.VOLCANIC_INGOT.get())
                .addition(Items.NETHERITE_INGOT)
                .addition(ModItems.MOLTEN_SLAG.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.NECROMANCY_STAFF.get())
                .blueprint(GEAR)
                .base(Items.SKELETON_SKULL)
                .addition(ModItems.NECRIUM_INGOT.get())
                .addition(ModItems.NECRIUM_INGOT.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.TIME_GEAR.get())
                .blueprint(RESOURCE)
                .base(Items.GOLD_BLOCK)
                .addition(ModItems.GUARDIAN_CORE.get())
                .addition(ModItems.DIVINE_ALLOY.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.VOID_BUNDLE.get())
                .blueprint(SPECIAL_ITEM)
                .base(Items.BUNDLE)
                .addition(Items.ENDER_CHEST)
                .addition(ModItems.DRAGON_HEART.get())
                .addition(Items.SHULKER_SHELL)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.TOTEM_OF_DREAMS.get())
                .blueprint(SPECIAL_ITEM)
                .base(Items.TOTEM_OF_UNDYING)
                .addition(ModItems.DIVINE_ALLOY.get())
                .addition(ModItems.DREADIUM_INGOT.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.LUCKY_ROCK.get())
                .blueprint(ACCESSORY)
                .base(Items.COBBLESTONE)
                .addition(ModItems.DIVINE_ALLOY.get())
                .addition(Items.EMERALD)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.SHROOMITE_INGOT.get())
                .blueprint(UPGRADE)
                .base(ModItems.DREADIUM_INGOT.get())
                .addition(ModBlocks.GLEAM_SHROOM.get().asItem())
                .addition(ModItems.VILE_BLOOD.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.VOLCANIC_INGOT.get())
                .blueprint(UPGRADE)
                .base(ModItems.DREADIUM_INGOT.get())
                .addition(Items.NETHERITE_SCRAP)
                .addition(ModItems.MOLTEN_SLAG.get())
                .addition(Items.MAGMA_CREAM)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.MYSTIC_EMBER.get())
                .blueprint(ACCESSORY)
                .base(Items.FIRE_CHARGE)
                .addition(Items.NETHERITE_SCRAP)
                .addition(ModItems.MOLTEN_SLAG.get())
                .addition(Items.CHARCOAL)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.LAVA_RING.get())
                .blueprint(ACCESSORY)
                .base(ModItems.IRON_RING.get())
                .addition(ModItems.VOLCANIC_INGOT.get())
                .addition(ModItems.RUBY.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.BAND_OF_REGENERATION.get())
                .blueprint(ACCESSORY)
                .base(ModItems.IRON_RING.get())
                .addition(ModItems.RUBY.get())
                .addition(ModItems.FAIRY_DUST.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.HEART_NECKLACE.get())
                .blueprint(ACCESSORY)
                .base(ModItems.CHAIN_NECKLACE.get())
                .addition(ModItems.RUBY.get())
                .addition(ModItems.VILE_BLOOD.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.DRAGON_HEART_NECKLACE.get())
                .blueprint(ACCESSORY)
                .base(ModItems.HEART_NECKLACE.get())
                .addition(ModItems.DRAGON_HEART.get())
                .addition(ModItems.DREADIUM_CHUNK.get())
                .addition(ModItems.SHROOMITE_INGOT.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.CURSED_GEM.get())
                .blueprint(SPECIAL_ITEM)
                .base(ModItems.RUBY.get())
                .addition(Items.CRYING_OBSIDIAN)
                .addition(ModItems.WARDEN_TENDRIL.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.VAMPIRE_GLOVES.get())
                .blueprint(ACCESSORY)
                .base(ModItems.LEATHER_GLOVES.get())
                .addition(Items.IRON_INGOT)
                .addition(ModItems.VILE_BLOOD.get())
                .addition(ModItems.WITHER_SPINE.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.WIND_ROCKET.get())
                .blueprint(SPECIAL_ITEM)
                .base(Items.FIREWORK_ROCKET)
                .addition(Items.PHANTOM_MEMBRANE)
                .addition(ModItems.WARDEN_TENDRIL.get())
                .addition(ModItems.WITHER_SPINE.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.LUCKY_CHISEL.get())
                .blueprint(ACCESSORY)
                .base(ModItems.HASTY_CHISEL.get())
                .addition(ModItems.LUCKY_ROCK.get())
                .addition(Items.NETHERITE_INGOT)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.ETERNAL_SATCHEL.get())
                .blueprint(ACCESSORY)
                .base(Items.BUNDLE)
                .addition(ModItems.WITHER_SPINE.get())
                .addition(ModItems.FAIRY_DUST.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.CHRONOS_CLOCK.get())
                .blueprint(SPECIAL_ITEM)
                .base(Items.CLOCK)
                .addition(ModItems.TIME_GEAR.get())
                .addition(ModItems.GUARDIAN_CORE.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(Items.DRAGON_EGG)
                .blueprint(RESOURCE)
                .base(ModItems.DRAGON_HEART.get())
                .addition(Items.END_CRYSTAL)
                .addition(Items.SHULKER_SHELL)
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.CHRONOS_STOPWATCH.get())
                .blueprint(SPECIAL_ITEM)
                .base(Items.CLOCK)
                .addition(ModItems.TIME_GEAR.get())
                .addition(Items.CHAIN)
                .addition(ModItems.GUARDIAN_CORE.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.DEMON_GLOVES.get())
                .blueprint(ACCESSORY)
                .base(ModItems.VAMPIRE_GLOVES.get())
                .addition(ModItems.MYSTIC_MOLTEN_RING.get())
                .addition(ModItems.VILE_BLOOD.get())
                .addition(ModItems.NECRIUM_INGOT.get())
                .save(consumer);

        WorkshopRecipeBuilder.recipe(ModItems.BUBBLE_WAND.get())
                .blueprint(SPECIAL_ITEM)
                .base(ModItems.HANDLE.get())
                .addition(Items.WATER_BUCKET)
                .addition(ModItems.HEART_OF_THE_ABYSS.get())
                .save(consumer);

        smallSmithingBuilder(consumer, ModItems.LAVA_RING.get(), ModItems.MYSTIC_EMBER.get(), ModItems.MYSTIC_MOLTEN_RING.get());

        ShapedRecipeBuilder.shaped(ModBlocks.DIMENSION_GATEWAY.get(), 1)
                .define('O', Blocks.OBSIDIAN)
                .define('g', Blocks.TINTED_GLASS)
                .pattern("OOO")
                .pattern("g g")
                .pattern("OOO")
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.IRON_RING.get())
                .define('i', Items.IRON_NUGGET)
                .define('I', Items.IRON_INGOT)
                .pattern("iIi")
                .pattern("I I")
                .pattern("iIi")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.LEATHER_GLOVES.get())
                .define('l', Items.LEATHER)
                .define('s', ModItems.LEATHER_SCRAP.get())
                .pattern("lsl")
                .pattern("lll")
                .pattern("sss")
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.CHAIN_NECKLACE.get())
                .define('c', Items.CHAIN)
                .define('i', Items.IRON_NUGGET)
                .pattern("cic")
                .pattern("c c")
                .pattern(" c ")
                .unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
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

        ShapedRecipeBuilder.shaped(ModItems.UNDERWATER_REDSTONE.get())
                .define('r', Items.REDSTONE)
                .define('A', Items.AMETHYST_SHARD)
                .pattern("rrr")
                .pattern("rAr")
                .pattern("rrr")
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.FROG_EXECUTOR_BLOCK.get(), 6)
                .define('f', Items.FROGSPAWN)
                .define('I', Items.IRON_INGOT)
                .define('P', ModBlocks.PIXEL_BLOCK.get())
                .define('r', Items.REDSTONE)
                .pattern("fff")
                .pattern("fPf")
                .pattern("IrI")
                .unlockedBy("has_pixel", has(ModBlocks.PIXEL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.PIXEL_BLOCK.get(), 6)
                .define('t', Items.TINTED_GLASS)
                .define('R', Items.RED_DYE)
                .define('G', Items.GREEN_DYE)
                .define('B', Items.BLUE_DYE)
                .pattern("ttt")
                .pattern("RGB")
                .pattern("ttt")
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.EYEDROPPER.get())
                .define('g', Items.GLASS)
                .define('P', ModBlocks.PIXEL_BLOCK.get())
                .pattern(" g ")
                .pattern("gPg")
                .pattern("gg ")
                .unlockedBy("has_pixel", has(ModBlocks.PIXEL_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.PAINT_BRUSH.get())
                .define('s', Items.STICK)
                .define('n', Items.IRON_NUGGET)
                .define('W', Items.WHEAT)
                .pattern("  s")
                .pattern("ns ")
                .pattern("Wn ")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.HANDLE.get(),1)
                .requires(Items.STICK)
                .requires(Items.SLIME_BALL)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.WORKSHOP.get().asItem())
                .define('S', Items.SMITHING_TABLE)
                .define('p', Items.STICK)
                .define('r', ModItems.RUBY.get())
                .pattern("rSr")
                .pattern("p p")
                .pattern("p p")
                .unlockedBy("has_smithing_table", has(Items.SMITHING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.HEART_OF_THE_ABYSS.get())
                .define('a', Items.HEART_OF_THE_SEA)
                .define('b', Items.GOLD_INGOT)
                .define('c', Items.KELP)
                .define('d', Items.PRISMARINE_SHARD)
                .pattern("bbb")
                .pattern("cac")
                .pattern("ddd")
                .unlockedBy("has_heart_of_the_sea", has(Items.HEART_OF_THE_SEA))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SOUL_FRAGMENT.get(), 2)
                .define('d', ModItems.SOUL_DUST.get())
                .define('B', ModTags.BOSS_DROPS)
                .pattern(" d ")
                .pattern("dBd")
                .pattern(" d ")
                .unlockedBy("has_soul_dust", has(ModItems.SOUL_DUST.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModItems.DREADIUM_CHUNK.get()),
                        ModItems.DREADIUM_INGOT.get(),
                        5,
                        300)
                .unlockedBy("has_dreadium_chunk", has(ModItems.DREADIUM_CHUNK.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModItems.NECRIUM_CHUNK.get()),
                        ModItems.NECRIUM_INGOT.get(),
                        5,
                        300)
                .unlockedBy("has_necrium_chunk", has(ModItems.NECRIUM_CHUNK.get()))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModTags.RUBY_ORES),
                        ModItems.RUBY.get(),
                        5,
                        300)
                .unlockedBy("has_ruby_ores", has(ModTags.RUBY_ORES))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModTags.NECRIUM_ORES),
                        ModItems.NECRIUM_CHUNK.get(),
                        5,
                        300)
                .unlockedBy("has_necrium_ores", has(ModTags.NECRIUM_ORES))
                .save(consumer);

        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(ModTags.DREADIUM_ORES),
                        ModItems.DREADIUM_CHUNK.get(),
                        5,
                        300)
                .unlockedBy("has_dreadium_ores", has(ModTags.DREADIUM_ORES))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIVINE_ALLOY.get(), 2)
                .define('D', Items.DIAMOND)
                .define('Q', Items.QUARTZ)
                .define('R', ModItems.RUBY.get())
                .define('s', ModItems.SOUL_FRAGMENT.get())
                .pattern("DRD")
                .pattern("QsQ")
                .pattern("DRD")
                .unlockedBy("has_soul_fragment", has(ModItems.RUBY.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIVINE_HELMET.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_HELMET)
                .define('g', Items.GOLD_INGOT)
                .define('#', Items.FEATHER)
                .pattern("#g#")
                .pattern("bDb")
                .unlockedBy("has_diamond", has(Items.DIAMOND_HELMET))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIVINE_CHESTPLATE.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_CHESTPLATE)
                .define('g', Items.GOLD_INGOT)
                .pattern("b b")
                .pattern("bDb")
                .pattern("gbg")
                .unlockedBy("has_diamond", has(Items.DIAMOND_CHESTPLATE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIVINE_LEGGINGS.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_LEGGINGS)
                .define('g', Items.GOLD_INGOT)
                .pattern("bDb")
                .pattern("g g")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_LEGGINGS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.DIVINE_BOOTS.get(), 1)
                .define('b', ModItems.DIVINE_ALLOY.get())
                .define('D', Items.DIAMOND_BOOTS)
                .define('g', Items.GOLD_INGOT)
                .pattern("gDg")
                .pattern("b b")
                .unlockedBy("has_diamond", has(Items.DIAMOND_BOOTS))
                .save(consumer);
    }

    protected void swordBuilder(Consumer<FinishedRecipe> consumer, ItemLike sword, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder.shaped(sword, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("B")
                .pattern("B")
                .pattern("h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void pickaxeBuilder(Consumer<FinishedRecipe> consumer, ItemLike pickaxe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder.shaped(pickaxe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BBB")
                .pattern(" h ")
                .pattern(" h ")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void axeBuilder(Consumer<FinishedRecipe> consumer, ItemLike axe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder.shaped(axe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BB")
                .pattern("Bh")
                .pattern(" h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void shovelBuilder(Consumer<FinishedRecipe> consumer, ItemLike shovel, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder.shaped(shovel, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("B")
                .pattern("h")
                .pattern("h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void ingotBlockBuilder(Consumer<FinishedRecipe> consumer, Item ingot, Block ingotBlock) {
        ShapelessRecipeBuilder.shapeless(ingotBlock, 1)
                .requires(ingot, 9)
                .unlockedBy("has_ingot", has(ingot))
                .save(consumer, new ResourceLocation(BlubbysMod.MOD_ID, ItemUtils.getIdFromItem(ingot) + "_from_ingot"));

        ShapelessRecipeBuilder.shapeless(ingot, 9)
                .requires(ingotBlock, 1)
                .unlockedBy("has_ingot", has(ingot))
                .save(consumer, new ResourceLocation(BlubbysMod.MOD_ID, ItemUtils.getIdFromItem(ingot) + "_from_block"));
    }

    protected void hoeBuilder(Consumer<FinishedRecipe> consumer, ItemLike hoe, ItemLike mainMaterial, ItemLike handleMaterial) {
        ShapedRecipeBuilder.shaped(hoe, 1)
                .define('h', handleMaterial)
                .define('B', mainMaterial)
                .pattern("BB")
                .pattern(" h")
                .pattern(" h")
                .unlockedBy("has_main_material", has(mainMaterial))
                .save(consumer);
    }

    protected void smallSmithingBuilder(Consumer<FinishedRecipe> consumer, Item input, Item ingredient, Item output)
    {
        UpgradeRecipeBuilder
                .smithing(
                        Ingredient.of(input),
                        Ingredient.of(ingredient),
                        output)
                .unlocks("has_" + ItemUtils.getIdFromItem(output), has(ingredient))
                .save(consumer, new ResourceLocation(BlubbysMod.MOD_ID, ItemUtils.getIdFromItem(output)));
    }

    protected void woodTypeBuilder(Consumer<FinishedRecipe> consumer, String woodName) {
        ItemLike log = log(woodName).get();
        ItemLike wood = wood(woodName).get();
        ItemLike strippedLog = strippedLog(woodName).get();
        ItemLike strippedWood = strippedWood(woodName).get();

        planksFromLogs(consumer, planks(woodName).get(), WoodUtils.registerLogItemTag(woodName));

        ShapedRecipeBuilder.shaped(wood, 3)
                .define('L', log)
                .pattern("LL")
                .pattern("LL")
                .unlockedBy("has_log", has(log))
                .save(consumer);

        ShapedRecipeBuilder.shaped(strippedWood, 3)
                .define('L', strippedLog)
                .pattern("LL")
                .pattern("LL")
                .unlockedBy("has_stripped_log", has(strippedLog))
                .save(consumer);
    }
}
