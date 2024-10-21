package com.bmod.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class CEntityRidingHandler {
    public static void handle(Player player, Entity entity, boolean gettingOn) {
        if (gettingOn)
            entity.startRiding(player);
        else
            player.ejectPassengers();
    }
}
