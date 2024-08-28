package me.blubby.bmod.datagen;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        dropSelf(ModBlocks.TEKTITE.get());
        dropSelf(ModBlocks.COMPRESSED_TEKTITE.get());
        dropOther(ModBlocks.TEKTITE_GRASS.get(), ModBlocks.TEKTITE.get());
        dropOther(ModBlocks.TEKTITE_NECRO.get(), ModBlocks.TEKTITE.get());
        dropOther(ModBlocks.TEKTITE_SNOW.get(), ModBlocks.TEKTITE.get());

        createOreDrop(ModBlocks.COSMILITE_ORE.get(), ModItems.COSMILITE_CHUNK.get()).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).apply(ApplyExplosionDecay.explosionDecay());
        dropWhenSilkTouch(ModBlocks.COSMILITE_ORE.get());


        dropSelf(ModBlocks.COSMIC_OAK_LOG.get());
        dropSelf(ModBlocks.COSMIC_OAK_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_COSMIC_OAK_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COSMIC_OAK_WOOD.get());
        dropSelf(ModBlocks.COSMIC_OAK_PLANKS.get());
        dropSelf(ModBlocks.COSMIC_OAK_SAPLING.get());
        createShearsOnlyDrop(ModBlocks.COSMIC_OAK_LEAVES.get());
        dropWhenSilkTouch(ModBlocks.COSMIC_OAK_LEAVES.get());
        createShearsOnlyDrop(ModBlocks.COSMIC_GRASS.get());
        dropWhenSilkTouch(ModBlocks.COSMIC_GRASS.get());

        dropSelf(ModBlocks.EBON_LOG.get());
        dropSelf(ModBlocks.EBON_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_EBON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_EBON_WOOD.get());
        dropSelf(ModBlocks.EBON_PLANKS.get());
        dropSelf(ModBlocks.EBON_SAPLING.get());
        createShearsOnlyDrop(ModBlocks.EBON_LEAVES.get());
        dropWhenSilkTouch(ModBlocks.EBON_LEAVES.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }
}
