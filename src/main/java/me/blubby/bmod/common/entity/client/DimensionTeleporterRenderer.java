package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DimensionTeleporterRenderer extends MobRenderer<DimensionTeleporterEntity, DimensionTeleporterModel<DimensionTeleporterEntity>> {
    public DimensionTeleporterRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DimensionTeleporterModel<>(pContext.bakeLayer(ModModelLayers.DIMENSION_TELEPORTER_LAYER)), 0f);
    }

    @Override
    public ResourceLocation getTextureLocation(DimensionTeleporterEntity pEntity) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "textures/entity/dimensional_teleporter.png");
    }

    @Override
    public void render(DimensionTeleporterEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}