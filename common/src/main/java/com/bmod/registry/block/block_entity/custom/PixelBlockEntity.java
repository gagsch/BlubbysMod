package com.bmod.registry.block.block_entity.custom;

import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.block.custom.PixelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PixelBlockEntity extends BlockEntity {
    private int[] color = {0, 0, 0};
    private UUID owner;

    public PixelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.PIXEL_BLOCK_ENTITY_TYPE.get(), blockPos, blockState);
    }

    public void setColor(Level level, int red, int green, int blue) {
        this.color[0] = red % 256;
        this.color[1] = green % 256;
        this.color[2] = blue % 256;
        level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(PixelBlock.UPDATE, !this.getBlockState().getValue(PixelBlock.UPDATE)));
        setChanged();
    }

    public static void setColors(Level level, int red, int green, int blue, BlockPos... blockPositions) {
        for (BlockPos blockPos : blockPositions)
        {
            if (level.getBlockEntity(blockPos) instanceof PixelBlockEntity pixel)
            {
                pixel.color[0] = red % 256;
                pixel.color[1] = green % 256;
                pixel.color[2] = blue % 256;
                level.setBlockAndUpdate(blockPos, pixel.getBlockState().setValue(PixelBlock.UPDATE, !pixel.getBlockState().getValue(PixelBlock.UPDATE)));
                pixel.setChanged();
            }
        }
    }

    public int[] getColor() {
        return color;
    }

    public void setOwner(ServerPlayer player) {
        this.owner = player.getUUID();
        this.setChanged();
    }

    public boolean isOwner(Player player) {
        return player.getUUID().equals(this.owner);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.color = compoundTag.getIntArray("color");
        this.owner = compoundTag.getUUID("ownerUUID");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putIntArray("color", this.color);
        compoundTag.putUUID("ownerUUID", this.owner);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.putIntArray("color", this.color);
        tag.putUUID("ownerUUID", this.owner);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
