package com.bmod.packet;

import com.bmod.BlubbysMod;
import com.bmod.packet.C2S.C2SOpenMenu;
import com.bmod.packet.C2S.C2SSaveFrog;
import com.bmod.packet.C2S.C2SSavePixel;
import com.bmod.packet.S2C.S2CEntityRidingMessage;
import com.bmod.packet.S2C.S2CSyncAccessories;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;

public interface ModPackets {
    SimpleNetworkManager NET = SimpleNetworkManager.create(BlubbysMod.MOD_ID);

    MessageType S2C_ENTITY_RIDING = NET.registerS2C("s2c_entity_riding", S2CEntityRidingMessage::new);
    MessageType S2C_SYNC_ACCESSORIES = NET.registerS2C("s2c_sync_accessories", S2CSyncAccessories::new);
    MessageType C2S_OPEN_MENU = NET.registerC2S("c2s_open_menu", C2SOpenMenu::new);
    MessageType C2S_SAVE_PIXEL = NET.registerC2S("c2s_save_pixel", C2SSavePixel::new);
    MessageType C2S_SAVE_FROG = NET.registerC2S("c2s_save_frog", C2SSaveFrog::new);
}