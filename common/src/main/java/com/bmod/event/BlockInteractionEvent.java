package com.bmod.event;

import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.bmod.registry.item.ModItems;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlockInteractionEvent {
    public static void initialize() {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, interactionHand, pos, dir) -> {
            Level level = player.getLevel();
            ItemStack stack = player.getItemInHand(interactionHand);

            if (stack.is(ModItems.EYEDROPPER.get()) && level.getBlockEntity(pos) instanceof PixelBlockEntity pixel) {
                doEyedropperClick(player, stack, pixel);
            }

            return EventResult.pass();
        });
    }

    public static void doEyedropperClick(Player player, ItemStack stack, PixelBlockEntity pixel) {
        if (player.isCrouching()) {
            stack.getOrCreateTag().putLong("blockPos", pixel.getBlockPos().asLong());
            return;
        }

        BlockPos blockPos = BlockPos.of(stack.getOrCreateTag().getLong("blockPos"));

        if (player.level.getBlockEntity(blockPos) instanceof PixelBlockEntity oldPixel)
        {
            int r = oldPixel.getColor()[0];
            int g = oldPixel.getColor()[1];
            int b = oldPixel.getColor()[2];

            pixel.setColor(r, g, b);
        }
    }
}
