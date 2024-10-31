package com.bmod.packet;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class C2SRequestGatewayMessage extends BaseC2SMessage {
    private final BlockPos blockPos;
    private final boolean hasGem;

    public C2SRequestGatewayMessage(BlockPos blockPos, boolean hasGem) {
        this.blockPos = blockPos;
        this.hasGem = hasGem;
    }

    public C2SRequestGatewayMessage(FriendlyByteBuf buf) {
        blockPos = buf.readBlockPos();
        hasGem = buf.readBoolean();
    }

    @Override
    public MessageType getType() {
        return ModPackets.C2S_REQUEST_NIGHTMARE_GATEWAY;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(blockPos);
        buf.writeBoolean(hasGem);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> SNightmareGatewaySync.handle(Objects.requireNonNull(context.getPlayer().getServer()), context.getPlayer().level.dimension().location(), blockPos, hasGem, context.getPlayer()));
    }
}
