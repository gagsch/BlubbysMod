package com.bmod.client.renderer;

import com.bmod.client.model.ShroomiteHelmetModel;
import com.bmod.registry.item.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ShroomiteHelmetRenderer<E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("shroomite"), "3");
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/models/armor/shroomite_layer_3.png");
    private final EntityModelSet model;

    public ShroomiteHelmetRenderer(RenderLayerParent<E, M> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = modelSet;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, E pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack stack = pLivingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if(stack.is(ModItems.SHROOMITE_HELMET.get())) {
            pPoseStack.pushPose();

            pPoseStack.scale(1, 1, 1);
            this.getParentModel().getHead().translateAndRotate(pPoseStack);
            ShroomiteHelmetModel<E> helmetModel = new ShroomiteHelmetModel<>(this.model.bakeLayer(LAYER_LOCATION));
            helmetModel.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.armorCutoutNoCull(TEXTURE)), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

            pPoseStack.popPose();
        }
    }
}