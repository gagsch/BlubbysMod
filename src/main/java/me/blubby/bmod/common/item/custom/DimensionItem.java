package me.blubby.bmod.common.item.custom;

import me.blubby.bmod.server.world.dimension.ModDimensions;
import me.blubby.bmod.server.world.portal.ModTeleporter;
import me.blubby.bmod.utils.PositionUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DimensionItem extends Item {
    public DimensionItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            ResourceKey<Level> targetDimension;
            BlockPos targetPosition;

            if (serverPlayer.getLevel().dimension() == ModDimensions.BLYDIM_KEY)
            {
                targetDimension = Level.OVERWORLD;
                targetPosition = PositionUtils.loadPositionFromPlayer(player, false);
                PositionUtils.savePositionToPlayer(player.blockPosition(), player, true);
            }
            else
            {
                targetDimension = ModDimensions.BLYDIM_KEY;
                targetPosition = PositionUtils.loadPositionFromPlayer(player, true);
                PositionUtils.savePositionToPlayer(player.blockPosition(), player, false);
            }

            serverPlayer.changeDimension(level.getServer().getLevel(targetDimension), new ModTeleporter(targetPosition));
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
