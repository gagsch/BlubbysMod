package com.bmod.registry.item.custom;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ChronosClockItem extends ToolTipItem {
    public ChronosClockItem(Item.Properties properties) {
        super(properties, ToolTips.chronos_clock);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            MinecraftServer server = player.getServer();
            assert server != null;
            ServerLevel serverLevel = server.getLevel(Level.OVERWORLD);

            assert serverLevel != null;
            serverLevel.setDayTime(serverLevel.getDayTime()+250);
        }
        return super.use(level, player, hand);
    }
}