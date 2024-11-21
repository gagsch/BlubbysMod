package com.bmod.registry.block.block_entity;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.block_entity.custom.DimensionGatewayBlockEntity;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final Supplier<BlockEntityType<DimensionGatewayBlockEntity>> DIMENSION_GATEWAY_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register("dimension_gateway_block_entity",
            () -> BlockEntityType.Builder.of(DimensionGatewayBlockEntity::new, ModBlocks.DIMENSION_GATEWAY.get()).build(null));
}
