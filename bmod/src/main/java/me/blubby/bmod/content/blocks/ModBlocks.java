package me.blubby.bmod.content.blocks;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.content.blocks.custom.BurnablePillarBlock;
import me.blubby.bmod.content.item.ModItems;
import me.blubby.bmod.content.world.feature.tree.MiniYggdrasilTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
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

    public static final RegistryObject<Block> YGGDRASIL_LOG = registerBlock("yggdrasil_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> YGGDRASIL_WOOD = registerBlock("yggdrasil_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_YGGDRASIL_LOG = registerBlock("stripped_yggdrasil_log",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Block> STRIPPED_YGGDRASIL_WOOD = registerBlock("stripped_yggdrasil_wood",
            () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_MISC);

    public static final RegistryObject<Block> YGGDRASIL_PLANKS = registerBlock("yggdrasil_planks",
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
    public static final RegistryObject<Block> YGGDRASIL_LEAVES = registerBlock("yggdrasil_leaves",
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

    public static final RegistryObject<Block> MINI_YGGDRASIL_SAPLING = registerBlock("mini_yggdrasil_sapling",
            () -> new SaplingBlock(new MiniYggdrasilTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_MISC);


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
