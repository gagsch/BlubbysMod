package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.bmod.util.WoodUtils.*;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator arg, ExistingFileHelper existingFileHelper) {
        super(arg, BlubbysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.PLANKS).add(planks(DREADWOOD).get(), planks(EBON).get());
        tag(BlockTags.FENCES).add(fence(DREADWOOD).get(), fence(EBON).get());
        tag(BlockTags.LEAVES).add(leaves(DREADWOOD).get(), leaves(EBON).get());
        tag(BlockTags.LOGS).add(log(DREADWOOD).get(), log(EBON).get(), strippedLog(DREADWOOD).get(), strippedLog(EBON).get(), wood(DREADWOOD).get(), wood(EBON).get(), strippedWood(DREADWOOD).get(), strippedWood(EBON).get());
        tag(BlockTags.WOODEN_DOORS).add(door(DREADWOOD).get(), door(EBON).get());
        tag(BlockTags.WOODEN_TRAPDOORS).add(trapdoor(DREADWOOD).get(), trapdoor(EBON).get());
        tag(BlockTags.WOODEN_BUTTONS).add(button(DREADWOOD).get(), button(EBON).get());
        tag(BlockTags.WOODEN_FENCES).add(fence(DREADWOOD).get(), fence(EBON).get());
        tag(BlockTags.FENCES).add(fence(DREADWOOD).get(), fence(EBON).get());
        tag(BlockTags.FENCE_GATES).add(gate(DREADWOOD).get(), gate(EBON).get());
        tag(BlockTags.WOODEN_SLABS).add(slab(DREADWOOD).get(), slab(EBON).get());
        tag(BlockTags.WOODEN_STAIRS).add(stairs(DREADWOOD).get(), stairs(EBON).get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(pressurePlate(DREADWOOD).get(), pressurePlate(EBON).get());

        tag(BlockTags.DIRT).add(ModBlocks.DARK_TURF_BLOCK.get(), ModBlocks.NECROTIC_GRASS_BLOCK.get());
        tag(BlockTags.MUSHROOM_GROW_BLOCK).add(ModBlocks.DARK_SOIL.get(), ModBlocks.DARK_TURF_BLOCK.get(), ModBlocks.NECROTIC_GRASS_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.DREADIUM_ORE.get(), ModBlocks.DEEPERSLATE_DREADIUM_ORE.get(), ModBlocks.NECRIUM_ORE.get(), ModBlocks.DEEPERSLATE_NECRIUM_ORE.get(), ModBlocks.RUBY_ORE.get(), ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModBlocks.DEEPERSLATE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DREADIUM_ORE.get(), ModBlocks.DEEPERSLATE_DREADIUM_ORE.get(), ModBlocks.NECRIUM_ORE.get(), ModBlocks.DEEPERSLATE_NECRIUM_ORE.get(), ModBlocks.RUBY_ORE.get(), ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModBlocks.DEEPERSLATE.get(), ModBlocks.WEB_STONE.get(), ModBlocks.FOSSILIZED_BONE_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.DARK_SOIL.get(), ModBlocks.DARK_TURF_BLOCK.get(), ModBlocks.NECROTIC_GRASS_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_HOE).add(leaves(DREADWOOD).get(), leaves(EBON).get());
    }
}
