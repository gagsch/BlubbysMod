package com.bmod.event;

import com.bmod.registry.item.ModItems;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModifyLootEvent {

    public static void initialize() {
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> { if (builtin) {
            if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(id) ||
                    BuiltInLootTables.ANCIENT_CITY.equals(id) ||
                    BuiltInLootTables.SIMPLE_DUNGEON.equals(id) ||
                    BuiltInLootTables.STRONGHOLD_LIBRARY.equals(id) ||
                    BuiltInLootTables.IGLOO_CHEST.equals(id) ||
                    BuiltInLootTables.JUNGLE_TEMPLE.equals(id) ||
                    BuiltInLootTables.DESERT_PYRAMID.equals(id) ||
                    BuiltInLootTables.WOODLAND_MANSION.equals(id) ||
                    BuiltInLootTables.RUINED_PORTAL.equals(id)) {

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

            if (BuiltInLootTables.JUNGLE_TEMPLE.equals(id))
            {
                LootPool.Builder cursedDollPool =
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 1.0F))
                                .add(LootItem.lootTableItem(ModItems.VOODOO_DOLL.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.4f)));

                context.addPool(cursedDollPool.build());
            }
        }});
    }
}