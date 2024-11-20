package com.bmod.registry.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DragonHeartNecklaceItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        setAttribute(player, Attributes.MAX_HEALTH, 6, AttributeModifier.Operation.ADDITION);
    }
}
