package com.bmod.registry.entity.client;

import com.bmod.BlubbysMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SpiderQueenModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "spiderqueenmodel"), "main");
	private final ModelPart queenspider;
	private final ModelPart body;
	private final ModelPart back;
	private final ModelPart torso;
	private final ModelPart legs;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart leg5;
	private final ModelPart leg8;
	private final ModelPart leg6;
	private final ModelPart leg7;
	private final ModelPart head;
	private final ModelPart fangs;
	private final ModelPart eyes;

	public SpiderQueenModel(ModelPart root) {
		this.queenspider = root.getChild("queenspider");
		this.body = this.queenspider.getChild("body");
		this.back = this.body.getChild("back");
		this.torso = this.body.getChild("torso");
		this.legs = this.torso.getChild("legs");
		this.leg1 = this.legs.getChild("leg1");
		this.leg2 = this.legs.getChild("leg2");
		this.leg3 = this.legs.getChild("leg3");
		this.leg4 = this.legs.getChild("leg4");
		this.leg5 = this.legs.getChild("leg5");
		this.leg8 = this.legs.getChild("leg8");
		this.leg6 = this.legs.getChild("leg6");
		this.leg7 = this.legs.getChild("leg7");
		this.head = this.body.getChild("head");
		this.fangs = this.head.getChild("fangs");
		this.eyes = this.head.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition queenspider = partdefinition.addOrReplaceChild("queenspider", CubeListBuilder.create(), PartPose.offset(8.0F, 24.0F, -8.0F));

		PartDefinition body = queenspider.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-8.0F, 0.0F, 10.0F));

		PartDefinition back = body.addOrReplaceChild("back", CubeListBuilder.create().texOffs(4, 3).addBox(-7.0F, -19.0F, 0.0F, 14.0F, 14.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(40, 38).addBox(-4.0F, -16.0F, -7.0F, 8.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = torso.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, -4.0F));

		PartDefinition leg1 = legs.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(40, 57).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(28, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition leg2 = legs.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 58).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(36, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition leg3 = legs.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(28, 61).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(44, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition leg4 = legs.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(56, 61).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 1.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg5 = legs.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(0, 62).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, 2.7489F, 0.0F));

		PartDefinition leg8 = legs.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(0, 66).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 70).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition leg6 = legs.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(28, 65).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(68, 69).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, 3.0107F, 0.0F));

		PartDefinition leg7 = legs.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(56, 65).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 70).addBox(-14.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 1.0F, 0.0F, -3.0107F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 38).addBox(-5.0F, -10.0F, -9.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -7.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition fangs = head.addOrReplaceChild("fangs", CubeListBuilder.create().texOffs(16, 70).addBox(-2.0F, -8.0F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(76, 0).addBox(2.0F, -8.0F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, -14.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(68, 57).addBox(-6.0F, -14.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(76, 7).addBox(4.0F, -14.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(76, 27).addBox(1.0F, -13.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(76, 31).addBox(-3.0F, -13.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(76, 11).addBox(-3.0F, -15.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(76, 19).addBox(-4.0F, -16.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(76, 23).addBox(3.0F, -16.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(76, 15).addBox(2.0F, -15.0F, -17.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		queenspider.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return queenspider;
	}
}