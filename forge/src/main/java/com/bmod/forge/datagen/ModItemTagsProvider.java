package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.bmod.util.WoodUtils.*;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagsProvider, ExistingFileHelper exFileHelper) {
        super(gen, blockTagsProvider, BlubbysMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ItemTags.LOGS).add(log(DREADWOOD).get().asItem(), log(EBON).get().asItem(), strippedLog(DREADWOOD).get().asItem(), strippedLog(EBON).get().asItem(), wood(DREADWOOD).get().asItem(), wood(EBON).get().asItem(), strippedWood(DREADWOOD).get().asItem(), strippedWood(EBON).get().asItem());
        tag(ItemTags.PLANKS).add(planks(DREADWOOD).get().asItem(), planks(EBON).get().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(ModBlocks.DEEPERSLATE.get().asItem());
    }
}
