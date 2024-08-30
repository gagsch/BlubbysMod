package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.animations.ModAnimationDefinitions;
import me.blubby.bmod.common.entity.custom.BehemothEntity;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class Behemoth<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "behemoth"), "main");
	private final ModelPart behemoth;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart lower;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart upper;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart head;
	private final ModelPart jaw;

	public Behemoth(ModelPart root) {
		this.behemoth = root.getChild("behemoth");
		this.body = behemoth.getChild("body");

		this.torso = body.getChild("torso");
		this.upper = torso.getChild("upper");
		this.lower = torso.getChild("lower");

		this.head = upper.getChild("head");
		this.jaw = head.getChild("jaw");

		this.arms = upper.getChild("arms");
		this.left_arm = arms.getChild("left_arm");
		this.right_arm = arms.getChild("right_arm");

		this.legs = lower.getChild("legs");
		this.left_leg = legs.getChild("left_leg");
		this.right_leg = legs.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition behemoth = partdefinition.addOrReplaceChild("behemoth", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = behemoth.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lower = torso.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(0, 42).addBox(-12.0F, -34.0F, -10.0F, 24.0F, 22.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = lower.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(84, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -12.0F, -4.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(84, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -12.0F, -4.0F));

		PartDefinition upper = torso.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -22.0F, -8.0F, 32.0F, 22.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.0F, -8.0F));

		PartDefinition arms = upper.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(-8.0F, 19.0F, 3.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(116, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -39.0F, 3.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(116, 0).addBox(0.0F, 0.0F, -4.0F, 8.0F, 36.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(24.0F, -39.0F, 3.0F));

		PartDefinition head = upper.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 80).addBox(-8.0F, -12.0F, -16.0F, 16.0F, 14.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-10.0F, -20.0F, -18.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.0F, -20.0F, -18.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 4.0F));

		PartDefinition left_horn_1_r1 = head.addOrReplaceChild("left_horn_1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -8.0F, -9.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -13.0F, -5.0F, 0.7854F, 0.0F, -0.0436F));

		PartDefinition right_horn_1_r1 = head.addOrReplaceChild("right_horn_1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -8.0F, -10.0F, 0.7854F, 0.0F, 0.0436F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(66, 66).addBox(-10.0F, 0.0F, -14.0F, 20.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		applyHeadRotation(netHeadYaw, headPitch);
		this.animateWalk(limbSwing, limbSwingAmount);

		this.animate(((BehemothEntity) entity).attackAnimationState, ModAnimationDefinitions.BEHEMOTH_ATTACK, ageInTicks, 1f);
		this.animate(((BehemothEntity) entity).groundHitAnimationState, ModAnimationDefinitions.BEHEMOTH_GROUND_HIT, ageInTicks, .2f);
	}

	private void animateWalk(float pLimbSwing, float pLimbSwingAmount) {
		long i = (long)(pLimbSwing * 50 * 2);
		float f = Math.min(pLimbSwingAmount * 2.5f, 1f);
		KeyframeAnimations.animate(this, ModAnimationDefinitions.BEHEMOTH_WALK, i, f, new Vector3f());
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30, 30);
		headPitch = Mth.clamp(headPitch, -25, 45);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		behemoth.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return behemoth;
	}
}