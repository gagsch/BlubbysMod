package me.blubby.bmod.core.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.block.ModBlocks;
import static me.blubby.bmod.core.util.WoodUtils.*;

import me.blubby.bmod.common.block.custom.HotPepperCropBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    ExistingFileHelper existingFileHelper;
    DataGenerator dataGen;

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Blubby_sModOfDoom.MOD_ID, exFileHelper);

        this.dataGen = gen;
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels()
    {
        bottomTopBlockWithItem(ModBlocks.SLUDGE_BLOCK, blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK, "top"), blockLoc(ModBlocks.SLUDGE_BLOCK));

        bottomTopBlockWithItem(ModBlocks.NECROTIC_GRASS_BLOCK, blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK, "top"), blockLoc(ModBlocks.NECROTIC_GRASS_BLOCK));
        bottomTopBlockWithItem(ModBlocks.DARK_TURF_BLOCK, blockLoc(ModBlocks.DARK_SOIL), blockLoc(ModBlocks.DARK_TURF_BLOCK, "top"), blockLoc(ModBlocks.DARK_TURF_BLOCK));
        blockWithItem(ModBlocks.DARK_SOIL);

        blockWithItem(ModBlocks.DEEPERSLATE);

        blockWithItem(ModBlocks.SCARLITE_ORE);
        blockWithItem(ModBlocks.NECRIUM_ORE);

        axisBlockWithItem(ModBlocks.FOSSILIZED_BONE_BLOCK, blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK, "top"));
        blockWithItem(ModBlocks.ROT_BLOCK);

        translucentBlockWithItem(ModBlocks.BUBBLE_BLOCK);
        cropBlockWithAges(ModBlocks.HOT_PEPPER_CROP, 6, age -> new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/hot_pepper_stage" + age));

        crossBlock(ModBlocks.GLEAM_SHROOM);

        woodType(DREADWOOD);
        woodType(EBON);
    }

    private void woodType(String wood)
    {
        axisBlockWithItem(log(wood), blockLoc(log(wood)), blockLoc(log(wood), "top"));
        axisBlockWithItem(wood(wood), blockLoc(log(wood)), blockLoc(log(wood)));
        axisBlockWithItem(strippedLog(wood), blockLoc(strippedLog(wood)), blockLoc(strippedLog(wood), "top"));
        axisBlockWithItem(strippedWood(wood), blockLoc(strippedLog(wood)), blockLoc(strippedLog(wood)));
        blockWithItem(leaves(wood));
        blockWithItem(planks(wood));
        crossBlock(sapling(wood));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void translucentBlockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("translucent"));
        simpleBlockItem(blockRegistryObject.get(),
                models().cubeAll(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("translucent"));
    }

    private void bottomTopBlockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture)
    {
        simpleBlock(blockRegistryObject.get(), cubeBottomTop(blockRegistryObject, bottomTexture, topTexture, sideTexture));
        simpleBlockItem(blockRegistryObject.get(), cubeBottomTop(blockRegistryObject, bottomTexture, topTexture, sideTexture));
    }

    private void axisBlockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation sideTexture, ResourceLocation endTexture)
    {
        if (blockRegistryObject.get() instanceof RotatedPillarBlock rotatedPillarBlock)
            axisBlock(rotatedPillarBlock, sideTexture, endTexture);

        simpleBlockItem(blockRegistryObject.get(), models().withExistingParent(getName(blockRegistryObject), "minecraft:block/cube_column"));
    }

    private ModelFile cubeBottomTop(RegistryObject<Block> blockRegistryObject, ResourceLocation bottomTexture, ResourceLocation topTexture, ResourceLocation sideTexture)
    {
        return models().cubeBottomTop(getName(blockRegistryObject), sideTexture, bottomTexture, topTexture);
    }

    private void crossBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void cropBlockWithAges(RegistryObject<Block> blockRegistryObject, int maxAge, Function<Integer, ResourceLocation> ageTextureLocationFunction) {
        Block block = blockRegistryObject.get();

        for (int age = 0; age <= maxAge; age++) {
            models().withExistingParent(getName(blockRegistryObject) + "_stage" + age, "minecraft:block/crop")
                    .texture("crop", ageTextureLocationFunction.apply(age))
                    .renderType("cutout");
        }

        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(HotPepperCropBlock.AGE);
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/" + getName(blockRegistryObject) + "_stage" + age)))
                    .build();
        });
    }

    public String getName(Supplier<? extends Block> block) {
        return block.get().builtInRegistryHolder().key().location().getPath();
    }

    public ResourceLocation blockLoc(Block block) {
        return new ResourceLocation("block/" + block.builtInRegistryHolder().key().location().getPath());
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/" + getName(block));
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block, String suffix) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/" + getName(block) + "_" + suffix);
    }
}
