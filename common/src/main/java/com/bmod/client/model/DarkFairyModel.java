package com.bmod.client.model;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.bmod.BlubbysMod;
import com.bmod.client.animation.ModAnimationDefinitions;
import com.bmod.registry.entity.custom.DarkFairyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DarkFairyModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BlubbysMod.MOD_ID, "dark_fairy_layer"), "main");
	private final ModelPart dark_fairy;
	private final ModelPart body;
	private final ModelPart wings;
	private final ModelPart left_wing;
	private final ModelPart right_wing;

	public DarkFairyModel(ModelPart root) {
		this.dark_fairy = root.getChild("dark_fairy");
		this.body = this.dark_fairy.getChild("body");
		this.wings = this.body.getChild("wings");
		this.left_wing = this.wings.getChild("left_wing");
		this.right_wing = this.wings.getChild("right_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dark_fairy = partdefinition.addOrReplaceChild("dark_fairy", CubeListBuilder.create(), PartPose.offset(0.0F, 30.0F, 0.0F));

		PartDefinition body = dark_fairy.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(1.0F))
				.texOffs(12, 16).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition left_wing = wings.addOrReplaceChild("left_wing", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 2.0F));

		PartDefinition lower_r1 = left_wing.addOrReplaceChild("lower_r1", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 3.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -3.0F, 0.0F, -0.3446F, 0.1664F, 0.053F));

		PartDefinition upper_r1 = left_wing.addOrReplaceChild("upper_r1", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 1.0F, 0.3099F, 0.1664F, 0.053F));

		PartDefinition right_wing = wings.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 2.0F));

		PartDefinition lower_r2 = right_wing.addOrReplaceChild("lower_r2", CubeListBuilder.create().texOffs(12, 6).addBox(0.0F, 3.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 0.0F, -0.3446F, -0.1664F, -0.053F));

		PartDefinition upper_r2 = right_wing.addOrReplaceChild("upper_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.0F, 0.3099F, -0.1664F, -0.053F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(((DarkFairyEntity) entity).flyingAnimationState, ModAnimationDefinitions.DARK_FAIRY_FLY, ageInTicks, 1f);
	}
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		dark_fairy.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return dark_fairy;
	}
}