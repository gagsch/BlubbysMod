package com.bmod.event;

import com.bmod.registry.block.block_entity.custom.DimensionGatewayBlockEntity;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.server.level.ServerLevel;

public class PlayerInteractEvent {
    public static void initialize()
    {
        InteractionEvent.LEFT_CLICK_BLOCK.register((player, interactionHand, blockPos, direction) -> {
            if (!player.isCreative() && player.level instanceof ServerLevel level && level.getBlockEntity(blockPos) instanceof DimensionGatewayBlockEntity blockEntity)
            {
                // blockEntity.drops();
                return EventResult.pass();
            }

            return EventResult.pass();
        });
    }
}
