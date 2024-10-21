package com.bmod.packet;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;

public class S2COpenRecipeBookMessage extends BaseS2CMessage {
    private final int pages;

    public S2COpenRecipeBookMessage(int pages) {
        this.pages = pages;
    }

    public S2COpenRecipeBookMessage(FriendlyByteBuf buf) {
        pages = buf.readVarInt();
    }

    @Override
    public MessageType getType() {
        return ModPackets.S2C_OPEN_RECIPE_BOOK;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(pages);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> COpenRecipeBookHandler.handle(pages));
    }
}