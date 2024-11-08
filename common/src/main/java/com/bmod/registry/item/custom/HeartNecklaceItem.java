package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HeartNecklaceItem extends BaseAccessoryItem {
    @Override
    public void accessoryTick(ServerLevel level, ServerPlayer player) {
        setAttribute(player, Attributes.MAX_HEALTH, 2, AttributeModifier.Operation.ADDITION);
    }
}
