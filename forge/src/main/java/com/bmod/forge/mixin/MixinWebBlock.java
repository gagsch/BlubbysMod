package com.bmod.forge.mixin;

import com.bmod.registry.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WebBlock.class)
public class MixinWebBlock {

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, CallbackInfo ci) {
        if (entity instanceof Player player) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

            if (!boots.isEmpty()) {
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.WEB_WALKER.get(), boots);

                switch (enchantmentLevel)
                {
                    case 1:
                        entity.makeStuckInBlock(blockState, new Vec3(0.5, 0.5, 0.5));
                        ci.cancel();
                        break;
                    case 2:
                        entity.makeStuckInBlock(blockState, new Vec3(0.999, 1, 0.999));
                        ci.cancel();
                        break;
                    case 3:
                        ci.cancel();
                        break;
                }
            }
        }
    }
}