package me.blubby.bmod.common.block;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.block.custom.BubbleBlock;
import me.blubby.bmod.common.block.custom.DarkGrassBlock;
import me.blubby.bmod.common.block.custom.HotPepperCropBlock;
import me.blubby.bmod.common.item.ModCreativeTab;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.common.world.feature.configured.TreeConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Blubby_sModOfDoom.MOD_ID);

    public static final TagKey<Block> grassBlocks = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "grass_blocks"));

    public static final RegistryObject<Block>

    SLUDGE_BLOCK = registerBlock("sludge_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK))),

    NECROTIC_GRASS_BLOCK = registerBlock("necrotic_grass_block",
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.PODZOL))),

    DARK_TURF_BLOCK = registerBlock("dark_turf_block",
            () -> new DarkGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK))),

    DARK_SOIL = registerBlock("dark_soil",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT))),

    GLEAM_SHROOM = registerBlock("gleam_shroom",
            () -> new MushroomBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((light) -> {
                return 8;
            }), () -> {
                return TreeConfiguredFeatures.DREADWOOD.getHolder().get();
            })),

    DEEPERSLATE = registerBlock("deeperslate",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops())),

    SCARLITE_ORE = registerBlock("scarlite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops())),

    NECRIUM_ORE = registerBlock("necrium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).requiresCorrectToolForDrops())),

    FOSSILIZED_BONE_BLOCK = registerBlock("fossilized_bone_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).requiresCorrectToolForDrops())),

    ROT_BLOCK = registerBlock("rot_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MUD)){
                        @Override
                        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return true;
                        }

                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return 5;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return 20;
                        }
                    }),

    BUBBLE_BLOCK = BLOCKS.register("bubble_block",
            () -> new BubbleBlock(BlockBehaviour.Properties.of(Material.STONE).noCollission().strength(0.1f).sound(SoundType.WET_GRASS))),

    HOT_PEPPER_CROP = BLOCKS.register("hot_pepper",
            () -> new HotPepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
