package com.bmod.registry.item.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GravityBootsItem extends AccessoryItem {
    @Override
    public void localAccessoryTick(Level level, Player player) {
        if (player.getDeltaMovement().y >= 0 && !player.isCrouching())
        {
            player.setDeltaMovement(player.getDeltaMovement().add(0, 0.04f, 0));
        }
        player.resetFallDistance();
    }
}
