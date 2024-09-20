package com.bmod.forge.datagen;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.ModEntities;
import com.bmod.registry.item.ModItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModEntityLootTables extends EntityLoot {
    @Override
    protected void addTables() {
        add(ModEntities.BEHEMOTH.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModItems.VILE_BLOOD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(ModEntities.ROT_FLY.get(), LootTable.lootTable());
        add(ModEntities.SNOW_FLINX.get(), LootTable.lootTable());
    }

    @NotNull
    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        List<EntityType<?>> knownEntities = new ArrayList<>();

        for (ResourceLocation id : ModEntities.ENTITY_TYPES.getRegistrar().getIds()) {
            EntityType<?> entityType = ModEntities.ENTITY_TYPES.getRegistrar().get(id);

            if (entityType != null && id.getNamespace().equals(BlubbysMod.MOD_ID)) {
                knownEntities.add(entityType);
            }
        }

        return knownEntities;
    }
}
