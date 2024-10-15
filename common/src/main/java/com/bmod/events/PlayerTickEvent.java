package com.bmod.events;

import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.world.ModDimensions;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class PlayerTickEvent {
    public static void initialize() {
        TickEvent.PLAYER_PRE.register((player) ->
        {
            if (player instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.getLevel().dimension() != ModDimensions.BLYDIM_KEY)
                    return;

                BlockPos respawnPos = serverPlayer.getRespawnPosition() != null ? serverPlayer.getRespawnPosition() : serverPlayer.server.overworld().getSharedSpawnPos();

                if (ModMobEffects.HORRIFIED.get() == null || !serverPlayer.hasEffect(ModMobEffects.HORRIFIED.get())) {
                    serverPlayer.teleportTo(serverPlayer.level.getServer().getLevel(Level.OVERWORLD),
                            respawnPos.getX(),
                            respawnPos.getY(),
                            respawnPos.getZ(),
                            0, 0);
                }
            }
        });
    }
}
