package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import me.blubby.bmod.common.entity.animations.ModAnimationDefinitions;
import me.blubby.bmod.common.entity.custom.SnowFlinxEntity;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class SnowFlinxModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "snowflinx"), "main");
	private final ModelPart snow_flinx;
	private final ModelPart body;
	private final ModelPart feet;
	private final ModelPart nose;

	public SnowFlinxModel(ModelPart root) {
		this.snow_flinx = root.getChild("snow_flinx");
		this.body = snow_flinx.getChild("body");
		this.feet = body.getChild("feet");
		this.nose = body.getChild("nose");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition snow_flinx = partdefinition.addOrReplaceChild("snow_flinx", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = snow_flinx.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 45).addBox(-6.0F, -17.0F, -9.0F, 12.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(2, 2).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 16.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition feet = body.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_ankle_r1 = feet.addOrReplaceChild("right_ankle_r1", CubeListBuilder.create().texOffs(19, 34).addBox(-1.0F, -4.0F, 1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -3.0F, -3.0F, -0.2618F, 0.1309F, 0.0F));

		PartDefinition left_ankle_r1 = feet.addOrReplaceChild("left_ankle_r1", CubeListBuilder.create().texOffs(19, 34).addBox(-1.0F, -4.0F, 1.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, -3.0F, -0.2618F, -0.1309F, 0.0F));

		PartDefinition right_foot_r1 = feet.addOrReplaceChild("right_foot_r1", CubeListBuilder.create().texOffs(49, 17).addBox(-2.0F, -4.0F, -7.0F, 6.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, -3.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition left_foot_r1 = feet.addOrReplaceChild("left_foot_r1", CubeListBuilder.create().texOffs(49, 17).addBox(-2.0F, -4.0F, -7.0F, 6.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -3.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, -9.0F));

		PartDefinition cube_r1 = nose.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(limbSwing, limbSwingAmount);

		this.animate(((SnowFlinxEntity) entity).idleAnimationState, ModAnimationDefinitions.SNOW_FLINX_IDLE, ageInTicks, 0.8f);
	}

	private void animateWalk(float pLimbSwing, float pLimbSwingAmount) {
		long i = (long)(pLimbSwing * 50);
		float f = Math.min(pLimbSwingAmount * 2.5f, 1f);
		KeyframeAnimations.animate(this, ModAnimationDefinitions.SNOW_FLINX_WALK, i, f, new Vector3f());
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		snow_flinx.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return snow_flinx;
	}
}