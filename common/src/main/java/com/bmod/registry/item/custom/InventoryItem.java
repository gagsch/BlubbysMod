package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.ModSounds;
import com.bmod.registry.mob_effect.ModMobEffects;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
        VOODOO_DOLL
    };

    public InventoryItem(Properties properties, inventoryItems inventoryItem, tooltips toolTips) {
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
                case LUCKY_ROCK:
                    serverPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK, 19, 4, false, false));
                    break;
                case VOODOO_DOLL:
                    if (stack.getOrCreateTag().getInt(BlubbysMod.MOD_ID + ":pin") == 1)
                    {
                        serverPlayer.addEffect(new MobEffectInstance(ModMobEffects.HORRIFIED.get(), 19, 0, false, false));
                    }
                    break;
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer)
        {
            if (inventoryItem == inventoryItems.VOODOO_DOLL)
            {
                CompoundTag nbt = item.getOrCreateTag();

                if (nbt.getInt(BlubbysMod.MOD_ID + ":pin") == 1) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            ModSounds.PIN_PULL.get(), SoundSource.PLAYERS,
                            1.0F, 1.0F);
                    nbt.putInt(BlubbysMod.MOD_ID + ":pin", 0);
                }
                else {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(),
                            ModSounds.PIN_PUSH.get(), SoundSource.PLAYERS,
                            1.0F, 1.0F);
                    nbt.putInt(BlubbysMod.MOD_ID + ":pin", 1);
                }

                item.setTag(nbt);

                return InteractionResultHolder.success(item);
            }
        }
        return InteractionResultHolder.pass(item);
    }
}
