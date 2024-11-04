package com.bmod.registry.item.custom;

import com.bmod.util.ContainerUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BaseAccessoryItem extends ToolTipItem implements IAccessoryItem {

    public BaseAccessoryItem() {
        super(DEFAULT_ACCESSORY_PROPERTIES);
    }

    @Override
    public void accessoryTick(@NotNull ServerLevel level, @NotNull ServerPlayer player) {

    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (player instanceof ServerPlayer)
        {
            SimpleContainer container = new SimpleContainer(5);
            ContainerUtils.loadContainerFromPlayer(container, player, "accessories");

            if (!ContainerUtils.playerAccessoriesHasItem(player, itemStack.getItem()))
            {
                for (int i = 0; i < container.getContainerSize(); i++) {
                    if (container.getItem(i).isEmpty()) {
                        container.setItem(i, new ItemStack(itemStack.getItem()));
                        itemStack.shrink(1);
                        container.setChanged();
                    }
                }
                ContainerUtils.saveContainerToPlayer(container, player, "accessories");
                return InteractionResultHolder.success(itemStack);
            }
        }
        return InteractionResultHolder.pass(itemStack);
    }
}
