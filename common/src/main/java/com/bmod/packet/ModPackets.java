package com.bmod.packet;

import com.bmod.BlubbysMod;
import com.bmod.packet.C2S.C2SOpenMenu;
import com.bmod.packet.S2C.S2CEntityRidingMessage;
import com.bmod.packet.S2C.S2COpenRecipeBookMessage;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;

public interface ModPackets {
    SimpleNetworkManager NET = SimpleNetworkManager.create(BlubbysMod.MOD_ID);

    MessageType S2C_OPEN_RECIPE_BOOK = NET.registerS2C("s2c_int", S2COpenRecipeBookMessage::new);
    MessageType S2C_ENTITY_RIDING = NET.registerS2C("s2c_entity_riding", S2CEntityRidingMessage::new);
    MessageType C2S_OPEN_MENU = NET.registerC2S("c2s_open_menu", C2SOpenMenu::new);

    static void initialize() {
        NET.registerS2C("s2c_int", S2COpenRecipeBookMessage::new);
        NET.registerS2C("s2c_entity_riding", S2CEntityRidingMessage::new);
        NET.registerC2S("c2s_open_menu", C2SOpenMenu::new);
    }
}
