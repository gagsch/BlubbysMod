package com.bmod.forge.mixin;

import com.bmod.registry.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Shadow
    private Optional<BlockPos> lastClimbablePos;

    @Inject(method = "onClimbable", at = @At("TAIL"), cancellable = true)
    public void onClimbable(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof Player player)
        {
            if (player.getFeetBlockState().is(Blocks.COBWEB))
            {
                ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

                if (!boots.isEmpty()) {
                    int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.WEB_WALKER.get(), boots);

                    if (enchantmentLevel == 3) {
                        this.lastClimbablePos = Optional.of(player.blockPosition());

                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}
