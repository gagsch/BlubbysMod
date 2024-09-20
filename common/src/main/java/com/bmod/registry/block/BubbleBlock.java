package com.bmod.registry.block;

import com.bmod.registry.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BubbleBlock extends Block {
    public static final BooleanProperty HAS_WATER_NEIGHBOR;

    public BubbleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HAS_WATER_NEIGHBOR, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HAS_WATER_NEIGHBOR, checkForWater(context.getLevel(), context.getClickedPos()));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (shouldIgnoreSelection(context)) {
            return Shapes.empty();
        }
        return super.getShape(state, world, pos, context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_WATER_NEIGHBOR);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        serverLevel.setBlock(blockPos, blockState.setValue(HAS_WATER_NEIGHBOR, checkForWater(serverLevel, blockPos)), 3);
    }

    @Override
    public void neighborChanged(BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean isMoving) {
        boolean hasWaterNeighbor = checkForWater(world, pos);

        if (state.getValue(HAS_WATER_NEIGHBOR) != hasWaterNeighbor) {
            world.setBlock(pos, state.setValue(HAS_WATER_NEIGHBOR, hasWaterNeighbor), 3);
        }
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return blockState.getValue(HAS_WATER_NEIGHBOR);
    }

    @Override
    public int getSignal(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull Direction direction) {
        return blockState.getValue(HAS_WATER_NEIGHBOR) ? 15 : 0;
    }

    public boolean checkForWater(Level world, BlockPos pos) {
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.is(Blocks.WATER)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldIgnoreSelection(CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity instanceof Player player) {
                if (player.getMainHandItem().getItem() != ModItems.BUBBLE_WAND.get() && player.getOffhandItem().getItem() != ModItems.BUBBLE_WAND.get()) {
                    return player.level.getBlockState(player.blockPosition().above()) == ModBlocks.BUBBLE_BLOCK.get().defaultBlockState();
                }
            }
        }
        return false;
    }

    static {
        HAS_WATER_NEIGHBOR = BooleanProperty.create("has_water_neighbor");
    }
}