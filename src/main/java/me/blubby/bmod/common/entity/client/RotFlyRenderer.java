package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.custom.RotFlyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RotFlyRenderer extends MobRenderer<RotFlyEntity, RotFlyModel<RotFlyEntity>> {
    public RotFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RotFlyModel<>(pContext.bakeLayer(ModModelLayers.ROT_FLY_LAYER)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(RotFlyEntity pEntity) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "textures/entity/rot_fly.png");
    }

    @Override
    public void render(RotFlyEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}