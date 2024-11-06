package com.bmod.registry.block.block_entity.custom;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.custom.NightmareGatewayBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.bmod.registry.block.block_entity.ModBlockEntityTypes;

public class NightmareGatewayBlockEntity extends BlockEntity {
    public boolean blockState = false;
    public int[] teleportPos = {0, 0, 0};

    public NightmareGatewayBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.NIGHTMARE_GATEWAY_ENTITY_TYPE.get(), blockPos, blockState);
    }

    public BlockPos prepareSafeTeleport(ServerLevel level) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(this.getBlockPos().getX(), 160, this.getBlockPos().getZ());

        while (!level.getBlockState(pos.below()).isSolidRender(level, pos.below())) {
            pos.move(Direction.DOWN);

            if (pos.getY() < -64 && !level.isWaterAt(pos.below(2)) || level.getBlockState(pos.below(2)).is(BlockTags.LEAVES)) {
                pos.move(Direction.UP, 160).move(Direction.EAST);
            }
            else if (level.isWaterAt(pos.below(2))) {
                while (level.isWaterAt(pos.below(2))) {
                    pos.move(Direction.EAST);
                }
            }
        }
        if (level.getBlockState(pos.below()).is(ModBlocks.NIGHTMARE_GATEWAY.get()))
        {
            return pos.move(Direction.DOWN).immutable();
        }

        teleportPos[0] = pos.getX();
        teleportPos[1] = pos.getY();
        teleportPos[2] = pos.getZ();

        BlockPos nearestPos = BlockPos.findClosestMatch(
                new BlockPos(teleportPos[0], teleportPos[1], teleportPos[2]),
                5, 5,
                pos1 -> level.getBlockState(pos1).is(ModBlocks.NIGHTMARE_GATEWAY.get())
        ).orElse(null);

        if (nearestPos != null) {
            teleportPos[0] = nearestPos.getX();
            teleportPos[1] = nearestPos.getY();
            teleportPos[2] = nearestPos.getZ();
            return nearestPos;
        }

        BlockState blockState = ModBlocks.NIGHTMARE_GATEWAY.get().defaultBlockState().setValue(NightmareGatewayBlock.POWERED, true);

        NightmareGatewayBlockEntity blockEntity = new NightmareGatewayBlockEntity(pos, blockState);
        blockEntity.teleportPos = new int[] {this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()};

        level.setBlockAndUpdate(pos, blockState);
        level.setBlockEntity(blockEntity);

        return pos;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        this.teleportPos = compoundTag.getIntArray("teleportPos");
    }

    public void tick(Level level, BlockPos blockPos)
    {
        this.blockState = level.getBlockState(blockPos).getValue(NightmareGatewayBlock.POWERED);
        if (blockState && level.random.nextInt(5) == 0)
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
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putIntArray("teleportPos", this.teleportPos);
        super.saveAdditional(compoundTag);
    }
}
