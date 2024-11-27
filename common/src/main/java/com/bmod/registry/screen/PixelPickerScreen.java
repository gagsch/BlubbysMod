package com.bmod.registry.screen;

import com.bmod.packet.C2S.C2SSavePixel;
import com.bmod.registry.block.block_entity.custom.PixelBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class PixelPickerScreen extends Screen {

   private final Font font = Minecraft.getInstance().font;
    private final PixelBlockEntity pixel;
    protected EditBox redEdit;
    protected EditBox greenEdit;
    protected EditBox blueEdit;
    protected Button exitButton;

    public PixelPickerScreen(PixelBlockEntity pixel) {
        super(Component.translatable("block.blubbysmod.pixel_block"));
        this.pixel = pixel;
    }

    @Override
    protected void init() {
        this.redEdit = createColorEditBox(this.width / 2 - 55, "R", pixel.getColor()[0]);
        this.greenEdit = createColorEditBox(this.width / 2 - 15, "G", pixel.getColor()[1]);
        this.blueEdit = createColorEditBox(this.width / 2 + 25, "B", pixel.getColor()[2]);

        this.exitButton = new Button(this.width / 2 - 40, 70, 80, 20, Component.literal("Save"), (button) -> {
            this.onDone();
        });

        this.addWidget(redEdit);
        this.addWidget(greenEdit);
        this.addWidget(blueEdit);
        this.addRenderableWidget(exitButton);
    }

    protected int saveEdit(EditBox editBox) {
        String numericString = editBox.getValue().replaceAll("[^0-9]", "");
        if (numericString.isEmpty())
        {
            return 0;
        }
        return Math.min(Integer.parseInt(numericString), 255);
    }

    protected void onDone() {
        new C2SSavePixel(pixel, saveEdit(redEdit), saveEdit(greenEdit), saveEdit(blueEdit)).sendToServer();
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public void onClose() {
        this.onDone();
        super.onClose();
    }

    private EditBox createColorEditBox(int x, String suggestion, int initialValue) {
        EditBox editBox = createEditBox(x, 30, 30, suggestion, String.valueOf(initialValue), 3);
        editBox.setResponder(string -> handleResponder(editBox, string));
        return editBox;
    }

    private EditBox createEditBox(int x, int y, int width, String suggestion, String initialValue, int maxLength) {
        EditBox editBox = new EditBox(this.font, x, y, width, 20, Component.literal(suggestion));
        editBox.setValue(initialValue);
        editBox.setMaxLength(maxLength);
        return editBox;
    }

    private void handleResponder(EditBox editBox, String value) {
        if (value.startsWith("0")) {
            editBox.setValue(value.substring(1));
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {

        if (this.minecraft != null) {
            this.renderBackground(poseStack);
        }

        if (this.blueEdit != null) {
            this.redEdit.render(poseStack, mouseX, mouseY, partialTicks);
            this.greenEdit.render(poseStack, mouseX, mouseY, partialTicks);
            this.blueEdit.render(poseStack, mouseX, mouseY, partialTicks);
        }

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }
}