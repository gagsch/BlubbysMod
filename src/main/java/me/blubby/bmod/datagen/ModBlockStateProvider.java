package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
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

        axisBlockWithItem(ModBlocks.COSMIC_OAK_LOG, blockLoc(ModBlocks.COSMIC_OAK_LOG), blockLoc(ModBlocks.COSMIC_OAK_LOG, "top"));
        axisBlockWithItem(ModBlocks.COSMIC_OAK_WOOD, blockLoc(ModBlocks.COSMIC_OAK_LOG), blockLoc(ModBlocks.COSMIC_OAK_LOG));
        axisBlockWithItem(ModBlocks.STRIPPED_COSMIC_OAK_LOG, blockLoc(ModBlocks.STRIPPED_COSMIC_OAK_LOG), blockLoc(ModBlocks.STRIPPED_COSMIC_OAK_LOG, "top"));
        axisBlockWithItem(ModBlocks.STRIPPED_COSMIC_OAK_WOOD, blockLoc(ModBlocks.STRIPPED_COSMIC_OAK_LOG), blockLoc(ModBlocks.STRIPPED_COSMIC_OAK_LOG));
        blockWithItem(ModBlocks.COSMIC_OAK_LEAVES);
        blockWithItem(ModBlocks.COSMIC_OAK_PLANKS);
        saplingBlock(ModBlocks.COSMIC_OAK_SAPLING);

        axisBlockWithItem(ModBlocks.EBON_LOG, blockLoc(ModBlocks.EBON_LOG), blockLoc(ModBlocks.EBON_LOG, "top"));
        axisBlockWithItem(ModBlocks.EBON_WOOD, blockLoc(ModBlocks.EBON_LOG), blockLoc(ModBlocks.EBON_LOG));
        axisBlockWithItem(ModBlocks.STRIPPED_EBON_LOG, blockLoc(ModBlocks.STRIPPED_EBON_LOG), blockLoc(ModBlocks.STRIPPED_EBON_LOG, "top"));
        axisBlockWithItem(ModBlocks.STRIPPED_EBON_WOOD, blockLoc(ModBlocks.STRIPPED_EBON_LOG), blockLoc(ModBlocks.STRIPPED_EBON_LOG));
        blockWithItem(ModBlocks.EBON_LEAVES);
        blockWithItem(ModBlocks.EBON_PLANKS);
        saplingBlock(ModBlocks.EBON_SAPLING);
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
