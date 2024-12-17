package com.bmod.registry.screen;

import com.bmod.packet.C2S.C2SSaveFrog;
import com.bmod.registry.block.block_entity.custom.FrogExecutorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CodeScreen extends Screen {

   private final Font font = Minecraft.getInstance().font;
    private final FrogExecutorBlockEntity frog;
    protected MultiLineEditBox codeLine;
    protected Button exitButton;

    public CodeScreen(FrogExecutorBlockEntity frog) {
        super(Component.translatable("block.blubbysmod.frog_executor_block"));
        this.frog = frog;
    }

    @Override
    protected void init() {
        this.codeLine = new MultiLineEditBox(this.font, this.width / 2 - 125, 30, 250, 100, Component.literal("var x = 10 print x + 5 ..."), Component.literal(""));
        codeLine.setCharacterLimit(4096);
        codeLine.setValue(this.frog.getCode());

        this.exitButton = new Button(this.width / 2 - 40, 170, 80, 20, Component.literal("Save & Run"), (button) -> {
            this.onDone();
        });

        this.addWidget(codeLine);
        this.addRenderableWidget(exitButton);
    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        if (i == 258) {
            codeLine.charTyped(' ', 0);
            codeLine.charTyped(' ', 0);
            codeLine.charTyped(' ', 0);
            codeLine.charTyped(' ', 0);
        }
        return super.keyPressed(i, j, k);
    }

    @Override
    protected boolean isValidCharacterForName(String string, char c, int i) {
        System.out.println("Character typed y: " + c);
        return super.isValidCharacterForName(string, c, i);
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

    @Override
    public boolean changeFocus(boolean bl) {
        return false;
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