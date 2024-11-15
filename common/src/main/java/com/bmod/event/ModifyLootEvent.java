package com.bmod.event;

import com.bmod.registry.item.ModItems;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.Set;

import static com.bmod.registry.item.custom.BlueprintItem.*;

public class ModifyLootEvent {

    private static final ResourceLocation DREADWOOD_HOUSE = new ResourceLocation("chests/dreadwood_house");

    private static final Set<ResourceLocation> BLUEPRINT_LOOT_TABLES = Set.of(
            BuiltInLootTables.IGLOO_CHEST,
            BuiltInLootTables.WOODLAND_MANSION,
            BuiltInLootTables.VILLAGE_CARTOGRAPHER,
            BuiltInLootTables.VILLAGE_ARMORER,
            BuiltInLootTables.VILLAGE_TOOLSMITH,
            DREADWOOD_HOUSE
    );

    private static final Set<ResourceLocation> CURSED_GEM_LOOT_TABLES = Set.of(
            BuiltInLootTables.ANCIENT_CITY,
            DREADWOOD_HOUSE
    );

    private static final Set<ResourceLocation> HOT_PEPPER_LOOT_TABLES = Set.of(
            BuiltInLootTables.FARMER_GIFT,
            BuiltInLootTables.VILLAGE_DESERT_HOUSE,
            BuiltInLootTables.VILLAGE_SAVANNA_HOUSE
    );

    private static final Set<ResourceLocation> MOLTEN_SLAG_LOOT_TABLES = Set.of(
            BuiltInLootTables.BASTION_TREASURE,
            BuiltInLootTables.RUINED_PORTAL,
            BuiltInLootTables.NETHER_BRIDGE
    );

    public static void initialize() {
        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> { if (builtin) {
            if (BLUEPRINT_LOOT_TABLES.contains(id)) {
                context.addPool(createBlueprintLootPool(ACCESSORY).build());
                context.addPool(createBlueprintLootPool(SPECIAL_ITEM).build());
                context.addPool(createBlueprintLootPool(RESOURCE).build());
                context.addPool(createBlueprintLootPool(UPGRADE).build());
                context.addPool(createBlueprintLootPool(GEAR).build());
            }

            if (CURSED_GEM_LOOT_TABLES.contains(id)) {
                context.addPool(createBasicLootPool(ModItems.CURSED_GEM.get(), 0.075f, 1, 1, 1, 1).build());
            }

            if (HOT_PEPPER_LOOT_TABLES.contains(id)) {
                context.addPool(createBasicLootPool(ModItems.HOT_PEPPER_SEEDS.get(), 0.2f, 1, 3, 1, 2).build());
            }

            if (MOLTEN_SLAG_LOOT_TABLES.contains(id)) {
                context.addPool(createBasicLootPool(ModItems.MOLTEN_SLAG.get(), 0.075f, 1, 1, 2, 3).build());
            }
        }});
    }

    public static LootPool.Builder createBlueprintLootPool(String tagKey) {
        return LootPool.lootPool()
                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                .add(LootItem.lootTableItem(ModItems.BLUEPRINT.get())
                        .apply(() -> {
                            CompoundTag tag = new CompoundTag();
                            tag.putString("blueprint", tagKey);

                            return SetNbtFunction.setTag(tag).build();
                        })
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)))
                        .when(LootItemRandomChanceCondition.randomChance(0.075f))
                );
    }

    public static LootPool.Builder createBasicLootPool(Item item, float chance, float min, float max, int rollMin, int rollMax) {
        return LootPool.lootPool()
                .setRolls(UniformGenerator.between(rollMin, rollMax))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .when(LootItemRandomChanceCondition.randomChance(chance))
                );
    }
}