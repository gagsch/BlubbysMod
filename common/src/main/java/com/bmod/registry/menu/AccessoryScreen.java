package com.bmod.registry.menu;

import com.bmod.registry.menu.container.AccessoryMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class AccessoryScreen extends AbstractContainerScreen<AccessoryMenu> {
    private static final ResourceLocation HOPPER_LOCATION = new ResourceLocation("textures/gui/container/hopper.png");

    public AccessoryScreen(AccessoryMenu arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);
        this.passEvents = false;
        this.imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(PoseStack arg, int i, int j, float f) {
        this.renderBackground(arg);
        super.render(arg, i, j, f);
        this.renderTooltip(arg, i, j);
    }

    protected void renderBg(PoseStack arg, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, HOPPER_LOCATION);
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        this.blit(arg, k, l, 0, 0, this.imageWidth, this.imageHeight);
    }
}
