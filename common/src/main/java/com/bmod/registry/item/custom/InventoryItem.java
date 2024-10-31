package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.mob_effect.ModMobEffects;
import com.bmod.util.mixinUtils.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class InventoryItem extends ToolTipItem {
    public enum inventoryItems {
        LUCKY_ROCK,
        ANCIENT_RECIPE_BOOK
    };

    public InventoryItem(Properties properties, inventoryItems inventoryItem) {
        super(properties);
        this.inventoryItem = inventoryItem;
    }

    inventoryItems inventoryItem;

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean isSelected) {
        if (entity instanceof ServerPlayer serverPlayer)
        {
            switch (inventoryItem)
            {
                case LUCKY_ROCK:
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK, 19, 4, false, false));
                    break;
                case ANCIENT_RECIPE_BOOK:
                    CompoundTag playerData = ((IEntityDataSaver) serverPlayer).blubbysmod$getPersistentData();
                    int pagesDiscovered = playerData.getInt("ancient_recipes_unlocked");

                    stack.getOrCreateTag().putInt(BlubbysMod.MOD_ID + ":pages", pagesDiscovered);
                    break;
            }
        }
    }
}
