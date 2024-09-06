package me.blubby.bmod.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.blubby.bmod.common.entity.ModEntities;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

public class EndPortalDimensionalPortalLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public EndPortalDimensionalPortalLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity == new DimensionTeleporterEntity(ModEntities.DIMENSION_TELEPORTER.get(), entity.level));
        {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.endPortal());
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}