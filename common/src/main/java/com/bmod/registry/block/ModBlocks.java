package com.bmod.registry.block;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.world.feature.ModTreeFeatures;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final Map<String, Supplier<Block>> BLOCK_HASH_MAP = new HashMap<>();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final TagKey<Block> grassBlocks = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, "grass_blocks"));

    public static final Supplier<Block> SLUDGE_BLOCK = registerBlock("sludge_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK))),

    NECROTIC_GRASS_BLOCK = registerBlock("necrotic_grass_block", true,
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL))),

    DARK_TURF_BLOCK = registerBlock("dark_turf_block", true,
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK))),

    DARK_SOIL = registerBlock("dark_soil", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT))),

    GLEAM_SHROOM = registerBlock("gleam_shroom", true,
            () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((light) -> {
                return 8;
            }), () -> {
                return ModTreeFeatures.DREADWOOD;
            })),

    DEEPERSLATE = registerBlock("deeperslate", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops())),

    SCARLITE_ORE = registerBlock("scarlite_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops())),

    NECRIUM_ORE = registerBlock("necrium_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops())),

    FOSSILIZED_BONE_BLOCK = registerBlock("fossilized_bone_block", true,
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).requiresCorrectToolForDrops())),

    ROT_BLOCK = registerBlock("rot_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD))),

    BUBBLE_BLOCK = registerBlock("bubble_block", false,
            () -> new BubbleBlock(BlockBehaviour.Properties.of(Material.STONE).noCollission().strength(0.1f).sound(SoundType.WET_GRASS))),

    HOT_PEPPER_CROP = registerBlock("hot_pepper", false,
            () -> new HotPepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static <T extends Block> Supplier<T> registerBlock(String name, boolean createItem, Supplier<T> block) {
        Supplier<T> toReturn = BLOCKS.register(name, block);

        BLOCK_HASH_MAP.put(name, (Supplier<Block>) toReturn);

        if(createItem) ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)));
        return toReturn;
    }
}
