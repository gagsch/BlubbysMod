package com.bmod.packet;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class S2CEntityRidingMessage extends BaseS2CMessage {
    private final UUID playerUUID;
    private final int entityId;

    public S2CEntityRidingMessage(ServerPlayer player, Entity entity) {
        this.playerUUID = player.getUUID();
        this.entityId = entity.getId();
    }

    public S2CEntityRidingMessage(FriendlyByteBuf buf) {
        this.playerUUID = buf.readUUID();
        this.entityId = buf.readVarInt();
    }

    @Override
    public MessageType getType() {
        return ModPackets.S2C_ENTITY_RIDING;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        buf.writeVarInt(entityId);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        Entity entity = context.getPlayer().getLevel().getEntity(entityId);
        Player player = context.getPlayer().getLevel().getPlayerByUUID(playerUUID);

        context.queue(() -> CEntityRidingHandler.handle(player, entity));
    }
}