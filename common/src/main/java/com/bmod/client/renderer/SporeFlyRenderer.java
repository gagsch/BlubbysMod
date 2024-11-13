 package com.bmod.client.renderer;

import com.bmod.BlubbysMod;
import com.bmod.client.model.SporeFlyModel;
import com.bmod.registry.entity.custom.SporeFlyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SporeFlyRenderer extends MobRenderer<SporeFlyEntity, SporeFlyModel<SporeFlyEntity>> {
    public SporeFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SporeFlyModel<>(pContext.bakeLayer(SporeFlyModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SporeFlyEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/spore_fly.png");
    }

    @Override
    public void render(SporeFlyEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}