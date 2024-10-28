package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.custom.DarkFairyEntity;
import com.bmod.registry.entity.custom.LeechEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DarkFairyRenderer extends MobRenderer<DarkFairyEntity, DarkFairyModel<DarkFairyEntity>> {
    public DarkFairyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DarkFairyModel<>(pContext.bakeLayer(DarkFairyModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DarkFairyEntity pEntity) {
        return new ResourceLocation(BlubbysMod.MOD_ID, "textures/entity/dark_fairy.png");
    }

    @Override
    protected int getBlockLightLevel(DarkFairyEntity entity, BlockPos blockPos) {
        return 15;
    }

    @Override
    public RenderType getRenderType(DarkFairyEntity entity, boolean pTranslucent, boolean pGlowing, boolean pOutline) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }
}