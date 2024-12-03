package com.bmod.registry.block.block_entity.custom;

import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.block.custom.FrogExecutorBlock;
import com.bmod.registry.block.custom.PixelBlock;
import com.bmod.util.frog.FrogParser;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class FrogExecutorBlockEntity extends BlockEntity {
    private String code = "";
    private static final UUID DEFAULT = new UUID(0, 0);
    private UUID owner = DEFAULT;

    public FrogExecutorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.FROG_EXECUTOR_BLOCK_ENTITY_TYPE.get(), blockPos, blockState);
    }

    public void setOwner(ServerPlayer player) {
        this.owner = player.getUUID();
        this.setChanged();
    }

    public boolean isOwner(Player player) {
        return player.getUUID().equals(this.owner) || this.owner.equals(DEFAULT);
    }

    public void setCode(Level level, String code) {
        if (level instanceof ServerLevel serverLevel)
        {
            FrogParser frogParser = new FrogParser(serverLevel);

            try {
                frogParser.makeStartBytes();
                frogParser.makeInstructions(code);
                frogParser.runInstructions();
            } catch (Exception e) {
                System.out.println("An error occurred in the Frog Executor Block: " + e.getMessage());
            }
        }

        this.code = code;

        level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(FrogExecutorBlock.UPDATE, !this.getBlockState().getValue(FrogExecutorBlock.UPDATE)));
        this.setChanged();
    }

    public String getCode() {
        return code;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.code = compoundTag.getString("code");
        this.owner = compoundTag.getUUID("ownerUUID");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putString("code", this.code);
        compoundTag.putUUID("ownerUUID", this.owner);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.putString("code", this.code);
        tag.putUUID("ownerUUID", this.owner);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
