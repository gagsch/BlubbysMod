package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;

import static com.bmod.util.WoodUtils.*;

import com.bmod.registry.block.custom.HotPepperCropBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BlubbysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        crossBlock(ModBlocks.GLEAM_SHROOM.get());
        blockWithItem(ModBlocks.GLEAM_SHROOM_BLOCK.get());
        blockWithItem(ModBlocks.ROT_BLOCK.get());
        axisBlockWithItem(ModBlocks.FOSSILIZED_BONE_BLOCK.get(), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK, "top"));
        bottomTopBlockWithItem(ModBlocks.DARK_TURF_BLOCK.get(), blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.DARK_TURF_BLOCK, "top"), blockLoc(ModBlocks.DARK_TURF_BLOCK));
        bottomTopBlockWithItem(ModBlocks.NECROTIC_GRASS_BLOCK.get(), blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK, "top"), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK));
        blockWithItem(ModBlocks.DARK_SOIL.get());
        blockWithItem(ModBlocks.DEEPERSLATE.get());
        bottomTopBlockWithItem(ModBlocks.MYCELIUM_DEEPERSLATE.get(), blockLoc(ModBlocks.DEEPERSLATE), blockLoc(ModBlocks.MYCELIUM_DEEPERSLATE, "top"), blockLoc(ModBlocks.MYCELIUM_DEEPERSLATE));
        blockWithItem(ModBlocks.WEB_STONE.get());
        blockWithItem(ModBlocks.SILK_BLOCK.get());
        bottomTopBlockWithItem(ModBlocks.SLUDGE_BLOCK.get(), blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK));

        woodType(DREADWOOD);
        woodType(EBON);

        translucentBlockWithItem(ModBlocks.BUBBLE_BLOCK.get());
        cropBlockWithAges(ModBlocks.HOT_PEPPER_CROP.get(), 7, age -> new ResourceLocation(BlubbysMod.MOD_ID, "block/hot_pepper_stage" + age));

        blockWithItem(ModBlocks.NECRIUM_ORE.get());
        blockWithItem(ModBlocks.DEEPERSLATE_NECRIUM_ORE.get());
        blockWithItem(ModBlocks.RUBY_ORE.get());
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE.get());
        blockWithItem(ModBlocks.DREADIUM_ORE.get());
        blockWithItem(ModBlocks.DEEPERSLATE_DREADIUM_ORE.get());

        blockWithItem(ModBlocks.DREADIUM_BLOCK.get());
        blockWithItem(ModBlocks.RUBY_BLOCK.get());
        blockWithItem(ModBlocks.SHROOMITE_BLOCK.get());
        blockWithItem(ModBlocks.DIVINE_BLOCK.get());
        blockWithItem(ModBlocks.VOLCANIC_BLOCK.get());
    }

    private void woodType(String wood)
    {
        axisBlockWithItem(log(wood).get(), blockLoc(log(wood)), blockLoc(log(wood), "top"));
        axisBlockWithItem(wood(wood).get(), blockLoc(log(wood)), blockLoc(log(wood)));
        axisBlockWithItem(strippedLog(wood).get(), blockLoc(strippedLog(wood)), blockLoc(strippedLog(wood), "top"));
        axisBlockWithItem(strippedWood(wood).get(), blockLoc(strippedLog(wood)), blockLoc(strippedLog(wood)));
        blockWithItem(leaves(wood).get());
        blockWithItem(planks(wood).get());
        crossBlock(sapling(wood).get());
        stairsBlock(stairs(wood).get(), blockLoc(planks(wood)));
        slabBlock(slab(wood).get(), blockLoc(planks(wood)), blockLoc(planks(wood)));
        fenceBlock(fence(wood).get(), blockLoc(planks(wood)));
        fenceGateBlock(gate(wood).get(), blockLoc(planks(wood)));
        doorBlockWithRenderType(door(wood).get(), blockLoc(door(wood), "bottom"), blockLoc(door(wood), "top"), "cutout");
        trapdoorBlockWithRenderType(trapdoor(wood).get(), blockLoc(trapdoor(wood)), true, "cutout");
        pressurePlateBlock(pressurePlate(wood).get(), blockLoc(planks(wood)));
        buttonBlock(button(wood).get(), blockLoc(planks(wood)));

        models().buttonInventory(getName(button(wood)) + "_inventory", blockLoc(planks(wood)));
        models().fenceInventory(getName(fence(wood)) + "_inventory", blockLoc(planks(wood)));
    }

    private void blockWithItem(Block block)
    {
        simpleBlock(block, cubeAll(block));
        simpleBlockItem(block, cubeAll(block));
        ModItemModelProvider.registeredItems.add(getName(block));
    }

    private void translucentBlockWithItem(Block block)
    {
        simpleBlock(block,
                models().cubeAll(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), blockTexture(block)).renderType("translucent"));
        simpleBlockItem(block,
                models().cubeAll(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), blockTexture(block)).renderType("translucent"));
        ModItemModelProvider.registeredItems.add(getName(block));
    }

    private void bottomTopBlockWithItem(Block block, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture)
    {
        simpleBlock(block, models().cubeBottomTop(getName(block), sideTexture, bottomTexture, topTexture));
        simpleBlockItem(block, models().cubeBottomTop(getName(block), sideTexture, bottomTexture, topTexture));
        ModItemModelProvider.registeredItems.add(getName(block));
    }

    private void axisBlockWithItem(Block block, ResourceLocation sideTexture, ResourceLocation endTexture)
    {
        if (block instanceof RotatedPillarBlock rotatedPillarBlock)
            axisBlock(rotatedPillarBlock, sideTexture, endTexture);

        simpleBlockItem(block, models().withExistingParent(getName(block), "minecraft:block/cube_column"));
        ModItemModelProvider.registeredItems.add(getName(block));
    }

    private void northFacingBlockWithItem(Supplier<Block> block, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation frontTexture)
    {
        simpleBlock(block.get(), models().cube(getName(block.get()), bottomTexture, topTexture, frontTexture, sideTexture, sideTexture, sideTexture).texture("particle", topTexture));
        simpleBlockItem(block.get(), models().cube(getName(block.get()), bottomTexture, topTexture, frontTexture, sideTexture, sideTexture, sideTexture));
        ModItemModelProvider.registeredItems.add(getName(block));
    }

    private void crossBlock(Block block) {
        simpleBlock(block,
                models().cross(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), blockTexture(block)).renderType("cutout"));
    }

    private void cropBlockWithAges(Block block, int maxAge, Function<Integer, ResourceLocation> ageTextureLocationFunction) {
        for (int age = 0; age <= maxAge; age++) {
            models().withExistingParent(getName(block) + "_stage" + age, "minecraft:block/crop")
                    .texture("crop", ageTextureLocationFunction.apply(age))
                    .renderType("cutout");
        }

        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(HotPepperCropBlock.AGE);
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(new ResourceLocation(BlubbysMod.MOD_ID, "block/" + getName(block) + "_stage" + age)))
                    .build();
        });
    }

    public String getName(Supplier<? extends Block> block) {
        return block.get().builtInRegistryHolder().key().location().getPath();
    }

    public String getName(Block block) {
        return block.builtInRegistryHolder().key().location().getPath();
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "block/" + getName(block));
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block, String suffix) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "block/" + getName(block) + "_" + suffix);
    }
}
