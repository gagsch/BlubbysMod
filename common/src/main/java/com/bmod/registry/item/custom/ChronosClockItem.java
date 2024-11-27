package com.bmod.registry.item.custom;

import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChronosClockItem extends ToolTipItem {
    private long targetTime = 0;

    public ChronosClockItem() {
        super(new Properties()
                .tab(ModCreativeTab.BLUBBYS_MOD)
                .durability(-1)
                .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        targetTime = 13;

        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if (targetTime > 0)
        {
            if (level instanceof ServerLevel serverLevel)
            {
                serverLevel.setDayTime(serverLevel.getDayTime() + 45);
            }
            else if (level instanceof ClientLevel clientLevel)
            {
                clientLevel.setDayTime(clientLevel.getDayTime() + 45);
            }
        }
        targetTime--;
    }
}