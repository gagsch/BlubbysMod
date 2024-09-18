package me.blubby.bmod.core.datagen;

import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.item.ModItems;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import java.util.stream.Collectors;

public class ModEntityLootTables extends EntityLoot {
    @Override
    protected void addTables() {
        add(ModEntities.BEHEMOTH.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModItems.VILE_BLOOD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
        add(ModEntities.ROT_FLY.get(), LootTable.lootTable());
        add(ModEntities.SNOW_FLINX.get(), LootTable.lootTable());
    }

    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        return ModEntities.ENTITY_TYPES.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toList());
    }
}
