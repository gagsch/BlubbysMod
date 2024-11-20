package com.bmod.registry.item.custom;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class LuckyChiselItem extends AccessoryItem {
    @Override
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        setAttribute(player, Attributes.LUCK, 10, AttributeModifier.Operation.ADDITION);
        setAttribute(player, ModAttributes.DIGGING_SPEED.get(), 0.66, AttributeModifier.Operation.ADDITION);
    }
}
