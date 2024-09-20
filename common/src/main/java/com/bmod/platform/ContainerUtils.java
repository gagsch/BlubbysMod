package com.bmod.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;

public class ContainerUtils {
    @ExpectPlatform
    public static void saveContainerToPlayer(SimpleContainer container, Player player, String key)
    {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void loadContainerFromPlayer(SimpleContainer container, Player player, String key)
    {
        throw new AssertionError();
    }
}
