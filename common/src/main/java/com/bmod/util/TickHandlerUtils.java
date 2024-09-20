package com.bmod.util;

import dev.architectury.event.events.common.TickEvent;

public class TickHandlerUtils {

    public static void initialize() {
        events();
    }

    private static int ticksRemaining = 0;
    private static boolean shouldExecuteAction = false;
    private static Runnable action;

    public static void startCountdown(int ticks, Runnable actionToExecute) {
        ticksRemaining = ticks;
        shouldExecuteAction = true;
        action = actionToExecute;
    }

    public static void events() {
        TickEvent.SERVER_LEVEL_PRE.register(instance ->
        {
            if (shouldExecuteAction) {
                if (ticksRemaining > 0) {
                    ticksRemaining--;
                } else {
                    action.run();
                    shouldExecuteAction = false;
                }
            }
        });
    }
}
