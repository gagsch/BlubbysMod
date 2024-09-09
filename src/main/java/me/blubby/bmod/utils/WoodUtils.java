package me.blubby.bmod.utils;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.blocks.custom.BurnablePillarBlock;
import me.blubby.bmod.common.blocks.custom.CustomSapling;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WoodUtils {
    private static final Map<String, RegistryObject<Block>> registeredBlocks = new HashMap<>();

    public static final String COSMIC_OAK = "cosmic_oak";
    public static final String EBON = "ebon";

    public static void registerWood(String woodName, AbstractTreeGrower treeGrower, CreativeModeTab tab)
    {
        RegistryObject<Block> log = registerBlock(woodName + "_log",
                () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), tab);

        RegistryObject<Block> wood = registerBlock(woodName + "_wood",
                () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), tab);

        RegistryObject<Block> strippedLog = registerBlock("stripped_" + woodName + "_log",
                () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), tab);

        RegistryObject<Block> strippedWood = registerBlock("stripped_" + woodName + "_wood",
                () -> new BurnablePillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), tab);

        RegistryObject<Block> planks = registerBlock(woodName + "_planks",
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

        RegistryObject<Block> leaves = registerBlock(woodName + "_leaves",
                () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).requiresCorrectToolForDrops()){
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

        RegistryObject<Block> sapling = registerBlock(woodName + "_sapling",
                () -> new CustomSapling(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), tab);
    }

    public static RegistryObject<Block> getBlockRegistry(String blockName) {
        return registeredBlocks.get(blockName);
    }

    public static RegistryObject<Block> log(String wood) {
        return getBlockRegistry(wood + "_log");
    }

    public static RegistryObject<Block> wood(String wood) {
        return getBlockRegistry(wood + "_wood");
    }

    public static RegistryObject<Block> strippedLog(String wood) {
        return getBlockRegistry("stripped_" + wood + "_log");
    }

    public static RegistryObject<Block> strippedWood(String wood) {
        return getBlockRegistry("stripped_" + wood + "_wood");
    }

    public static RegistryObject<Block> planks(String wood) {
        return getBlockRegistry(wood + "_planks");
    }

    public static RegistryObject<Block> leaves(String wood) {
        return getBlockRegistry(wood + "_leaves");
    }

    public static RegistryObject<Block> sapling(String wood) {
        return getBlockRegistry(wood + "_sapling");
    }

    public static TagKey<Item> registerLogItemTag(String wood) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Blubby_sModOfDoom.MOD_ID, wood + "_logs"));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = ModBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        registeredBlocks.put(name, (RegistryObject<Block>) toReturn);

        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block, CreativeModeTab tab)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}