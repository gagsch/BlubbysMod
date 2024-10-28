package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.bmod.registry.entity.custom.RotFlyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RotFlyRenderer extends MobRenderer<RotFlyEntity, RotFlyModel<RotFlyEntity>> {
    public RotFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RotFlyModel<>(pContext.bakeLayer(RotFlyModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RotFlyEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/rot_fly.png");
    }

    @Override
    public void render(RotFlyEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}