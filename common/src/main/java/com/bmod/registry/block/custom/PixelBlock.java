package com.bmod.registry.block.custom;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.screen.PixelPickerScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class PixelBlock extends BaseEntityBlock {
    public static final BooleanProperty UPDATE = BooleanProperty.create("update");

    public PixelBlock() {
        super(Properties.copy(Blocks.GLASS));

        this.registerDefaultState(this.defaultBlockState().setValue(UPDATE, false));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide || player.getItemInHand(interactionHand).is(ModItems.EYEDROPPER.get()))
            return InteractionResult.SUCCESS;

        if (level.getBlockEntity(blockPos) instanceof PixelBlockEntity pixel) {
            Minecraft.getInstance().setScreen(new PixelPickerScreen(pixel));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity player, ItemStack stack) {
        super.setPlacedBy(level, pos, state, player, stack);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof PixelBlockEntity pixelBlockEntity) {
                pixelBlockEntity.setOwner(serverPlayer);
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PixelBlockEntity(blockPos, blockState);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UPDATE);
    }
}
