package com.bmod.registry.item.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GravityBootsItem extends AccessoryItem {
    @Override
    @Environment(EnvType.SERVER)
    public void serverAccessoryTick(ServerLevel level, ServerPlayer player) {
        localAccessoryTick(level, player);
    }

    @Override
    public void localAccessoryTick(Level level, Player player) {
        if (player.getAbilities().flying || player.isFallFlying() || player.isSwimming())
            return;

        float factor = 0.0f;
        if (player.getDeltaMovement().y >= 0 && !player.isCrouching())
        {
            factor = 0.04f;
        }
        else if (player.isCrouching())
        {
            factor = -0.09f;
        }
        player.setDeltaMovement(player.getDeltaMovement().add(0, factor, 0));

        player.resetFallDistance();
    }
}
