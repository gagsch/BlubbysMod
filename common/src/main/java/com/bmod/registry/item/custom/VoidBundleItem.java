package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.screen.menu.VoidBundleMenu;
import net.minecraft.network.chat.Component;
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
                .tab(ModCreativeTab.BLUBBYS_MOD)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        player.openMenu(new MenuProvider() {
            @Override
            public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInventory, @NotNull Player playerEntity) {
                return new VoidBundleMenu(id, playerInventory, new SimpleContainer(54));
            }

            @Override
            public @NotNull Component getDisplayName() {
                return player.getDisplayName();
            }
        });

        return super.use(level, player, hand);
    }
}