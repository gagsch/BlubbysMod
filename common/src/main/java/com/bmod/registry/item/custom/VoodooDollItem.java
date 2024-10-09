package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.ModSounds;
import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class VoodooDollItem extends InventoryItem {
    public VoodooDollItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(1),
                inventoryItems.VOODOO_DOLL,
                tooltips.VOODOO_DOLL
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer)
        {
            CompoundTag tag = item.getOrCreateTag();

            if (tag.getInt(BlubbysMod.MOD_ID + ":pin") == 1) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.PIN_PULL.get(), SoundSource.PLAYERS,
                        1.0F, 1.0F);
                tag.putInt(BlubbysMod.MOD_ID + ":pin", 0);
            }
            else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        ModSounds.PIN_PUSH.get(), SoundSource.PLAYERS,
                        1.0F, 1.0F);
                tag.putInt(BlubbysMod.MOD_ID + ":pin", 1);
                serverPlayer.hurt(DamageSource.MAGIC, 1);
            }

            item.setTag(tag);

            return InteractionResultHolder.success(item);
        }
        return InteractionResultHolder.pass(item);
    }
}
