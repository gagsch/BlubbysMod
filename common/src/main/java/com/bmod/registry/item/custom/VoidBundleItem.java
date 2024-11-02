package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.menu.container.VoidBundleMenu;
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

public class VoidBundleItem extends ToolTipItem {
    public VoidBundleItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        if (!player.level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            MenuRegistry.openMenu(serverPlayer, new MenuProvider() {
                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player playerEntity) {
                    return new VoidBundleMenu(id, playerInventory, new SimpleContainer(54));
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