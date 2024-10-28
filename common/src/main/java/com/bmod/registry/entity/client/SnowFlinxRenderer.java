package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.custom.SnowFlinxEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SnowFlinxRenderer extends MobRenderer<SnowFlinxEntity, SnowFlinxModel<SnowFlinxEntity>> {
    public SnowFlinxRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SnowFlinxModel<>(pContext.bakeLayer(SnowFlinxModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SnowFlinxEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/snow_flinx.png");
    }

    @Override
    public void render(SnowFlinxEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(0.6f,0.6f,0.6f);
        if (pEntity.isBaby())
        {
            pMatrixStack.scale(0.4f,0.4f,0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}