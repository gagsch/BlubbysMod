package com.bmod.event;

import com.bmod.registry.item.ModItems;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModifyLootEvent {

    public static void initialize() {
        Set<ResourceLocation> ANCIENT_LOOT_TABLES = Set.of(
                BuiltInLootTables.ABANDONED_MINESHAFT,
                BuiltInLootTables.ANCIENT_CITY,
                BuiltInLootTables.SIMPLE_DUNGEON,
                BuiltInLootTables.STRONGHOLD_LIBRARY,
                BuiltInLootTables.IGLOO_CHEST,
                BuiltInLootTables.JUNGLE_TEMPLE,
                BuiltInLootTables.DESERT_PYRAMID,
                BuiltInLootTables.WOODLAND_MANSION,
                BuiltInLootTables.RUINED_PORTAL
        );

        Set<ResourceLocation> DORMANT_CORE_LOOT_TABLES = Set.of(BuiltInLootTables.SHIPWRECK_TREASURE, BuiltInLootTables.BURIED_TREASURE);

        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> { if (builtin) {
            if (ANCIENT_LOOT_TABLES.contains(id)) {
                LootPool.Builder pagePool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                .add(LootItem.lootTableItem(ModItems.ANCIENT_RECIPE_PAGE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 4f)))
                                        .when(LootItemRandomChanceCondition.randomChance(0.1f)));

                LootPool.Builder bookPool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                .add(LootItem.lootTableItem(ModItems.ANCIENT_RECIPE_BOOK.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.06f)));

                context.addPool(pagePool.build());
                context.addPool(bookPool.build());
            }
            /*
            else if (BuiltInLootTables.JUNGLE_TEMPLE.equals(id)) {
                LootPool.Builder cursedDollPool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                .add(LootItem.lootTableItem(ModItems.VOODOO_DOLL.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.4f)));

                context.addPool(cursedDollPool.build());
            }
            */
            else if (BuiltInLootTables.END_CITY_TREASURE.equals(id)) {
                LootPool.Builder corePool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                .add(LootItem.lootTableItem(ModItems.ETERNAL_CORE.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.1f)));

                context.addPool(corePool.build());
            }
            else if (DORMANT_CORE_LOOT_TABLES.contains(id)) {
                LootPool.Builder corePool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                .add(LootItem.lootTableItem(ModItems.DORMANT_CORE.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.1f)));

                context.addPool(corePool.build());
            }
        }});
    }
}