package me.blubby.bmod.datagen;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
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

        add(ModBlocks.COSMILITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.COSMILITE_ORE.get(), ModItems.COSMILITE_CHUNK.get()));
        add(ModBlocks.NECRIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NECRIUM_ORE.get(), ModItems.NECRIUM_CHUNK.get()));

        add(ModBlocks.DEAD_TISSUE_BLOCK.get(), createSingleItemTable(Items.ROTTEN_FLESH, UniformGenerator.between(1, 4)));
        dropSelf(ModBlocks.FOSSILIZED_BONE_BLOCK.get());

        dropSelf(ModBlocks.COSMIC_OAK_LOG.get());
        dropSelf(ModBlocks.COSMIC_OAK_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_COSMIC_OAK_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COSMIC_OAK_WOOD.get());
        add(ModBlocks.COSMIC_OAK_LEAVES.get(), (block) ->
                createCosmicOakLeavesDrops(block, ModBlocks.COSMIC_OAK_SAPLING.get(), 0.1F));
        dropSelf(ModBlocks.COSMIC_OAK_PLANKS.get());
        dropSelf(ModBlocks.COSMIC_OAK_SAPLING.get());

        dropSelf(ModBlocks.EBON_LOG.get());
        dropSelf(ModBlocks.EBON_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_EBON_LOG.get());
        dropSelf(ModBlocks.STRIPPED_EBON_WOOD.get());
        add(ModBlocks.EBON_LEAVES.get(), (block) ->
                createCosmicOakLeavesDrops(block, ModBlocks.EBON_SAPLING.get(), 0.1F));
        dropSelf(ModBlocks.EBON_PLANKS.get());
        dropSelf(ModBlocks.EBON_SAPLING.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }

    protected static LootTable.Builder createCosmicOakLeavesDrops(Block fromBlock, Block toBlock, float... chance) {
        LootItemCondition.Builder noSilkTouch = MatchTool.toolMatches(ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))
                .invert();
        LootItemCondition.Builder notUsingShears = MatchTool.toolMatches(ItemPredicate.Builder.item()
                        .of(Items.SHEARS))
                .invert();
        LootItemCondition.Builder combinedCondition = noSilkTouch.or(notUsingShears);

        return createLeavesDrops(fromBlock, toBlock, chance).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(combinedCondition).add(((LootPoolSingletonContainer.Builder)applyExplosionCondition(fromBlock, LootItem.lootTableItem(Items.GOLDEN_APPLE))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
    }
}
