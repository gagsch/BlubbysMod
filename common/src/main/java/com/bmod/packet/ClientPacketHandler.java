package com.bmod.packet;

import com.bmod.registry.menu.AncientRecipeBookScreen;
import net.minecraft.client.Minecraft;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientPacketHandler {

    public static void handleOpenRecipeBook(int pages) {
        Minecraft.getInstance().setScreen(new AncientRecipeBookScreen(pages));
    }
}
