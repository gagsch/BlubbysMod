package com.bmod.forge.events;

import com.bmod.registry.item.ModItems;
import com.bmod.registry.world.ModDimensions;
import com.bmod.forge.world.ModTeleporter;
import com.bmod.platform.forge.PositionUtilsImpl;
import com.bmod.util.TickHandlerUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class PlayerSleepEvent {


    @SubscribeEvent
    public static void onPlayerWake(PlayerSleepInBedEvent event) {

        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer) {

            TickHandlerUtils.startCountdown(60, () -> {
                if (serverPlayer.isSleeping() && serverPlayer.getInventory().contains(ModItems.TOTEM_OF_DREAMS.get().getDefaultInstance()) && serverPlayer.level.dimension() == Level.OVERWORLD) {
                    serverPlayer.stopSleeping();

                    ResourceKey<Level> targetDimension = ModDimensions.BLYDIM_KEY;
                    PositionUtilsImpl.savePositionToPlayer(serverPlayer.blockPosition(), serverPlayer, false);
                    serverPlayer.changeDimension(Objects.requireNonNull(Objects.requireNonNull(serverPlayer.level.getServer()).getLevel(targetDimension)), new ModTeleporter(serverPlayer.blockPosition()));
                }
            });

        }
    }
}
