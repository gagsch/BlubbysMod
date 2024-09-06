package me.blubby.bmod.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.blubby.bmod.Blubby_sModOfDoom;
import me.blubby.bmod.common.entity.animations.ModAnimationDefinitions;
import me.blubby.bmod.common.entity.custom.DimensionTeleporterEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;


public class DimensionTeleporterModel<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Blubby_sModOfDoom.MOD_ID, "dimension_teleporter"), "main");
	private final ModelPart root1;
	public final ModelPart dimensional_portal;

	public DimensionTeleporterModel(ModelPart root) {
		this.root1 = root.getChild("root");
		this.dimensional_portal = root1.getChild("dimensional_portal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition dimensional_portal = root.addOrReplaceChild("dimensional_portal", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -36.0F, -1.0F, 20.0F, 36.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(((DimensionTeleporterEntity) entity).lifeAnimationState, ModAnimationDefinitions.PORTAL_LIFE, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root1;
	}
}