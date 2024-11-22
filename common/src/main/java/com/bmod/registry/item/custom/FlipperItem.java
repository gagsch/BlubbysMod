package com.bmod.registry.item.custom;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class FlipperItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        setAttribute(player, ModAttributes.SWIMMING_SPEED.get(), 1.2f, AttributeModifier.Operation.MULTIPLY_BASE);
    }
}
