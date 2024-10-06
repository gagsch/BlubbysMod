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

        if (lightmap.blubbysmod$isDirty()) {
            minecraft.getProfiler().push("lightTex");
            DimLightSystem.updateLuminance(minecraft, lightmap.blubbysmod$prevFlicker());
            minecraft.getProfiler().pop();
        }
    }

    @Inject(method = "getNightVisionScale", at = @At("RETURN"), cancellable = true)
    private static void getNightVisionScale(LivingEntity entity, float sinVar, CallbackInfoReturnable<Float> cir) {
        int duration = Objects.requireNonNull(entity.getEffect(MobEffects.NIGHT_VISION)).getDuration();

        float base = entity.level.dimension() == ModDimensions.BLYDIM_KEY ? 0.0F : 0.7F;
        float factor = entity.level.dimension() == ModDimensions.BLYDIM_KEY ? 0.1F : 0.3F;

        float sinComponent = Mth.sin(((float)duration - sinVar) * 3.1415927F * 0.2F) * factor;
        cir.setReturnValue(duration > 200 ? (base + 0.1F) : (base + sinComponent));
    }
}