package com.bmod.event.client;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

public class RenderOverlayEvent {

    private static final ResourceLocation IMG_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/block/bubble_block.png");

    public static void initialize() {
        ClientGuiEvent.RENDER_HUD.register((matrices, tickDelta) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Window window = minecraft.getWindow();
            LocalPlayer player = minecraft.player;
            if (isPlayerInsideBlock(player))
            {
                renderVignette(matrices, window, .7f, IMG_LOCATION);
            }
        });
    }

    private static boolean isPlayerInsideBlock(Player player) {
        if (player == null) {
            return false;
        }

        Block block = player.level.getBlockState(new BlockPos(player.getEyePosition().x, player.getEyePosition().y, player.getEyePosition().z)).getBlock();

        return block == ModBlocks.BUBBLE_BLOCK.get();
    }

    private static void renderVignette(PoseStack poseStack, Window window, float alpha, ResourceLocation resource) {
        poseStack.pushPose();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1F, 1F, 1F, alpha);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, resource);
        GuiComponent.blit(poseStack, 0, 0, -90, 0.0F, 0.0F, window.getGuiScaledWidth(), window.getGuiScaledHeight(), window.getGuiScaledWidth(), window.getGuiScaledHeight());
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }
}
