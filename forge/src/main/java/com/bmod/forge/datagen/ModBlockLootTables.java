package com.bmod.forge.datagen;
import com.bmod.registry.block.ModBlocks;
import com.bmod.util.WoodUtils;
import com.bmod.registry.item.ModItems;
import dev.architectury.registry.registries.RegistrySupplier;
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
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static com.bmod.util.WoodUtils.*;

public class ModBlockLootTables extends BlockLoot {
    private final Set<String> registeredBlockLoot = new HashSet<>();

    @Override
    protected void addTables() {
        add(door(DREADWOOD).get(), createDoorTable(door(DREADWOOD).get()));
        add(door(EBON).get(), createDoorTable(door(EBON).get()));
        add(slab(DREADWOOD).get(), createSlabItemTable(slab(DREADWOOD).get()));
        add(slab(EBON).get(), createSlabItemTable(slab(EBON).get()));

        dropOther(ModBlocks.NECROTIC_GRASS_BLOCK.get(), ModBlocks.DARK_SOIL.get());
        dropOther(ModBlocks.DARK_TURF_BLOCK.get(), ModBlocks.DARK_SOIL.get());

        dropOther(ModBlocks.WEB_STONE.get(), ModBlocks.DEEPERSLATE.get());
        dropOther(ModBlocks.SILK_BLOCK.get(), Items.STRING);

        add(ModBlocks.RUBY_ORE.get(),
                (block) -> createOreDrop(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get()));
        add(ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModItems.RUBY.get()));

        add(ModBlocks.DREADIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DREADIUM_ORE.get(), ModItems.DREADIUM_CHUNK.get()));
        add(ModBlocks.DEEPERSLATE_DREADIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPERSLATE_DREADIUM_ORE.get(), ModItems.DREADIUM_CHUNK.get()));

        add(ModBlocks.NECRIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NECRIUM_ORE.get(), ModItems.NECRIUM_CHUNK.get()));
        add(ModBlocks.DEEPERSLATE_NECRIUM_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPERSLATE_NECRIUM_ORE.get(), ModItems.NECRIUM_CHUNK.get()));

        add(ModBlocks.ROT_BLOCK.get(), createSingleItemTable(Items.ROTTEN_FLESH, UniformGenerator.between(1, 4)));
        add(ModBlocks.HOT_PEPPER_CROP.get(), createSingleItemTable(ModItems.HOT_PEPPER_SEEDS.get(), UniformGenerator.between(0,1)));
        add(ModBlocks.BUBBLE_BLOCK.get(), noDrop());

        add(WoodUtils.leaves(DREADWOOD).get(), (block) ->
                createLeavesDrops(block, WoodUtils.sapling(DREADWOOD).get(), ModItems.ROTTEN_APPLE.get(), 0.1F));
        add(WoodUtils.leaves(EBON).get(), (block) ->
                createLeavesDrops(block, WoodUtils.sapling(EBON).get(), ModItems.ROTTEN_APPLE.get(), 0.1F));

        registeredBlockLoot.add(door(DREADWOOD).get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(slab(DREADWOOD).get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(door(EBON).get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(slab(EBON).get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.NECROTIC_GRASS_BLOCK.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.DARK_TURF_BLOCK.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.WEB_STONE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.SILK_BLOCK.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.RUBY_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.DEEPSLATE_RUBY_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.DREADIUM_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.DEEPERSLATE_DREADIUM_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.NECRIUM_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.DEEPERSLATE_NECRIUM_ORE.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.ROT_BLOCK.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.HOT_PEPPER_CROP.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(ModBlocks.BUBBLE_BLOCK.get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(WoodUtils.leaves(DREADWOOD).get().builtInRegistryHolder().key().location().getPath());
        registeredBlockLoot.add(WoodUtils.leaves(EBON).get().builtInRegistryHolder().key().location().getPath());

        dropSelfForUnset();
    }

    public void dropSelfForUnset() {
        for (RegistrySupplier<Block> block : ModBlocks.BLOCKS)
        {
            String path = block.get().builtInRegistryHolder().key().location().getPath();
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

    @NotNull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCK_HASH_MAP.values().stream().map(Supplier::get)::iterator;
    }
}
