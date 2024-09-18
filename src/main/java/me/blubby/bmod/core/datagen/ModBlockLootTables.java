package me.blubby.bmod.core.datagen;
import me.blubby.bmod.common.block.ModBlocks;
import me.blubby.bmod.core.util.WoodUtils;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static me.blubby.bmod.core.util.WoodUtils.*;

public class ModBlockLootTables extends BlockLoot {
    private final Set<String> registeredBlockLoot = new HashSet<>();

    @Override
    protected void addTables() {
        dropOther(ModBlocks.NECROTIC_GRASS_BLOCK.get(), ModBlocks.DARK_SOIL.get());
        dropOther(ModBlocks.DARK_TURF_BLOCK.get(), ModBlocks.DARK_SOIL.get());

        add(ModBlocks.SCARLITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.SCARLITE_ORE.get(), ModItems.SCARLITE_CHUNK.get()));
        add(ModBlocks.NECRIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NECRIUM_ORE.get(), ModItems.NECRIUM_CHUNK.get()));

        add(ModBlocks.ROT_BLOCK.get(), createSingleItemTable(Items.ROTTEN_FLESH, UniformGenerator.between(1, 4)));
        add(ModBlocks.HOT_PEPPER_CROP.get(), createSingleItemTable(ModItems.HOT_PEPPER_SEEDS.get(), UniformGenerator.between(0,1)));
        add(ModBlocks.BUBBLE_BLOCK.get(), noDrop());

        add(WoodUtils.leaves(DREADWOOD).get(), (block) ->
                createLeavesDrops(block, WoodUtils.sapling(DREADWOOD).get(), ModItems.ROTTEN_APPLE.get(), 0.1F));
        add(WoodUtils.leaves(EBON).get(), (block) ->
                createLeavesDrops(block, WoodUtils.sapling(EBON).get(), ModItems.ROTTEN_APPLE.get(), 0.1F));

        registeredBlockLoot.add(ModBlocks.NECROTIC_GRASS_BLOCK.getId().getPath());
        registeredBlockLoot.add(ModBlocks.DARK_TURF_BLOCK.getId().getPath());
        registeredBlockLoot.add(ModBlocks.SCARLITE_ORE.getId().getPath());
        registeredBlockLoot.add(ModBlocks.NECRIUM_ORE.getId().getPath());
        registeredBlockLoot.add(ModBlocks.ROT_BLOCK.getId().getPath());
        registeredBlockLoot.add(ModBlocks.HOT_PEPPER_CROP.getId().getPath());
        registeredBlockLoot.add(ModBlocks.BUBBLE_BLOCK.getId().getPath());
        registeredBlockLoot.add(WoodUtils.leaves(DREADWOOD).getId().getPath());
        registeredBlockLoot.add(WoodUtils.leaves(EBON).getId().getPath());

        dropSelfForUnset();
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }

    public void dropSelfForUnset() {
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries())
        {
            String path = block.getId().getPath();
            if (!registeredBlockLoot.contains(path)) {
                dropSelf(block.get());
            }
        }
    }

    protected static LootTable.Builder createLeavesDrops(Block fromBlock, Block toBlock, Item item, float... chance) {
        LootItemCondition.Builder noSilkTouch = MatchTool.toolMatches(ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))
                .invert();
        LootItemCondition.Builder notUsingShears = MatchTool.toolMatches(ItemPredicate.Builder.item()
                        .of(Items.SHEARS))
                .invert();
        LootItemCondition.Builder combinedCondition = noSilkTouch.or(notUsingShears);

        return createLeavesDrops(fromBlock, toBlock, chance).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(combinedCondition).add(((LootPoolSingletonContainer.Builder)applyExplosionCondition(fromBlock, LootItem.lootTableItem(item))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
    }
}