package me.blubby.bmod.common.blocks;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.custom.BubbleBlock;
import me.blubby.bmod.common.blocks.custom.HotPepperCropBlock;
import me.blubby.bmod.common.item.ModCreativeTab;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

    public static final RegistryObject<Block>
            TEKTITE = registerBlock("tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK))),
            TEKTITE_GRASS = registerBlock("overgrown_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM))),
            TEKTITE_SNOW = registerBlock("snow_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK))),
            TEKTITE_NECRO = registerBlock("necrotic_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM))),
            COMPRESSED_TEKTITE = registerBlock("compressed_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN))),

            COSMILITE_ORE = registerBlock("cosmilite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN))),
            NECRIUM_ORE = registerBlock("necrium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN))),

            FOSSILIZED_BONE_BLOCK = registerBlock("fossilized_bone_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK))),
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
