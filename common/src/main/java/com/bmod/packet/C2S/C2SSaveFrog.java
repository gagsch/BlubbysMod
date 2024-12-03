package com.bmod.packet.C2S;

import com.bmod.packet.ModPackets;
import com.bmod.registry.block.block_entity.custom.FrogExecutorBlockEntity;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class C2SSaveFrog extends BaseC2SMessage {
    private final BlockPos blockPos;
    private final String code;

    public C2SSaveFrog(FrogExecutorBlockEntity frog, String code) {
        this.blockPos = frog.getBlockPos();
        this.code = code;
    }

    public C2SSaveFrog(FriendlyByteBuf buf) {
        this.blockPos = buf.readBlockPos();
        this.code = buf.readUtf();
    }

    @Override
    public MessageType getType() {
        return ModPackets.C2S_SAVE_FROG;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeUtf(this.code);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> SSaveCodeHandler.handle(context.getPlayer().getLevel(),
                context.getPlayer(), this.blockPos, this.code));
    }
}