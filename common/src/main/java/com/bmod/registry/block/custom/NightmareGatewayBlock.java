package com.bmod.registry.block.custom;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.block.block_entity.ModBlockEntityTypes;
import com.bmod.registry.block.block_entity.custom.NightmareGatewayBlockEntity;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.world.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NightmareGatewayBlock extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public NightmareGatewayBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .lightLevel((state) -> state.getValue(POWERED) ? 10 : 0));

        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return makeShape();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.0625, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.0625, 0.125, 0.875, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.1875, 0.1875, 0.8125, 0.8125, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.8125, 0.125, 0.875, 0.9375, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.9375, 0.0625, 0.9375, 1, 0.9375), BooleanOp.OR);

        return shape;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack stack = player.getItemInHand(interactionHand);

        if (!(level instanceof ServerLevel serverLevel && player instanceof ServerPlayer serverPlayer)) {
            if (stack.is(ModItems.CURSED_GEM.get())) {
                level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1, 1, true);
            }
            return InteractionResult.sidedSuccess(true);
        }

        if (serverLevel.getBlockEntity(blockPos) instanceof NightmareGatewayBlockEntity newBlockEntity) {
            if (!blockState.getValue(POWERED) && stack.is(ModItems.CURSED_GEM.get())) {
                stack.shrink(1);

                level.setBlock(blockPos, blockState.setValue(POWERED, true), 3);

                level.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1, 1, true);
            }
            else if (blockState.getValue(POWERED)) {
                ServerLevel dim = serverLevel.getServer().getLevel(level.dimension() == ModDimensions.BLYDIM_KEY ? Level.OVERWORLD : ModDimensions.BLYDIM_KEY);
                BlockPos pos = new BlockPos(newBlockEntity.teleportPos[0], newBlockEntity.teleportPos[1], newBlockEntity.teleportPos[2]);

                assert dim != null;
                if (!dim.getBlockState(pos).is(ModBlocks.NIGHTMARE_GATEWAY.get())) {
                    pos = newBlockEntity.prepareSafeTeleport(dim);
                }

                serverPlayer.teleportTo(dim,
                        pos.getX(), pos.getY(), pos.getZ(),
                        0, 0);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (!level.isClientSide())
            return null;

        return createTickerHelper(blockEntityType, ModBlockEntityTypes.NIGHTMARE_GATEWAY_ENTITY_TYPE.get(),
                (clientLevel, position, state, blockEntity) -> blockEntity.tick(clientLevel, position));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NightmareGatewayBlockEntity(blockPos, blockState);
    }
}