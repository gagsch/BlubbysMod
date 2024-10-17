package com.bmod.packet;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;

public class Server2ClientIntMessage extends BaseS2CMessage {
    private final int pages;

    public Server2ClientIntMessage(int pages) {
        this.pages = pages;
    }

    public Server2ClientIntMessage(FriendlyByteBuf buf) {
        pages = buf.readVarInt();
    }

    @Override
    public MessageType getType() {
        return ModPackets.SERVER_2_CLIENT_INT;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(pages);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> ClientPacketHandler.handleOpenRecipeBook(pages));
    }
}