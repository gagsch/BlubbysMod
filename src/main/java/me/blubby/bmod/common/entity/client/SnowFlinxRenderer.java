package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.custom.SnowFlinxEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnowFlinxRenderer extends MobRenderer<SnowFlinxEntity, SnowFlinxModel<SnowFlinxEntity>> {
    public SnowFlinxRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SnowFlinxModel<>(pContext.bakeLayer(ModModelLayers.SNOW_FLINX_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SnowFlinxEntity pEntity) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "textures/entity/snow_flinx.png");
    }

    @Override
    public void render(SnowFlinxEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.scale(0.6f,0.6f,0.6f);
        if (pEntity.isBaby())
        {
            pMatrixStack.scale(0.4f,0.4f,0.4f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}