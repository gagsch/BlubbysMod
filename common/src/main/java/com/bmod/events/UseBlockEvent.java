package com.bmod.events;

import com.bmod.registry.item.ModItems;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.registry.world.ModDimensions;
import com.bmod.util.TickHandlerUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class UseBlockEvent {

    public static void initialize() {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, face) ->
        {
            if (!player.level.isClientSide() && player instanceof ServerPlayer serverPlayer) {

                TickHandlerUtils.startCountdown(60, () -> {
                    if (serverPlayer.isSleeping() && serverPlayer.hasEffect(ModMobEffects.HORRIFIED.get()) && serverPlayer.level.dimension() == Level.OVERWORLD) {
                        serverPlayer.stopSleeping();

                        serverPlayer.teleportTo(serverPlayer.level.getServer().getLevel(ModDimensions.BLYDIM_KEY),
                                serverPlayer.position().x,
                                serverPlayer.position().y,
                                serverPlayer.position().z,
                                0,0);
                    }
                });

            }

            return EventResult.pass();
        });
    }
}
