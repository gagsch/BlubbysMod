package com.bmod.util;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.CustomSapling;
import com.bmod.registry.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static com.bmod.registry.block.ModBlocks.registerBlock;

public class WoodUtils {
    public static final String DREADWOOD = "dreadwood";
    public static final String EBON = "ebon";

    public static void registerWood(String woodName, AbstractTreeGrower treeGrower)
    {
        Supplier<Block> log = registerBlock(woodName + "_log", true,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LOG))),

                wood = registerBlock(woodName + "_wood", true,
                        () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_WOOD))),

                strippedLog = registerBlock("stripped_" + woodName + "_log", true,
                        () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_LOG))),

                strippedWood = registerBlock("stripped_" + woodName + "_wood", true,
                        () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_WOOD))),

                planks = registerBlock(woodName + "_planks", true,
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS))),

                leaves = registerBlock(woodName + "_leaves", true,
                        () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).requiresCorrectToolForDrops()){
                            protected boolean decaying(@NotNull BlockState state) {
                                return state.getValue(DISTANCE) == 20;
                            }
                        }),

                sapling = registerBlock(woodName + "_sapling", true,
                        () -> new CustomSapling(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    }

    public static Supplier<Block> getBlockRegistry(String blockName) {
        return ModBlocks.BLOCK_HASH_MAP.get(blockName);
    }

    public static Supplier<Block> log(String wood) {
        return getBlockRegistry(wood + "_log");
    }

    public static Supplier<Block> wood(String wood) { return getBlockRegistry(wood + "_wood"); }

    public static Supplier<Block> strippedLog(String wood) { return getBlockRegistry("stripped_" + wood + "_log"); }

    public static Supplier<Block> strippedWood(String wood) { return getBlockRegistry("stripped_" + wood + "_wood"); }

    public static Supplier<Block> planks(String wood) {
        return getBlockRegistry(wood + "_planks");
    }

    public static Supplier<Block> leaves(String wood) {
        return getBlockRegistry(wood + "_leaves");
    }

    public static Supplier<Block> sapling(String wood) {
        return getBlockRegistry(wood + "_sapling");
    }

    public static TagKey<Item> registerLogItemTag(String wood) { return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, wood + "_logs")); }
}