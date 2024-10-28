package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.animations.ModAnimationDefinitions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.bmod.registry.entity.custom.RotFlyEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class RotFlyModel<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlubbysMod.MOD_ID, "rot_fly_layer"), "main");
	private final ModelPart rot_fly;
	private final ModelPart body;
	private final ModelPart mouth;
	private final ModelPart wings;
	private final ModelPart wing1;
	private final ModelPart wing2;

	public RotFlyModel(ModelPart root) {
		this.rot_fly = root.getChild("rot_fly");
		this.body = rot_fly.getChild("body");
		this.mouth = body.getChild("mouth");
		this.wings = body.getChild("wings");
		this.wing1 = wings.getChild("wing1");
		this.wing2 = wings.getChild("wing2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rot_fly = partdefinition.addOrReplaceChild("rot_fly", CubeListBuilder.create(), PartPose.offset(3.0F, 24.0F, -4.0F));

		PartDefinition body = rot_fly.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, 0.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rot_fly_legs_2_r1 = body.addOrReplaceChild("rot_fly_legs_2_r1", CubeListBuilder.create().texOffs(-5, 0).addBox(0.0F, 0.0F, 3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -1.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition rot_fly_legs_1_r1 = body.addOrReplaceChild("rot_fly_legs_1_r1", CubeListBuilder.create().texOffs(-5, 0).addBox(-3.0F, 0.0F, 3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -1.0F, -1.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, 0.0F));

		PartDefinition rot_fly_mouth_r1 = mouth.addOrReplaceChild("rot_fly_mouth_r1", CubeListBuilder.create().texOffs(-2, 6).addBox(0.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, -1.5708F, 0.7854F, 1.5708F));

		PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wing1 = wings.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(16, 4).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -6.0F, 2.0F));

		PartDefinition wing2 = wings.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -6.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(((RotFlyEntity) entity).flyingAnimationState, ModAnimationDefinitions.ROT_FLY_FLYING, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rot_fly.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return rot_fly;
	}
}