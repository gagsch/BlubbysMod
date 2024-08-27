package me.blubby.bmod.datagen;

import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Blubby_sModOfDoom.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.TEKTITE, BlockType.cubeAll);
        blockWithItem(ModBlocks.COMPRESSED_TEKTITE, BlockType.cubeAll);
        blockWithItem(ModBlocks.COSMILITE_ORE, BlockType.cubeAll);

        blockWithItem(ModBlocks.COSMIC_OAK_LOG, BlockType.pillarBlock);
        blockWithItem(ModBlocks.COSMIC_OAK_LEAVES, BlockType.cubeAll);
        blockWithItem(ModBlocks.COSMIC_OAK_PLANKS, BlockType.cubeAll);
        blockWithItem(ModBlocks.STRIPPED_COSMIC_OAK_LOG, BlockType.pillarBlock);

        blockWithItem(ModBlocks.EBON_LOG, BlockType.pillarBlock);
        blockWithItem(ModBlocks.EBON_LEAVES, BlockType.cubeAll);
        blockWithItem(ModBlocks.EBON_PLANKS, BlockType.cubeAll);
        blockWithItem(ModBlocks.STRIPPED_EBON_LOG, BlockType.pillarBlock);
    }

    public enum BlockType {
        cubeAll,
        pillarBlock
    };

    private void blockWithItem(RegistryObject<Block> blockRegistryObject, BlockType blockType)
    {
        switch (blockType)
        {
            case cubeAll:
                simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
            case pillarBlock:
                if (blockRegistryObject.get() instanceof RotatedPillarBlock rotatedPillarBlock)
                {
                    axisBlock(rotatedPillarBlock, blockLoc(blockRegistryObject), blockLoc(blockRegistryObject, "top"));
                }
        }
        simpleBlockItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
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
