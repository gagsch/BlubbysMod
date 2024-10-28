package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.ModEntityTypes;
import com.bmod.registry.item.ModItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModEntityLootTables extends EntityLoot {
    @Override
    protected void addTables() {
        add(ModEntityTypes.BEHEMOTH.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(ModItems.VILE_BLOOD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        add(ModEntityTypes.ROT_FLY.get(), LootTable.lootTable());

        add(ModEntityTypes.SNOW_FLINX.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(Items.COD)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))
                .when(LootItemRandomChanceCondition.randomChance(0.5f))));

        add(ModEntityTypes.LEECH.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(ModItems.VILE_BLOOD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()))));

        add(ModEntityTypes.DARK_FAIRY.get(), LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(ModItems.ESSENCE_DARKNESS.get())
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f)))
                .add(LootItem.lootTableItem(ModItems.FAIRY_DUST.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75f)))));
    }

    @NotNull
    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        List<EntityType<?>> knownEntities = new ArrayList<>();

        for (ResourceLocation id : ModEntityTypes.ENTITY_TYPES.getRegistrar().getIds()) {
            EntityType<?> entityType = ModEntityTypes.ENTITY_TYPES.getRegistrar().get(id);

            if (entityType != null && id.getNamespace().equals(BlubbysMod.MOD_ID)) {
                knownEntities.add(entityType);
            }
        }

        return knownEntities;
    }
}
