package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.bmod.registry.entity.custom.BehemothEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BehemothRenderer extends MobRenderer<BehemothEntity, BehemothModel<BehemothEntity>> {
    public BehemothRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BehemothModel<>(pContext.bakeLayer(ModModelLayers.BEHEMOTH_LAYER)), 1f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BehemothEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/behemoth.png");
    }

    @Override
    public void render(@NotNull BehemothEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}