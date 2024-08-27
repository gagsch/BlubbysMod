package me.blubby.bmod.common.blocks;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.custom.BurnablePillarBlock;
import me.blubby.bmod.common.blocks.custom.CustomSapling;
import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.server.world.feature.tree.CosmicOakTreeGrower;
import me.blubby.bmod.server.world.feature.tree.EbonTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Blubby_sModOfDoom.MOD_ID);

    public static final RegistryObject<Block> TEKTITE = registerBlock("tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> TEKTITE_GRASS = registerBlock("overgrown_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> TEKTITE_SNOW = registerBlock("snow_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> TEKTITE_NECRO = registerBlock("necrotic_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_NYLIUM)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> COMPRESSED_TEKTITE = registerBlock("compressed_tektite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> COSMILITE_ORE = registerBlock("cosmilite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> COSMIC_OAK_LOG = registerBlock("cosmic_oak_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> COSMIC_OAK_WOOD = registerBlock("cosmic_oak_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_COSMIC_OAK_LOG = registerBlock("stripped_cosmic_oak_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_COSMIC_OAK_WOOD = registerBlock("stripped_cosmic_oak_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> COSMIC_OAK_PLANKS = registerBlock("cosmic_oak_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
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
            }, CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> COSMIC_OAK_LEAVES = registerBlock("cosmic_oak_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .requiresCorrectToolForDrops()) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> COSMIC_OAK_SAPLING = registerBlock("cosmic_oak_sapling",
            () -> new CustomSapling(new CosmicOakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_MISC);


    public static final RegistryObject<Block> EBON_LOG = registerBlock("ebon_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> EBON_WOOD = registerBlock("ebon_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_EBON_LOG = registerBlock("stripped_ebon_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_EBON_WOOD = registerBlock("stripped_ebon_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> EBON_PLANKS = registerBlock("ebon_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
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
            }, CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> EBON_LEAVES = registerBlock("ebon_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .requiresCorrectToolForDrops()) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }
            }, CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> EBON_SAPLING = registerBlock("ebon_sapling",
            () -> new CustomSapling(new EbonTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> COSMIC_GRASS = registerBlock("cosmic_grass",
            () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)), CreativeModeTab.TAB_MISC);

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
