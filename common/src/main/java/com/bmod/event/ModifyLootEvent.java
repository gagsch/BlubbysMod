package com.bmod.event;

import com.bmod.registry.item.ModItems;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
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

    public static void initialize() {
        Set<ResourceLocation> BLUEPRINT_LOOT_TABLES = Set.of(
                BuiltInLootTables.IGLOO_CHEST,
                BuiltInLootTables.WOODLAND_MANSION,
                BuiltInLootTables.VILLAGE_CARTOGRAPHER,
                BuiltInLootTables.VILLAGE_ARMORER,
                BuiltInLootTables.VILLAGE_TOOLSMITH
        );

        LootEvent.MODIFY_LOOT_TABLE.register((lootTables, id, context, builtin) -> { if (builtin) {
            if (BLUEPRINT_LOOT_TABLES.contains(id)) {
                context.addPool(createBlueprintLootPool(CREATE).build());
                context.addPool(createBlueprintLootPool(ACCESSORY).build());
                context.addPool(createBlueprintLootPool(SPECIAL_ITEM).build());
                context.addPool(createBlueprintLootPool(UPGRADE).build());
            }
        }});
    }

    public static LootPool.Builder createBlueprintLootPool(String tagKey) {
        return LootPool.lootPool()
                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                .add(LootItem.lootTableItem(ModItems.BLUEPRINT.get())
                        .apply(() -> {
                            CompoundTag tag = new CompoundTag();
                            tag.putString("blueprint", tagKey);

                            return SetNbtFunction.setTag(tag).build();
                        })
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 3f)))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                );
    }
}