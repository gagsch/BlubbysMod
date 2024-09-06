package me.blubby.bmod.common.item.custom;

import me.blubby.bmod.server.container.EnderChestUpgradeContainer;
import me.blubby.bmod.server.container.SecondEnderChestContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EnderBundleItem extends ToolTipItem {
    public EnderBundleItem(Properties properties) {
        super(properties, ToolTips.ender_bundle);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.level.isClientSide && player instanceof ServerPlayer) {
            NetworkHooks.openScreen((ServerPlayer) player, new MenuProvider() {
                @Override
                public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {
                    return new EnderChestUpgradeContainer(id, playerInventory, new SecondEnderChestContainer(54));
                }

                @Override
                public Component getDisplayName() {
                    return player.getDisplayName();
                }
            });
        }
        return super.use(level, player, hand);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;  // This makes the item stack to a maximum of one.
    }
}