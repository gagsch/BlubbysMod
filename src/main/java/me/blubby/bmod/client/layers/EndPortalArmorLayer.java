package me.blubby.bmod.client.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.blubby.bmod.common.armor.ModArmorItem;
import me.blubby.bmod.common.tier.ModArmorMaterial;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EndPortalArmorLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public EndPortalArmorLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.CHEST);

        if ((itemstack.getItem() instanceof ModArmorItem armorItem)) {
            if (armorItem.getMaterial() == ModArmorMaterial.COSMILITE) {
                VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.endPortal());
                this.getParentModel().renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}