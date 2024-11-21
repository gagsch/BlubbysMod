package com.bmod.packet.C2S;

import com.bmod.packet.ModPackets;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class C2SOpenMenu extends BaseC2SMessage {
    public C2SOpenMenu() {
    }

    public C2SOpenMenu(FriendlyByteBuf buf) {
    }

    @Override
    public MessageType getType() {
        return ModPackets.C2S_OPEN_MENU;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        ServerPlayer player = context.getPlayer().getServer().getPlayerList().getPlayer(context.getPlayer().getUUID());

        context.queue(() -> SOpenAccessoryMenuHandler.handle(player));
    }
}