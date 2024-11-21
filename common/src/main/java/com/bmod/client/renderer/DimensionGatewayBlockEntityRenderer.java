package com.bmod.client.renderer;

import com.bmod.registry.block.block_entity.custom.DimensionGatewayBlockEntity;
import com.bmod.registry.item.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class DimensionGatewayBlockEntityRenderer implements BlockEntityRenderer<DimensionGatewayBlockEntity> {
    private final ItemRenderer itemRenderer;
    private final long startTime = System.currentTimeMillis();

    public DimensionGatewayBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DimensionGatewayBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!blockEntity.powered)
            return;

        Player player = Minecraft.getInstance().player;

        int x = blockEntity.getBlockPos().getX();
        int y = blockEntity.getBlockPos().getY();
        int z = blockEntity.getBlockPos().getZ();

        Vec3 pos = new Vec3(x, y, z);

        if (player != null && player.position().distanceTo(pos) < 20) {
            double dx = player.getX() - blockEntity.getBlockPos().getX() - 0.5;
            double dz = player.getZ() - blockEntity.getBlockPos().getZ() - 0.5;
            float angle = (float) (Math.atan2(dz, dx) * (180 / Math.PI)) - 90;

            ItemStack itemStack = new ItemStack(ModItems.CURSED_GEM.get());

            poseStack.pushPose();
            poseStack.translate(0.5f, getCurrentItemY(), 0.5f);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-angle));
            poseStack.scale(0.75f, 0.75f, 0.75f);

            this.itemRenderer.renderStatic(itemStack,
                    ItemTransforms.TransformType.FIXED,
                    255,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    bufferSource,
                    (int) blockEntity.getBlockPos().asLong());

            poseStack.popPose();
        }
    }

    private float getCurrentItemY() {
        float duration = 3500f;
        float elapsedTime = (System.currentTimeMillis() - startTime) % duration;
        float t = elapsedTime / duration;

        return 0.5f + (float) Math.sin(t * Math.PI * 2) * 0.05f;
    }
}
