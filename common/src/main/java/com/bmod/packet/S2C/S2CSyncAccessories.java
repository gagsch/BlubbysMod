package com.bmod.packet.S2C;

import com.bmod.packet.ModPackets;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;

public class S2CSyncAccessories extends BaseS2CMessage {
    private final Container accessories;

    public S2CSyncAccessories(Container accessories) {
        this.accessories = accessories;
    }

    public S2CSyncAccessories(FriendlyByteBuf buf) {
        this.accessories = new SimpleContainer(5);

        for (int i = 0; i < 5; i++) {
            this.accessories.setItem(i, buf.readItem());
        }
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        for (int i = 0; i < 5; i++)
        {
            buf.writeItem(this.accessories.getItem(i));
        }
    }

    @Override
    public MessageType getType() {
        return ModPackets.S2C_SYNC_ACCESSORIES;
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        context.queue(() -> CSyncAccessoryHandler.handle(accessories));
    }
}