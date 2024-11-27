package com.bmod.packet.C2S;

import com.bmod.packet.ModPackets;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class C2SSavePixel extends BaseC2SMessage {
    private final BlockPos blockPos;
    private final int r;
    private final int g;
    private final int b;

    public C2SSavePixel(PixelBlockEntity pixel, int r, int g, int b) {
        this.blockPos = pixel.getBlockPos();
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public C2SSavePixel(FriendlyByteBuf buf) {
        this.blockPos = buf.readBlockPos();
        this.r = buf.readVarInt();
        this.g = buf.readVarInt();
        this.b = buf.readVarInt();
    }

    @Override
    public MessageType getType() {
        return ModPackets.C2S_SAVE_PIXEL;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeVarInt(this.r);
        buf.writeVarInt(this.g);
        buf.writeVarInt(this.b);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> SSavePixelHandler.handle(context.getPlayer().getLevel(),
                context.getPlayer(), this.blockPos,
                this.r, this.g, this.b));
    }
}