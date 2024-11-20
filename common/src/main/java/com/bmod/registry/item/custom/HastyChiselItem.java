package com.bmod.registry.item.custom;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class HastyChiselItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        setAttribute(player, ModAttributes.DIGGING_SPEED.get(), 0.33, AttributeModifier.Operation.ADDITION);
    }
}
