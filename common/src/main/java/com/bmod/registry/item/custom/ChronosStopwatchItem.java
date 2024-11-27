package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ChronosStopwatchItem extends ToolTipItem {
    public ChronosStopwatchItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_MOD)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag tag = itemStack.getOrCreateTag();
            if (tag.contains("position") && tag.contains("level") && tag.getString("level").equals(serverPlayer.getLevel().dimension().location().getPath()))
            {
                long[] position = tag.getLongArray("position");

                serverPlayer.teleportTo(position[0], position[1], position[2]);

                tag.remove("position");
                tag.remove("level");

                serverPlayer.getCooldowns().addCooldown(this, 1200);
            }
            else
            {
                long[] position = {(long) player.position().x, (long) player.position().y, (long) player.position().z};

                tag.putLongArray("position",  position);
                tag.putString("level",  serverPlayer.getLevel().dimension().location().getPath());

                serverPlayer.getCooldowns().addCooldown(this, 20);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, !level.isClientSide());
    }
}
