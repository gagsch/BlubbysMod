package me.blubby.bmod.common.item.custom;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChronosClockItem extends ToolTipItem {
    public ChronosClockItem(Item.Properties properties) {
        super(properties, ToolTips.chronos_clock);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            MinecraftServer server = player.getServer();
            ServerLevel serverLevel = server.getLevel(Level.OVERWORLD);

            serverLevel.setDayTime(serverLevel.getDayTime()+250);
        }
        return super.use(level, player, hand);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}