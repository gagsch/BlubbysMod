package com.bmod.packet;

import com.bmod.BlubbysMod;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;

public interface ModPackets {
    SimpleNetworkManager NET = SimpleNetworkManager.create(BlubbysMod.MOD_ID);

    MessageType S2C_OPEN_RECIPE_BOOK = NET.registerS2C("s2c_int", S2COpenRecipeBookMessage::new);
    MessageType S2C_ENTITY_RIDING = NET.registerS2C("s2c_entity_riding", S2CEntityRidingMessage::new);
    MessageType S2C_SYNC_NIGHTMARE_GATEWAY = NET.registerS2C("s2c_sync_nightmare_gateway", S2CSyncNightmareGatewayMessage::new);
    MessageType C2S_REQUEST_NIGHTMARE_GATEWAY = NET.registerC2S("c2s_request_nightmare_gateway", C2SRequestGatewayMessage::new);

    static void initialize() {
        NET.registerS2C("s2c_int", S2COpenRecipeBookMessage::new);
        NET.registerS2C("s2c_entity_riding", S2CEntityRidingMessage::new);
        NET.registerS2C("s2c_sync_nightmare_gateway", S2CSyncNightmareGatewayMessage::new);
        NET.registerC2S("c2s_request_nightmare_gateway", C2SRequestGatewayMessage::new);
    }
}
