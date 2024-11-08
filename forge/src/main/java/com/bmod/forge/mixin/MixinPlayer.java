package com.bmod.forge.mixin;

import com.bmod.registry.attribute.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Player.class)
public class MixinPlayer {
    @Inject(method = "getDigSpeed", at = @At("RETURN"), cancellable = true, remap = false)
    public void getDigSpeed(BlockState arg, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof Player player) {
            float baseSpeed = cir.getReturnValue();

            AttributeInstance attributeInstance = player.getAttribute(ModAttributes.DIGGING_SPEED.get());
            if (attributeInstance != null) {
                double attributeValue = attributeInstance.getValue();
                System.out.println("Attribute value: " + attributeValue);
                baseSpeed *= (float) attributeValue;
                System.out.println("Adjusted baseSpeed: " + baseSpeed);
            } else {
                System.out.println("Attribute not found for player");
            }

            cir.setReturnValue(baseSpeed);
        }
    }

    @Inject(method = "createAttributes", at = @At("RETURN"), cancellable = true)
    private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir)
    {
        AttributeSupplier.Builder builder = cir.getReturnValue();
        cir.setReturnValue(builder.add(ModAttributes.DIGGING_SPEED.get()));
    }
}
