package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.custom.LeechEntity;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LeechRenderer extends MobRenderer<LeechEntity, LeechModel<LeechEntity>> {
    public LeechRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LeechModel<>(pContext.bakeLayer(LeechModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LeechEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/leech.png");
    }

    @Override
    public void render(LeechEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}