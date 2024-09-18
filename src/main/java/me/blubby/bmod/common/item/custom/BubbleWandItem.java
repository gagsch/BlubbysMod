package me.blubby.bmod.common.item.custom;

import me.blubby.bmod.common.block.ModBlocks;
import me.blubby.bmod.common.item.ModCreativeTab;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;

public class BubbleWandItem extends ToolTipItem {
    public BubbleWandItem() {
        super(new Item.Properties().tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM).durability(-1), ToolTips.bubble_wand);
    }

    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = place(new BlockPlaceContext(context));
        if (!result.consumesAction() && isEdible()) {
            return use(context.getLevel(), context.getPlayer(), context.getHand()).getResult() == InteractionResult.CONSUME
                    ? InteractionResult.CONSUME_PARTIAL
                    : InteractionResult.FAIL;
        }
        return result;
    }

    @javax.annotation.Nullable
    protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState state = ModBlocks.BUBBLE_BLOCK.get().getStateForPlacement(context);
        return (state != null && canPlace(context, state)) ? state : null;
    }

    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        CollisionContext collisionContext = (context.getPlayer() == null) ? CollisionContext.empty() : CollisionContext.of(context.getPlayer());
        return state.canSurvive(context.getLevel(), context.getClickedPos()) && context.getLevel().isUnobstructed(state, context.getClickedPos(), collisionContext);
    }

    public InteractionResult place(BlockPlaceContext context) {
        if (!context.canPlace()) return InteractionResult.FAIL;

        BlockState state = getPlacementState(context);
        if (state == null || !context.getLevel().setBlock(context.getClickedPos(), state, 11)) return InteractionResult.FAIL;

        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        BlockState currentState = level.getBlockState(pos);

        if (currentState.is(state.getBlock())) {
            currentState = updateBlockStateFromTag(pos, level, itemStack, currentState);
            updateCustomBlockEntityTag(level, player, pos, itemStack);
            currentState.getBlock().setPlacedBy(level, pos, currentState, player, itemStack);

            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, pos, itemStack);
            }
        }

        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, currentState));
        SoundType soundType = currentState.getSoundType(level, pos, player);
        level.playSound(player, pos, soundType.getPlaceSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private BlockState updateBlockStateFromTag(BlockPos pos, Level level, ItemStack itemStack, BlockState state) {
        CompoundTag tag = itemStack.getTag();
        if (tag != null) {
            CompoundTag blockStateTag = tag.getCompound("BlockStateTag");
            StateDefinition<Block, BlockState> stateDef = state.getBlock().getStateDefinition();
            for (String key : blockStateTag.getAllKeys()) {
                Property<?> property = stateDef.getProperty(key);
                if (property != null) {
                    state = updateState(state, property, blockStateTag.get(key).getAsString());
                }
            }
        }
        if (!state.equals(level.getBlockState(pos))) {
            level.setBlock(pos, state, 2);
        }
        return state;
    }

    public static boolean updateCustomBlockEntityTag(Level level, @javax.annotation.Nullable Player player, BlockPos pos, ItemStack itemStack) {
        MinecraftServer server = level.getServer();
        if (server == null) return false;

        CompoundTag tag = itemStack.getTagElement("BlockEntityTag");
        if (tag != null) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity != null) {
                if (!level.isClientSide && blockEntity.onlyOpCanSetNbt() && (player == null || !player.canUseGameMasterBlocks())) {
                    return false;
                }
                CompoundTag existingTag = blockEntity.saveWithoutMetadata();
                existingTag.merge(tag);
                if (!existingTag.equals(blockEntity.saveWithoutMetadata())) {
                    blockEntity.load(existingTag);
                    blockEntity.setChanged();
                    return true;
                }
            }
        }
        return false;
    }

    private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> property, String value) {
        return property.getValue(value).map(v -> state.setValue(property, v)).orElse(state);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}