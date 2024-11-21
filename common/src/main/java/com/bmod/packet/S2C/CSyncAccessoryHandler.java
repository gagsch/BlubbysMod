package com.bmod.packet.S2C;

import com.bmod.util.ContainerUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;

@Environment(EnvType.CLIENT)
public class CSyncAccessoryHandler {
    public static void handle(Container container) {
        ContainerUtils.saveAccessoriesToPlayer(container, Minecraft.getInstance().player);
    }
}