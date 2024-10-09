package com.bmod.registry.item.custom;

import com.bmod.BlubbysMod;
import com.bmod.registry.item.ModCreativeTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CursedGemItem extends InventoryItem {

    public CursedGemItem() {
        super(
                new Properties()
                        .tab(ModCreativeTab.BLUBBYS_TAB_OF_DOOM)
                        .durability(-1)
                        .stacksTo(1),
                inventoryItems.CURSED_GEM,
                tooltips.VOODOO_DOLL
        );
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        ItemStack item = player.getItemInHand(interactionHand);

        if (livingEntity instanceof ServerPlayer usedOn)
        {
            if (player instanceof ServerPlayer)
            {
                CompoundTag tag = item.getOrCreateTag();
                tag.putUUID(BlubbysMod.MOD_ID + ":player_link", usedOn.getUUID());
                item.setTag(tag);

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

}
