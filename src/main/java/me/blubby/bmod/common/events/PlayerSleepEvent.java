package me.blubby.bmod.common.events;

import me.blubby.bmod.common.item.ModItems;
import me.blubby.bmod.common.world.ModDimensions;
import me.blubby.bmod.common.world.ModTeleporter;
import me.blubby.bmod.core.util.PositionUtils;
import me.blubby.bmod.core.util.TickHandlerUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerSleepEvent {


    @SubscribeEvent
    public static void onPlayerWake(PlayerSleepInBedEvent event) {

        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof ServerPlayer serverPlayer) {

            TickHandlerUtils.startCountdown(60, () -> {
                if (serverPlayer.isSleeping() && serverPlayer.getInventory().contains(ModItems.TOTEM_OF_DREAMS.get().getDefaultInstance()) && serverPlayer.level.dimension() == Level.OVERWORLD) {
                    serverPlayer.stopSleeping();

                    ResourceKey<Level> targetDimension = ModDimensions.BLYDIM_KEY;
                    PositionUtils.savePositionToPlayer(serverPlayer.blockPosition(), serverPlayer, false);
                    serverPlayer.changeDimension(serverPlayer.level.getServer().getLevel(targetDimension), new ModTeleporter(serverPlayer.blockPosition()));
                }
            });

        }
    }
}
