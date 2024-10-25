package com.bmod.util;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.custom.CustomSapling;
import com.bmod.registry.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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
        registerBlock(woodName + "_log", true,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

        registerBlock(woodName + "_wood", true,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

        registerBlock("stripped_" + woodName + "_log", true,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

        registerBlock("stripped_" + woodName + "_wood", true,
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

        Supplier<Block> plank = registerBlock(woodName + "_planks", true,
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

        registerBlock(woodName + "_leaves", true,
                () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).requiresCorrectToolForDrops()) {
                    protected boolean decaying(@NotNull BlockState state) {
                        return state.getValue(DISTANCE) == 20;
                    }
                });

        registerBlock(woodName + "_sapling", true,
                () -> new CustomSapling(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

        registerBlock(woodName + "_door", true,
                () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));

        registerBlock(woodName + "_trapdoor", true,
                () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));

        registerBlock(woodName + "_stairs", true,
                () -> new StairBlock(plank.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

        registerBlock(woodName + "_slab", true,
                () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

        registerBlock(woodName + "_fence", true,
                () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

        registerBlock(woodName + "_fence_gate", true,
                () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));

        registerBlock(woodName + "_button", true,
                () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));

        registerBlock(woodName + "_pressure_plate", true,
                () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    }

    public static Supplier<?> getBlockRegistry(String blockName) {
        return ModBlocks.BLOCK_HASH_MAP.get(blockName);
    }

    @SuppressWarnings("unchecked")
    public static Supplier<RotatedPillarBlock> log(String wood) {
        return (Supplier<RotatedPillarBlock>) getBlockRegistry(wood + "_log");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<RotatedPillarBlock> wood(String wood) {
        return (Supplier<RotatedPillarBlock>) getBlockRegistry(wood + "_wood");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<RotatedPillarBlock> strippedLog(String wood) {
        return (Supplier<RotatedPillarBlock>) getBlockRegistry("stripped_" + wood + "_log");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<RotatedPillarBlock> strippedWood(String wood) {
        return (Supplier<RotatedPillarBlock>) getBlockRegistry("stripped_" + wood + "_wood");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<Block> planks(String wood) {
        return (Supplier<Block>) getBlockRegistry(wood + "_planks");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<LeavesBlock> leaves(String wood) {
        return (Supplier<LeavesBlock>) getBlockRegistry(wood + "_leaves");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<SaplingBlock> sapling(String wood) {
        return (Supplier<SaplingBlock>) getBlockRegistry(wood + "_sapling");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<DoorBlock> door(String wood) {
        return (Supplier<DoorBlock>) getBlockRegistry(wood + "_door");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<TrapDoorBlock> trapdoor(String wood) {
        return (Supplier<TrapDoorBlock>) getBlockRegistry(wood + "_trapdoor");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<StairBlock> stairs(String wood) {
        return (Supplier<StairBlock>) getBlockRegistry(wood + "_stairs");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<SlabBlock> slab(String wood) {
        return (Supplier<SlabBlock>) getBlockRegistry(wood + "_slab");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<FenceBlock> fence(String wood) {
        return (Supplier<FenceBlock>) getBlockRegistry(wood + "_fence");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<FenceGateBlock> gate(String wood) {
        return (Supplier<FenceGateBlock>) getBlockRegistry(wood + "_fence_gate");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<ButtonBlock> button(String wood) {
        return (Supplier<ButtonBlock>) getBlockRegistry(wood + "_button");
    }

    @SuppressWarnings("unchecked")
    public static Supplier<PressurePlateBlock> pressurePlate(String wood) {
        return (Supplier<PressurePlateBlock>) getBlockRegistry(wood + "_pressure_plate");
    }

    public static TagKey<Item> registerLogItemTag(String wood) { return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(BlubbysMod.MOD_ID, wood + "_logs")); }
}