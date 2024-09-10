package me.blubby.bmod.common.blocks;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.custom.BubbleBlock;
import me.blubby.bmod.common.blocks.custom.HotPepperCropBlock;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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

    public static final RegistryObject<Block>
            TEKTITE = registerBlock("tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)), CreativeModeTab.TAB_MISC),
            TEKTITE_GRASS = registerBlock("overgrown_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)), CreativeModeTab.TAB_MISC),
            TEKTITE_SNOW = registerBlock("snow_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_MISC),
            TEKTITE_NECRO = registerBlock("necrotic_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)), CreativeModeTab.TAB_MISC),
            COMPRESSED_TEKTITE = registerBlock("compressed_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_MISC),

            COSMILITE_ORE = registerBlock("cosmilite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_MISC),
            NECRIUM_ORE = registerBlock("necrium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_MISC),

            FOSSILIZED_BONE_BLOCK = registerBlock("fossilized_bone_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK)), CreativeModeTab.TAB_MISC),
            DEAD_TISSUE_BLOCK = registerBlock("dead_tissue_block",
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
            }, CreativeModeTab.TAB_MISC),

            BUBBLE_BLOCK = BLOCKS.register("bubble_block",
            () -> new BubbleBlock(BlockBehaviour.Properties.of(Material.STONE).noCollission().noOcclusion().strength(0.1f).sound(SoundType.WET_GRASS))),

            HOT_PEPPER_CROP = BLOCKS.register("hot_pepper",
            () -> new HotPepperCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block, CreativeModeTab tab)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
