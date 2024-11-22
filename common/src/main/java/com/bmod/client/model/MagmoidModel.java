package com.bmod.client.model;

import com.bmod.BlubbysMod;
import com.bmod.client.animation.ModAnimationDefinitions;
import com.bmod.registry.entity.custom.MagmoidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class MagmoidModel<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlubbysMod.MOD_ID, "magmoid_layer"), "main");
	private final ModelPart magmoid;
	private final ModelPart body;
	private final ModelPart upper_torso;
	private final ModelPart left_arm;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart lower_torso;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public MagmoidModel(ModelPart root) {
		this.magmoid = root.getChild("magmoid");
		this.body = this.magmoid.getChild("body");
		this.upper_torso = this.body.getChild("upper_torso");
		this.left_arm = this.upper_torso.getChild("left_arm");
		this.head = this.upper_torso.getChild("head");
		this.right_arm = this.upper_torso.getChild("right_arm");
		this.lower_torso = this.body.getChild("lower_torso");
		this.left_leg = this.lower_torso.getChild("left_leg");
		this.right_leg = this.lower_torso.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition magmoid = partdefinition.addOrReplaceChild("magmoid", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -3.0F));

		PartDefinition body = magmoid.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_torso = body.addOrReplaceChild("upper_torso", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, 3.0F));

		PartDefinition cube_r1 = upper_torso.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -20.0F, 0.0F, 28.0F, 22.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -25.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition left_arm = upper_torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 150).addBox(7.0F, 8.0F, -8.0F, 8.0F, 20.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(34, 150).addBox(7.0F, 8.0F, -8.0F, 8.0F, 20.0F, 9.0F, new CubeDeformation(1.0F)), PartPose.offset(13.0F, -10.0F, -8.0F));

		PartDefinition cube_r2 = left_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 130).addBox(-3.0F, -4.0F, -3.0F, 18.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -6.0F, 0.0F, -0.0436F, 0.6545F));

		PartDefinition head = upper_torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(104, 0).addBox(-8.0F, -12.0F, -17.0F, 17.0F, 17.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(104, 33).addBox(-8.0F, -12.0F, -17.0F, 17.0F, 17.0F, 16.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, -8.0F, -20.0F));

		PartDefinition cube_layer_2_r1 = head.addOrReplaceChild("cube_layer_2_r1", CubeListBuilder.create().texOffs(170, 0).addBox(-1.0F, -4.0F, 0.0F, 11.0F, 4.0F, 4.0F, new CubeDeformation(1.0F))
				.texOffs(162, 84).addBox(-1.0F, -4.0F, 0.0F, 11.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -8.0F, -11.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_layer_2_r2 = head.addOrReplaceChild("cube_layer_2_r2", CubeListBuilder.create().texOffs(171, 33).addBox(0.0F, -4.0F, 0.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(8.0F, -4.0F, -11.0F, -0.0436F, 0.0F, -0.3054F));

		PartDefinition cube_layer_2_r3 = head.addOrReplaceChild("cube_layer_2_r3", CubeListBuilder.create().texOffs(170, 8).addBox(-10.0F, -4.0F, 0.0F, 11.0F, 4.0F, 4.0F, new CubeDeformation(1.0F))
				.texOffs(156, 168).addBox(-10.0F, -4.0F, 0.0F, 11.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -8.0F, -11.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_layer_2_r4 = head.addOrReplaceChild("cube_layer_2_r4", CubeListBuilder.create().texOffs(171, 41).addBox(-7.0F, -4.0F, 0.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-7.0F, -4.0F, -11.0F, 0.0F, 0.0436F, 0.3054F));

		PartDefinition right_arm = upper_torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 150).addBox(-15.0F, 8.0F, -8.0F, 8.0F, 20.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(34, 150).addBox(-15.0F, 8.0F, -8.0F, 8.0F, 20.0F, 9.0F, new CubeDeformation(1.0F)), PartPose.offset(-13.0F, -10.0F, -8.0F));

		PartDefinition cube_r3 = right_arm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 130).addBox(-15.0F, -4.0F, -3.0F, 18.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, -6.0F, 0.0F, 0.0436F, -0.6545F));

		PartDefinition lower_torso = body.addOrReplaceChild("lower_torso", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, 6.0F));

		PartDefinition cube_r4 = lower_torso.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 92).addBox(-12.0F, -16.0F, -8.0F, 24.0F, 16.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -2.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_leg = lower_torso.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(156, 150).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(132, 150).addBox(-2.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(1.0F)), PartPose.offset(4.0F, 8.0F, 6.0F));

		PartDefinition right_leg = lower_torso.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(156, 150).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(132, 150).addBox(-4.0F, -2.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(1.0F)), PartPose.offset(-4.0F, 8.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		applyHeadRotation(netHeadYaw, headPitch);
		this.animateWalk(limbSwing, limbSwingAmount);

		this.animate(((MagmoidEntity) entity).attackAnimationState, ModAnimationDefinitions.MAGMOID_NORMAL_HIT, ageInTicks, 1f);
		this.animate(((MagmoidEntity) entity).groundHitAnimationState, ModAnimationDefinitions.MAGMOID_GROUND_HIT, ageInTicks, 1.5f);
	}

	private void animateWalk(float pLimbSwing, float pLimbSwingAmount) {
		long i = (long)(pLimbSwing * 50 * 2);
		float f = Math.min(pLimbSwingAmount * 2.5f, 1f);
		KeyframeAnimations.animate(this, ModAnimationDefinitions.MAGMOID_WALK, i, f, new Vector3f());
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30, 30);
		headPitch = Mth.clamp(headPitch, -25, 45);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		magmoid.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.magmoid;
	}
}