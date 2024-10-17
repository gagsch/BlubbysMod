package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CursedGemItem extends InventoryItem {

    private boolean usedOnEntity = false;

    public CursedGemItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(1),
                inventoryItems.CURSED_GEM
        );
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer)
        {
            if (livingEntity instanceof ServerPlayer usedOn)
            {
                this.usedOnEntity = true;
                player.getLevel().playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS,
                        1.0F, 1.0F);

                CompoundTag tag = item.getOrCreateTag();
                tag.putUUID(BlubbysMod.MOD_ID + ":player_link", usedOn.getUUID());
                item.setTag(tag);

                return InteractionResult.SUCCESS;
            }
            else
            {
                this.use(serverPlayer.getLevel(), serverPlayer, interactionHand);
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (player instanceof ServerPlayer serverPlayer)
        {
            if (this.usedOnEntity)
            {
                this.usedOnEntity = false;
                return InteractionResultHolder.fail(item);
            }

            serverPlayer.getLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(),
                    SoundEvents.ARMOR_EQUIP_NETHERITE, SoundSource.PLAYERS,
                    1.0F, 1.0F);

            CompoundTag tag = item.getOrCreateTag();
            tag.remove(BlubbysMod.MOD_ID + ":player_link");
            item.setTag(tag);

            return InteractionResultHolder.success(item);
        }

        return InteractionResultHolder.fail(item);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();

        return tag.hasUUID(BlubbysMod.MOD_ID + ":player_link");
    }
}
