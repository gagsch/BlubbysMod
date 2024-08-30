package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.custom.BehemothEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BehemothRenderer extends MobRenderer<BehemothEntity, Behemoth<BehemothEntity>> {
    public BehemothRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Behemoth<>(pContext.bakeLayer(ModModelLayers.BEHEMOTH_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(BehemothEntity pEntity) {
        return new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "textures/entity/behemoth.png");
    }

    @Override
    public void render(BehemothEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}