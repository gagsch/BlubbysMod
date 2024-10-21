package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.entity.animations.ModAnimationDefinitions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.bmod.registry.entity.custom.RotFlyEntity;
import com.mojang.math.Vector3f;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LeechModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlubbysMod.MOD_ID, "leech"), "main");
	private final ModelPart leech;
	private final ModelPart body;
	private final ModelPart back;
	private final ModelPart tail;
	private final ModelPart caboose;
	private final ModelPart front;
	private final ModelPart head;
	private final ModelPart skull;

	public LeechModel(ModelPart root) {
		this.leech = root.getChild("leech");
		this.body = leech.getChild("body");
		this.front = body.getChild("front");
		this.back = body.getChild("back");
		this.tail = back.getChild("tail");
		this.caboose = tail.getChild("caboose");
		this.head = front.getChild("head");
		this.skull = head.getChild("skull");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leech = partdefinition.addOrReplaceChild("leech", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = leech.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition back = body.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = back.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_front_part_r1 = tail.addOrReplaceChild("tail_front_part_r1", CubeListBuilder.create().texOffs(12, 15).addBox(-4.0F, -3.0F, 0.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 3.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition caboose = tail.addOrReplaceChild("caboose", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_back_part_r1 = caboose.addOrReplaceChild("tail_back_part_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 5.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition front = body.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = front.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_part_r1 = head.addOrReplaceChild("neck_part_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_part_r1 = skull.addOrReplaceChild("head_part_r1", CubeListBuilder.create().texOffs(18, 6).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -5.0F, -0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(limbSwing, limbSwingAmount);
	}

	private void animateWalk(float pLimbSwing, float pLimbSwingAmount) {
		long i = (long)(pLimbSwing * 50);
		float f = Math.min(pLimbSwingAmount * 2.5f, 1f);
		KeyframeAnimations.animate(this, ModAnimationDefinitions.LEECH_MOVE, i, f, new Vector3f());
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leech.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return leech;
	}
}