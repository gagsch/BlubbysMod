package me.blubby.bmod.core.util;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Queue;

@Mod.EventBusSubscriber
public class TickHandlerUtils {

    private static int ticksRemaining = 0;
    private static boolean shouldExecuteAction = false;
    private static Runnable action;

    public static void startCountdown(int ticks, Runnable actionToExecute) {
        ticksRemaining = ticks;
        shouldExecuteAction = true;
        action = actionToExecute;
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.ServerTickEvent event) {
        if (shouldExecuteAction) {
            if (ticksRemaining > 0) {
                ticksRemaining--;
            } else {
                action.run();
                shouldExecuteAction = false;
            }
        }
    }
}