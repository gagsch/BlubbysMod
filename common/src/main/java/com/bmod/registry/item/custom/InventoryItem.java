package com.bmod.registry.item.custom;

import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.world.ModDimensions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.NotNull;

public class InventoryItem extends ToolTipItem {
    public enum inventoryItems {
        LuckyRock,
        VoidLantern
    };

    public InventoryItem(Properties properties, inventoryItems inventoryItem, ToolTipItem.ToolTips toolTips) {
        super(properties, toolTips);
        this.inventoryItem = inventoryItem;
    }

    inventoryItems inventoryItem;

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean isSelected) {
        if (entity instanceof ServerPlayer serverPlayer)
        {
            switch (inventoryItem)
            {
                case LuckyRock:
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK, 39, 4, false, false));
                    break;
                case VoidLantern:
                    break;
            }
        }
    }
}
