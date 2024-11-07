package com.bmod.forge.mixin;

import com.bmod.registry.enchantment.ModEnchantments;
import com.bmod.registry.item.custom.IAccessoryItem;
import com.bmod.util.ContainerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

    @Shadow
    private Optional<BlockPos> lastClimbablePos;

    @Inject(method = "rideableUnderWater", at = @At("RETURN"), cancellable = true)
    public void rideableUnderWater(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof Player player) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci)
    {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof ServerPlayer player)
        {
            SimpleContainer container = new SimpleContainer(5);
            ContainerUtils.loadContainerFromPlayer(container, player, "accessories");

            for (int i = 0; i < container.getContainerSize(); i++)
            {
                if (container.getItem(i).getItem() instanceof IAccessoryItem accessoryItem)
                {
                    accessoryItem.accessoryTick(player.getLevel(), player);
                }
            }
        }
    }

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