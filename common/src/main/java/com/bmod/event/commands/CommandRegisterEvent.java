package com.bmod.event.commands;

import dev.architectury.event.events.common.CommandRegistrationEvent;

public class CommandRegisterEvent {
    public static void initialize() {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, selection) -> {
            dispatcher.register(MultiplyTpCommand.build());
            dispatcher.register(PixelChangeCommand.build());
            dispatcher.register(PixelChangeCommand.buildSecondary());
        });
    }
}