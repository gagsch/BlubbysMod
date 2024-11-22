package com.bmod.client.renderer;

import com.bmod.BlubbysMod;
import com.bmod.client.model.BehemothModel;
import com.bmod.client.model.MagmoidModel;
import com.bmod.registry.entity.custom.BehemothEntity;
import com.bmod.registry.entity.custom.DarkFairyEntity;
import com.bmod.registry.entity.custom.MagmoidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MagmoidRenderer extends MobRenderer<MagmoidEntity, MagmoidModel<MagmoidEntity>> {
    public MagmoidRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MagmoidModel<>(pContext.bakeLayer(MagmoidModel.LAYER_LOCATION)), 1f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MagmoidEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/magmoid.png");
    }

    @Override
    protected int getBlockLightLevel(MagmoidEntity entity, BlockPos blockPos) {
        return 15;
    }

    @Override
    public void render(@NotNull MagmoidEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}