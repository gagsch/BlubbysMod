package com.bmod.packet;

import com.bmod.BlubbysMod;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;

public interface ModPackets {
    SimpleNetworkManager NET = SimpleNetworkManager.create(BlubbysMod.MOD_ID);

    MessageType SERVER_2_CLIENT_INT = NET.registerS2C("s2c_int", Server2ClientIntMessage::new);

    static void initialize() {
        NET.registerS2C("s2c_int", Server2ClientIntMessage::new);
    }
}
