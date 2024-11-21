package com.bmod.registry.item.custom;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class GoldenScaleItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        if (player.isInWater()) {
            setAttribute(player, ModAttributes.SWIMMING_SPEED.get(), 1.25f, AttributeModifier.Operation.MULTIPLY_BASE);
        }
        else {
            clearAttribute(player, ModAttributes.SWIMMING_SPEED.get());
        }
    }
}
