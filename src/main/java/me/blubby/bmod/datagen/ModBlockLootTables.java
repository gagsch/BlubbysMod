package me.blubby.bmod.datagen;
import me.blubby.bmod.common.blocks.ModBlocks;
import me.blubby.bmod.utils.WoodUtils;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
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

import static me.blubby.bmod.utils.WoodUtils.*;

public class ModBlockLootTables extends BlockLoot {
    private final Set<String> registeredBlockLoot = new HashSet<>();

    @Override
    protected void addTables() {
        dropOther(ModBlocks.TEKTITE_GRASS.get(), ModBlocks.TEKTITE.get());
        dropOther(ModBlocks.TEKTITE_NECRO.get(), ModBlocks.TEKTITE.get());
        dropOther(ModBlocks.TEKTITE_SNOW.get(), ModBlocks.TEKTITE.get());

        add(ModBlocks.COSMILITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.COSMILITE_ORE.get(), ModItems.COSMILITE_CHUNK.get()));
        add(ModBlocks.NECRIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NECRIUM_ORE.get(), ModItems.NECRIUM_CHUNK.get()));

        add(ModBlocks.DEAD_TISSUE_BLOCK.get(), createSingleItemTable(Items.ROTTEN_FLESH, UniformGenerator.between(1, 4)));
        add(ModBlocks.HOT_PEPPER_CROP.get(), createSingleItemTable(ModItems.HOT_PEPPER.get(), UniformGenerator.between(1, 3)));

        add(WoodUtils.leaves(COSMIC_OAK).get(), (block) ->
                createCosmicOakLeavesDrops(block, WoodUtils.sapling(COSMIC_OAK).get(), 0.1F));
        add(WoodUtils.leaves(EBON).get(), (block) ->
                createCosmicOakLeavesDrops(block, WoodUtils.sapling(EBON).get(), 0.1F));

        registeredBlockLoot.add(ModBlocks.TEKTITE_GRASS.getId().getPath());
        registeredBlockLoot.add(ModBlocks.TEKTITE_NECRO.getId().getPath());
        registeredBlockLoot.add(ModBlocks.TEKTITE_SNOW.getId().getPath());
        registeredBlockLoot.add(ModBlocks.COSMILITE_ORE.getId().getPath());
        registeredBlockLoot.add(ModBlocks.NECRIUM_ORE.getId().getPath());
        registeredBlockLoot.add(ModBlocks.DEAD_TISSUE_BLOCK.getId().getPath());
        registeredBlockLoot.add(ModBlocks.HOT_PEPPER_CROP.getId().getPath());
        registeredBlockLoot.add(WoodUtils.leaves(COSMIC_OAK).getId().getPath());
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
