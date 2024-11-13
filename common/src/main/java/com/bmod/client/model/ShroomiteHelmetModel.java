package com.bmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ShroomiteHelmetModel<E extends LivingEntity> extends EntityModel<E> implements HeadedModel {
    private final ModelPart head;

    public ShroomiteHelmetModel(ModelPart head) {
        this.head = head;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        mesh.getRoot().addOrReplaceChild("root",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-14f, -13f, 0.0f, 10.0f, 12.0f, 0, new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .mirror()
                        .addBox(4f, -13f, 0.0f, 10.0f, 12.0f, 0, new CubeDeformation(0.0F))
                        .texOffs(0, 10)
                        .mirror(false)
                        .addBox(-9f, -13f, -5.0f, 0, 7.0f, 10.0f, new CubeDeformation(0.0F))
                        .texOffs(0, 10)
                        .addBox(9f, -13f, -5.0f, 0, 7.0f, 10.0f, new CubeDeformation(0.0F))
                        .texOffs(0, 10),
                PartPose.offset(0, -3f, 0));

        return LayerDefinition.create(mesh, 20, 20);
    }


    @Override
    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30, 30);
        pHeadPitch = Mth.clamp(pHeadPitch, -25, 45);
        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180f);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        head.getChild("root").render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    @Override
    public @NotNull ModelPart getHead() {
        return this.head;
    }
}