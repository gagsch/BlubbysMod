package com.bmod.mixin;

import static com.bmod.util.mixinUtils.DimLightSystem.enabled;

import com.bmod.util.mixinUtils.DimLightSystem;
import com.bmod.util.mixinUtils.TextureAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.platform.NativeImage;

@Mixin(net.minecraft.client.renderer.texture.DynamicTexture.class)
public class MixinDynamicTexture implements TextureAccess {
    @Shadow
    private NativeImage pixels;

    @Unique
    private boolean blubbysmodofdoom$enableHook = false;

    @Inject(method = "upload", at = @At("HEAD"))
    private void onUpload(CallbackInfo ci) {
        if (blubbysmodofdoom$enableHook && enabled && pixels != null) {
            final NativeImage img = pixels;

            for (int b = 0; b < 16; b++) {
                for (int s = 0; s < 16; s++) {
                    final int color = DimLightSystem.darken(img.getPixelRGBA(b, s), b, s);
                    img.setPixelRGBA(b, s, color);
                }
            }
        }
    }

    @Override
    public void darkness_enableUploadHook() {
        blubbysmodofdoom$enableHook = true;
    }
}