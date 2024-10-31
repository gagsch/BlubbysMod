package com.bmod.registry.block.block_entity.custom;

import com.bmod.packet.C2SRequestGatewayMessage;
import com.bmod.packet.S2CSyncNightmareGatewayMessage;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.bmod.registry.block.block_entity.ModBlockEntityTypes;

public class NightmareGatewayBlockEntity extends BlockEntity {
    public boolean updated = false;
    public boolean hasGem;
    public int[] teleportPos = {0, 0, 0};

    public NightmareGatewayBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.NIGHTMARE_GATEWAY_ENTITY_TYPE.get(), blockPos, blockState);
    }

    public void drops()
    {
        if (this.level instanceof ServerLevel serverLevel && hasGem)
        {
            this.hasGem = false;
            this.level.addFreshEntity(new ItemEntity(serverLevel,
                    this.getBlockPos().getX() + 0.5f,
                    this.getBlockPos().above().getY(),
                    this.getBlockPos().getZ() + 0.5f,
                    new ItemStack(ModItems.CURSED_GEM.get())));

            new S2CSyncNightmareGatewayMessage(this.getBlockPos(), false).sendToLevel(serverLevel);
        }
    }

    public BlockPos prepareSafeTeleport(ServerLevel level) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(this.getBlockPos().getX(), 200, this.getBlockPos().getZ());

        while (!level.getBlockState(pos.below()).isSolidRender(level, pos.below())) {
            pos.move(Direction.DOWN);

            if (pos.getY() < 40 && !level.isWaterAt(pos.below(2)) || level.getBlockState(pos.below(2)).is(BlockTags.LEAVES)) {
                pos.move(Direction.UP, 160).move(Direction.EAST);
            }
            else if (level.isWaterAt(pos.below(2))) {
                while (level.isWaterAt(pos.below(2))) {
                    pos.move(Direction.EAST);
                }
            }
        }

        BlockState blockState = ModBlocks.NIGHTMARE_GATEWAY.get().defaultBlockState();

        NightmareGatewayBlockEntity blockEntity = new NightmareGatewayBlockEntity(pos, blockState);
        blockEntity.teleportPos = new int[] {this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()};
        blockEntity.hasGem = true;

        teleportPos[0] = pos.getX();
        teleportPos[1] = pos.getY();
        teleportPos[2] = pos.getZ();

        level.setBlockAndUpdate(pos, blockState);
        level.setBlockEntity(blockEntity);

        return pos;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.hasGem = compoundTag.getBoolean("hasGem");
        this.teleportPos = compoundTag.getIntArray("teleportPos");
    }

    public void tick(Level level, BlockPos blockPos)
    {
        if (this.hasGem && level.random.nextInt(5) == 0)
        {
            double offsetX = (level.random.nextDouble() - 0.5) * 2;
            double offsetY = (level.random.nextDouble() - 0.5) * 2;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2;

            level.addParticle(
                    ParticleTypes.PORTAL,
                    this.getBlockPos().getX() + 0.5f + offsetX,
                    this.getBlockPos().getY() + 0.5f + offsetY,
                    this.getBlockPos().getZ() + 0.5f + offsetZ,
                    0, 0, 0
            );

            if (level.random.nextInt(40) == 0)
            {
                level.playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 1, 1, true);
            }
        }

        if (!updated) {
            updated = true;
            new C2SRequestGatewayMessage(blockPos, this.hasGem).sendToServer();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putBoolean("hasGem", this.hasGem);
        compoundTag.putIntArray("teleportPos", this.teleportPos);
        super.saveAdditional(compoundTag);
    }
}
