package com.bmod.registry.item.custom;

import com.bmod.registry.menu.container.EnderChestUpgradeMenu;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EnderBundleItem extends ToolTipItem {
    public EnderBundleItem(Properties properties) {
        super(properties, ToolTips.ender_bundle);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        if (!player.level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            MenuRegistry.openMenu(serverPlayer, new MenuProvider() {
                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player playerEntity) {
                    // return ModMenus.ENDER_CHEST_UPGRADE_MENU.get().create(id, playerInventory);
                    return new EnderChestUpgradeMenu(id, playerInventory, new SimpleContainer(54));
                }

                @Override
                public @NotNull Component getDisplayName() {
                    return serverPlayer.getDisplayName();
                }
            });
        }
        return super.use(level, player, hand);
    }
}