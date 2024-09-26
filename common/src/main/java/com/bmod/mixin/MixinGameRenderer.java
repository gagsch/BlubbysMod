package com.bmod.mixin;

import com.bmod.registry.world.ModDimensions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.bmod.util.mixinUtils.DimLightSystem;
import com.bmod.util.mixinUtils.LightmapAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Final
    @Shadow
    private Minecraft minecraft;
    @Final
    @Shadow
    private LightTexture lightTexture;

    @Inject(method = "renderLevel", at = @At(value = "HEAD"))
    private void onRenderLevel(float tickDelta, long nanos, PoseStack matrixStack, CallbackInfo ci) {
        final LightmapAccess lightmap = (LightmapAccess) lightTexture;

        if (lightmap.darkness_isDirty()) {
            minecraft.getProfiler().push("lightTex");
            DimLightSystem.updateLuminance(minecraft, lightmap.darkness_prevFlicker());
            minecraft.getProfiler().pop();
        }
    }

    @Inject(method = "getNightVisionScale", at = @At("RETURN"), cancellable = true)
    private static void getNightVisionScale(LivingEntity entity, float sinvar, CallbackInfoReturnable<Float> cir) {
        int duration = Objects.requireNonNull(entity.getEffect(MobEffects.NIGHT_VISION)).getDuration();
        cir.setReturnValue(entity.level.dimension() == ModDimensions.BLYDIM_KEY ? (duration > 200 ? 0.1F : 0.0F + Mth.sin(((float)duration - sinvar) * 3.1415927F * 0.2F) * 0.1F) : (duration > 200 ? 1.0F : 0.7F + Mth.sin(((float)duration - sinvar) * 3.1415927F * 0.2F) * 0.3F));
    }
}