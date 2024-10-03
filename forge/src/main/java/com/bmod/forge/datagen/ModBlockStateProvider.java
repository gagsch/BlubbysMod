package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;

import static com.bmod.util.WoodUtils.*;

import com.bmod.registry.block.HotPepperCropBlock;
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
    ExistingFileHelper existingFileHelper;
    DataGenerator dataGen;

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, BlubbysMod.MOD_ID, exFileHelper);

        this.dataGen = gen;
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels()
    {
        crossBlock(ModBlocks.GLEAM_SHROOM.get());
        blockWithItem(ModBlocks.ROT_BLOCK.get());
        axisBlockWithItem(ModBlocks.FOSSILIZED_BONE_BLOCK.get(), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK, "top"));
        bottomTopBlockWithItem(ModBlocks.DARK_TURF_BLOCK.get(), blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.DARK_TURF_BLOCK, "top"), blockLoc(ModBlocks.DARK_TURF_BLOCK));
        bottomTopBlockWithItem(ModBlocks.NECROTIC_GRASS_BLOCK.get(), blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK, "top"), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK));
        blockWithItem(ModBlocks.DARK_SOIL.get());
        blockWithItem(ModBlocks.DEEPERSLATE.get());
        blockWithItem(ModBlocks.WEB_STONE.get());
        blockWithItem(ModBlocks.SILK_BLOCK.get());
        bottomTopBlockWithItem(ModBlocks.SLUDGE_BLOCK.get(), blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK));

        woodType(DREADWOOD);
        woodType(EBON);

        translucentBlockWithItem(ModBlocks.BUBBLE_BLOCK.get());
        cropBlockWithAges(ModBlocks.HOT_PEPPER_CROP.get(), 7, age -> new ResourceLocation(BlubbysMod.MOD_ID, "block/hot_pepper_stage" + age));
        northFacingBlockWithItem(ModBlocks.ENRICHMENT_TABLE,
                blockLoc(ModBlocks.ENRICHMENT_TABLE, "bottom"),
                blockLoc(ModBlocks.ENRICHMENT_TABLE, "top"),
                blockLoc(ModBlocks.ENRICHMENT_TABLE, "side"),
                blockLoc(ModBlocks.ENRICHMENT_TABLE, "front"));

        blockWithItem(ModBlocks.NECRIUM_ORE.get());
        blockWithItem(ModBlocks.DEEPERSLATE_NECRIUM_ORE.get());
        blockWithItem(ModBlocks.RUBY_ORE.get());
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE.get());
        blockWithItem(ModBlocks.DREADIUM_ORE.get());
        blockWithItem(ModBlocks.DEEPERSLATE_DREADIUM_ORE.get());
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
    }

    private void blockWithItem(Block block)
    {
        simpleBlock(block, cubeAll(block));
        simpleBlockItem(block, cubeAll(block));
    }

    private void translucentBlockWithItem(Block block)
    {
        simpleBlock(block,
                models().cubeAll(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), blockTexture(block)).renderType("translucent"));
        simpleBlockItem(block,
                models().cubeAll(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath(), blockTexture(block)).renderType("translucent"));
    }

    private void bottomTopBlockWithItem(Block block, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture)
    {
        simpleBlock(block, models().cubeBottomTop(getName(block), sideTexture, bottomTexture, topTexture));
        simpleBlockItem(block, models().cubeBottomTop(getName(block), sideTexture, bottomTexture, topTexture));
    }

    private void axisBlockWithItem(Block block, ResourceLocation sideTexture, ResourceLocation endTexture)
    {
        if (block instanceof RotatedPillarBlock rotatedPillarBlock)
            axisBlock(rotatedPillarBlock, sideTexture, endTexture);

        simpleBlockItem(block, models().withExistingParent(getName(block), "minecraft:block/cube_column"));
    }

    private void northFacingBlockWithItem(Supplier<Block> block, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture, ResourceLocation frontTexture)
    {
        simpleBlock(block.get(), models().cube(getName(block.get()), bottomTexture, topTexture, frontTexture, sideTexture, sideTexture, sideTexture).texture("particle", topTexture));
        simpleBlockItem(block.get(), models().cube(getName(block.get()), bottomTexture, topTexture, frontTexture, sideTexture, sideTexture, sideTexture));
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
