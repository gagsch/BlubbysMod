package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import com.bmod.registry.menu.AncientGuideScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AncientGuideBookItem extends ToolTipItem {
    public AncientGuideBookItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (level.isClientSide()) {
            Minecraft.getInstance().setScreen(new AncientGuideScreen());

            return InteractionResultHolder.success(item);
        }

        return InteractionResultHolder.fail(item);
    }
}
