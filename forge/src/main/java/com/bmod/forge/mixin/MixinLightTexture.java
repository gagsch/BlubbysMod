package com.bmod.forge.mixin;

import com.bmod.util.mixin_util.ILightmapAccess;
import com.bmod.util.mixin_util.ITextureAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightTexture.class)
public class MixinLightTexture implements ILightmapAccess {
    @Final
    @Shadow
    private DynamicTexture lightTexture;
    @Shadow
    private float blockLightRedFlicker;
    @Shadow
    private boolean updateLightTexture;

    @Inject(method = "<init>*", at = @At(value = "RETURN"))
    private void afterInit(GameRenderer gameRenderer, Minecraft minecraftClient, CallbackInfo ci) {
        ((ITextureAccess) lightTexture).blubbysmod$enableUploadHook();
    }

    @Override
    public float blubbysmod$prevFlicker() {
        return blockLightRedFlicker;
    }

    @Override
    public boolean blubbysmod$isDirty() {
        return updateLightTexture;
    }
}
