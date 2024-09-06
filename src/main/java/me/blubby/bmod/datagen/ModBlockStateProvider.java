package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import static me.blubby.bmod.common.blocks.custom.ModWood.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
        blockWithItem(ModBlocks.TEKTITE);
        blockWithItem(ModBlocks.COMPRESSED_TEKTITE);
        bottomTopBlockWithItem(ModBlocks.TEKTITE_GRASS, blockLoc(ModBlocks.TEKTITE), blockLoc(ModBlocks.TEKTITE_GRASS, "top"), blockLoc(ModBlocks.TEKTITE_GRASS));
        bottomTopBlockWithItem(ModBlocks.TEKTITE_NECRO, blockLoc(ModBlocks.TEKTITE), blockLoc(ModBlocks.TEKTITE_NECRO, "top"), blockLoc(ModBlocks.TEKTITE_NECRO));
        bottomTopBlockWithItem(ModBlocks.TEKTITE_SNOW, blockLoc(ModBlocks.TEKTITE), blockLoc(ModBlocks.TEKTITE_SNOW, "top"), blockLoc(ModBlocks.TEKTITE_SNOW));

        blockWithItem(ModBlocks.COSMILITE_ORE);
        blockWithItem(ModBlocks.NECRIUM_ORE);

        axisBlockWithItem(ModBlocks.FOSSILIZED_BONE_BLOCK, blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK), blockLoc(ModBlocks.FOSSILIZED_BONE_BLOCK, "top"));
        blockWithItem(ModBlocks.DEAD_TISSUE_BLOCK);

        axisBlockWithItem(log(COSMIC_OAK), blockLoc(log(COSMIC_OAK)), blockLoc(log(COSMIC_OAK), "top"));
        axisBlockWithItem(wood(COSMIC_OAK), blockLoc(log(COSMIC_OAK)), blockLoc(log(COSMIC_OAK)));
        axisBlockWithItem(strippedLog(COSMIC_OAK), blockLoc(strippedLog(COSMIC_OAK)), blockLoc(strippedLog(COSMIC_OAK), "top"));
        axisBlockWithItem(strippedWood(COSMIC_OAK), blockLoc(strippedLog(COSMIC_OAK)), blockLoc(strippedLog(COSMIC_OAK)));
        blockWithItem(leaves(COSMIC_OAK));
        blockWithItem(planks(COSMIC_OAK));
        saplingBlock(sapling(COSMIC_OAK));

        axisBlockWithItem(log(EBON), blockLoc(log(EBON)), blockLoc(log(EBON), "top"));
        axisBlockWithItem(wood(EBON), blockLoc(log(EBON)), blockLoc(log(EBON)));
        axisBlockWithItem(strippedLog(EBON), blockLoc(strippedLog(EBON)), blockLoc(strippedLog(EBON), "top"));
        axisBlockWithItem(strippedWood(EBON), blockLoc(strippedLog(EBON)), blockLoc(strippedLog(EBON)));
        blockWithItem(leaves(EBON));
        blockWithItem(planks(EBON));
        saplingBlock(sapling(EBON));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
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

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public String getName(Supplier<? extends Block> block) {
        return block.get().builtInRegistryHolder().key().location().getPath();
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/" + getName(block));
    }

    public ResourceLocation blockLoc(Supplier<? extends Block> block, String suffix) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "block/" + getName(block) + "_" + suffix);
    }
}
