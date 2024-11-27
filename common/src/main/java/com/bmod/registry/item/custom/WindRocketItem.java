package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WindRocketItem extends ToolTipItem {
    public WindRocketItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_MOD)
                .durability(-1)
                .stacksTo(1));
    }

    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (player.isFallFlying()) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (!level.isClientSide) {
                FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(level, itemStack, player);
                level.addFreshEntity(fireworkRocketEntity);

                player.getCooldowns().addCooldown(itemStack.getItem(), 120);
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            return InteractionResultHolder.sidedSuccess(player.getItemInHand(interactionHand), level.isClientSide());
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
        }
    }

    public @NotNull ItemStack getDefaultInstance() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.getOrCreateTag().putByte("Flight", (byte)1);
        return itemStack;
    }
}
