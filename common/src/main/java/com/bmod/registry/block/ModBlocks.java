package com.bmod.registry.block;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.custom.*;
import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.world.feature.ModTreeFeatures;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final Map<String, Supplier<Block>> BLOCK_HASH_MAP = new HashMap<>();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final Supplier<Block> SLUDGE_BLOCK = registerBlock("sludge_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)));

    public static final Supplier<Block> NECROTIC_GRASS_BLOCK = registerBlock("necrotic_grass_block", true,
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final Supplier<Block> DARK_TURF_BLOCK = registerBlock("dark_turf_block", true,
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final Supplier<Block> DARK_SOIL = registerBlock("dark_soil", true,
            () -> new DarkSoilBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final Supplier<Block> GLEAM_SHROOM = registerBlock("gleam_shroom", true,
            () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.WITHER_ROSE)
                    .instabreak()
                    .noCollission()
                    .noOcclusion()
                    .lightLevel((light) -> 7), () -> ModTreeFeatures.HUGE_GLEAM_SHROOM));

    public static final Supplier<Block> GLEAM_SHROOM_BLOCK = registerBlock("gleam_shroom_block", true,
            () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
                    .lightLevel((light) -> 7)));

    public static final Supplier<Block> DEEPERSLATE = registerBlock("deeperslate", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));

    public static final Supplier<Block> MYCELIUM_DEEPERSLATE = registerBlock("mycelium_deeperslate", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops()
                    .lightLevel((light) -> 2)));

    public static final Supplier<Block> WEB_STONE = registerBlock("web_stone", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));

    public static final Supplier<Block> SILK_BLOCK = registerBlock("silk_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).sound(SoundType.HONEY_BLOCK).requiresCorrectToolForDrops()));

    public static final Supplier<Block> RUBY_ORE = registerBlock("ruby_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));

    public static final Supplier<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final Supplier<Block> DREADIUM_ORE = registerBlock("dreadium_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final Supplier<Block> DEEPERSLATE_DREADIUM_ORE = registerBlock("deeperslate_dreadium_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));

    public static final Supplier<Block> NECRIUM_ORE = registerBlock("necrium_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops()));

    public static final Supplier<Block> DEEPERSLATE_NECRIUM_ORE = registerBlock("deeperslate_necrium_ore", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));

    public static final Supplier<Block> FOSSILIZED_BONE_BLOCK = registerBlock("fossilized_bone_block", true,
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).requiresCorrectToolForDrops()));

    public static final Supplier<Block> ROT_BLOCK = registerBlock("rot_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD)));

    public static final Supplier<Block> BUBBLE_BLOCK = registerBlock("bubble_block", false,
            () -> new BubbleBlock(BlockBehaviour.Properties.copy(Blocks.HONEY_BLOCK).noCollission().strength(0.1f).sound(SoundType.WET_GRASS)));

    public static final Supplier<Block> HOT_PEPPER_CROP = registerBlock("hot_pepper", false,
            () -> new HotPepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final Supplier<Block> WORKSHOP = registerBlock("workshop", true,
            () -> new WorkshopBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final Supplier<Block> DIMENSION_GATEWAY = registerBlock("dimension_gateway", true,
            DimensionGatewayBlock::new);

    public static final Supplier<Block> DREADIUM_BLOCK = registerBlock("dreadium_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> RUBY_BLOCK = registerBlock("ruby_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> SHROOMITE_BLOCK = registerBlock("shroomite_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> DIVINE_BLOCK = registerBlock("divine_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final Supplier<Block> VOLCANIC_BLOCK = registerBlock("volcanic_block", true,
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).lightLevel((light) -> 4)));

    public static final Supplier<Block> UNDERWATER_REDSTONE_WIRE = registerBlock("underwater_redstone_wire", false,
            UnderwaterRedStoneWireBlock::new);

    public static final Supplier<Block> PIXEL_BLOCK = registerBlock("pixel_block", true,
            PixelBlock::new);

    @SuppressWarnings("unchecked")
    public static <T extends Block> Supplier<T> registerBlock(String name, boolean createItem, Supplier<T> block) {
        Supplier<T> toReturn = BLOCKS.register(name, block);

        BLOCK_HASH_MAP.put(name, (Supplier<Block>) toReturn);

        if(createItem) ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(ModCreativeTab.BLUBBYS_MOD)));
        return toReturn;
    }
}
