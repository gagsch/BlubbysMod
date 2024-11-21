package com.bmod.registry.item.custom;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class SilverScaleItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        if (player.isInWater()) {
            setAttribute(player, ModAttributes.SWIMMING_SPEED.get(), 1.15f, AttributeModifier.Operation.MULTIPLY_BASE);
        }
        else {
            clearAttribute(player, ModAttributes.SWIMMING_SPEED.get());
        }
    }
}
