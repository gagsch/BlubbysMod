package com.bmod.registry.menu.elements;

import com.bmod.registry.menu.AncientGuideScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.GameRenderer;

@Environment(EnvType.CLIENT)
public class AncientPageButton extends PageButton {
    private final boolean isForward;

    public AncientPageButton(int posX, int posY, boolean isForward, OnPress onPress, boolean playTurnSound) {
        super(posX, posY, isForward, onPress, playTurnSound);

        this.isForward = isForward;
    }

    @Override
    public void renderButton(PoseStack poseStack, int i, int j, float f) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, AncientGuideScreen.ANCIENT_BOOK_LOCATION);
        int k = 0;
        int l = 192;
        if (this.isHoveredOrFocused()) {
            k += 23;
        }

        if (!this.isForward) {
            l += 13;
        }

        this.blit(poseStack, this.x, this.y, k, l, 23, 13);
    }
}