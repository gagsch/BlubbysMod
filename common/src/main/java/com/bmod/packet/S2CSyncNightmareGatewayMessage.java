package com.bmod.packet;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class S2CSyncNightmareGatewayMessage extends BaseS2CMessage {
    private final BlockPos blockPos;
    private final boolean hasGem;

    public S2CSyncNightmareGatewayMessage(BlockPos blockPos, boolean hasGem) {
        this.blockPos = blockPos;
        this.hasGem = hasGem;
    }

    public S2CSyncNightmareGatewayMessage(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        hasGem = buf.readBoolean();
    }

    @Override
    public MessageType getType() {
        return ModPackets.S2C_SYNC_NIGHTMARE_GATEWAY;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeBoolean(hasGem);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> CNightmareGatewayGemUpdate.handle(blockPos, hasGem));
    }
}