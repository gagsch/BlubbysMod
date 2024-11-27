package com.bmod.registry.block.block_entity.custom;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.custom.DimensionGatewayBlock;
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

public class DimensionGatewayBlockEntity extends BlockEntity {
    public boolean powered = false;
    public int[] teleportPos = {0, 0, 0};

    public DimensionGatewayBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.DIMENSION_GATEWAY_ENTITY_TYPE.get(), blockPos, blockState);
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
        if (level.getBlockState(pos.below()).is(ModBlocks.DIMENSION_GATEWAY.get()))
        {
            return pos.move(Direction.DOWN).immutable();
        }

        teleportPos[0] = pos.getX();
        teleportPos[1] = pos.getY();
        teleportPos[2] = pos.getZ();

        BlockPos nearestPos = BlockPos.findClosestMatch(
                new BlockPos(teleportPos[0], teleportPos[1], teleportPos[2]),
                5, 5,
                pos1 -> level.getBlockState(pos1).is(ModBlocks.DIMENSION_GATEWAY.get())
        ).orElse(null);

        if (nearestPos != null) {
            teleportPos[0] = nearestPos.getX();
            teleportPos[1] = nearestPos.getY();
            teleportPos[2] = nearestPos.getZ();
            return nearestPos;
        }

        BlockState blockState = ModBlocks.DIMENSION_GATEWAY.get().defaultBlockState().setValue(DimensionGatewayBlock.POWERED, true);

        DimensionGatewayBlockEntity blockEntity = new DimensionGatewayBlockEntity(pos, blockState);
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
        BlockState blockState = level.getBlockState(blockPos);
        if (!(blockState.getBlock() instanceof DimensionGatewayBlock))
        {
            return;
        }
        this.powered = blockState.getValue(DimensionGatewayBlock.POWERED);
        if (this.powered && level.random.nextInt(5) == 0)
        {
            double offsetX = (level.random.nextDouble() - 0.5) * 2;
            double offsetY = (level.random.nextDouble() - 0.5) * 2;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2;

            level.addParticle(ParticleTypes.PORTAL,
                    this.getBlockPos().getX() + 0.5f + offsetX,
                    this.getBlockPos().getY() + 0.5f + offsetY,
                    this.getBlockPos().getZ() + 0.5f + offsetZ,
                    0, 0, 0);

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
