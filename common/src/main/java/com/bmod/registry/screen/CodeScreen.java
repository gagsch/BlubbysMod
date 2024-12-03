package com.bmod.registry.screen;

import com.bmod.packet.C2S.C2SSaveFrog;
import com.bmod.registry.block.block_entity.custom.FrogExecutorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CodeScreen extends Screen {

   private final Font font = Minecraft.getInstance().font;
    private final FrogExecutorBlockEntity frog;
    protected EditBox codeLine;
    protected Button exitButton;

    public CodeScreen(FrogExecutorBlockEntity frog) {
        super(Component.translatable("block.blubbysmod.frog_executor_block"));
        this.frog = frog;
    }

    @Override
    protected void init() {
        this.codeLine = createEditBox(this.width / 2 - 200);

        this.exitButton = new Button(this.width / 2 - 40, 70, 80, 20, Component.literal("Save & Run"), (button) -> {
            this.onDone();
        });

        this.addWidget(codeLine);
        this.addRenderableWidget(exitButton);
    }

    protected void onDone() {
        new C2SSaveFrog(frog, codeLine.getValue()).sendToServer();
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public void onClose() {
        this.onDone();
        super.onClose();
    }

    private EditBox createEditBox(int x) {
        EditBox editBox = new EditBox(this.font, x, 30, 400, 20, Component.literal(""));
        editBox.setMaxLength(1024);
        editBox.setValue(this.frog.getCode());
        return editBox;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {

        if (this.minecraft != null) {
            this.renderBackground(poseStack);
        }

        if (this.codeLine != null) {
            this.codeLine.render(poseStack, mouseX, mouseY, partialTicks);
        }

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }
}