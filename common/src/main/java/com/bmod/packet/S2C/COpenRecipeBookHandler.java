package com.bmod.packet.S2C;

import com.bmod.registry.menu.AncientRecipeBookScreen;
import net.minecraft.client.Minecraft;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class COpenRecipeBookHandler {
    public static void handle(int pages) {
        Minecraft.getInstance().setScreen(new AncientRecipeBookScreen(pages));
    }
}
