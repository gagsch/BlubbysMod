package com.bmod.event.client;

import com.bmod.BlubbysMod;
import com.bmod.packet.C2S.C2SOpenMenu;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ModifyGUIEvent {
    private static final ResourceLocation BUBBLE_BLOCK = new ResourceLocation(BlubbysMod.MOD_ID, "textures/block/bubble_block.png");
    private static final ResourceLocation ACCESSORY_BUTTON_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/accessory_button.png");
    public static final ImageButton BUTTON = new ImageButton(0, 0, 20, 18, 0, 0, 19, ACCESSORY_BUTTON_LOCATION, (button -> new C2SOpenMenu().sendToServer()));

    public static void initialize() {
        ClientGuiEvent.INIT_POST.register((screen, access) -> {
            if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen)
            {
                access.addRenderableWidget(BUTTON);
                BUTTON.visible = false;
            }
        });

        ClientGuiEvent.RENDER_POST.register((screen, poseStack, integer1, integer2, float1) -> {
            if (screen instanceof InventoryScreen inventoryScreen) {
                BUTTON.x = screen.width / 2 + 46 + (inventoryScreen.getRecipeBookComponent().isVisible() ? 78 : 0);
                BUTTON.y = screen.height / 2 - 22;
                BUTTON.visible = true;
            }
            else if (screen instanceof CreativeModeInventoryScreen creativeInventoryScreen) {
                BUTTON.x = screen.width / 2 + 30;
                BUTTON.y = screen.height / 2 - 50;
                BUTTON.visible = creativeInventoryScreen.getSelectedTab() == CreativeModeTab.TAB_INVENTORY.getId();
            }
        });

        ClientGuiEvent.RENDER_HUD.register((matrices, tickDelta) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Window window = minecraft.getWindow();
            LocalPlayer player = minecraft.player;
            if (isPlayerInsideBlock(player)) {
                renderVignette(matrices, window, .7f, BUBBLE_BLOCK);
            }
            if (minecraft.player.getMainHandItem().is(ModItems.IMMERSION_BREAKER.get())) {
                int attacks = minecraft.player.getMainHandItem().getOrCreateTag().getInt("attacks");

                if (attacks != 0) {
                    renderVignette(matrices, window, .55f, new ResourceLocation(BlubbysMod.MOD_ID, "textures/special/iba_stage_" + attacks + ".png"));
                }
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

    private static ItemStack playerHeldItem(Player player) {
        if (player == null) {
            return ItemStack.EMPTY;
        }

        return player.getMainHandItem();
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
