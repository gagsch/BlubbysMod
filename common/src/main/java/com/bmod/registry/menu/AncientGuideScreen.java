package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.elements.AncientPageButton;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class AncientGuideScreen extends Screen {
    public static final ResourceLocation ANCIENT_BOOK_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/ancient_guide.png");

    public final int textureWidth;
    public final int textureHeight;
    private static int currentPage = 0;

    public AncientGuideScreen() {
        super(Component.literal("Ancient Guide"));

        this.textureWidth = 186;
        this.textureHeight = 182;
    }

    @Override
    protected void init() {
        int xPos = (this.width - textureWidth) / 2;
        int yPos = (this.height - textureHeight) / 2;

        PageButton forwardButton = this.addRenderableWidget(new AncientPageButton(xPos + 103, yPos + 158, true, (button) -> this.pageForward(), true));
        PageButton backButton = this.addRenderableWidget(new AncientPageButton(xPos + 56, yPos + 158, false, (button) -> this.pageBack(), true));
        forwardButton.visible = true;
        backButton.visible = true;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, ANCIENT_BOOK_LOCATION);

        int xPos = (this.width - textureWidth) / 2;
        int yPos = (this.height - textureHeight) / 2;

        blit(poseStack, xPos, yPos, 0, 0, textureWidth, textureHeight);

        int textX = (this.width - textureWidth) / 2 + 32;
        int textY = yPos + 30;

        drawCurrentPage(poseStack, textX, textY);

        super.render(poseStack, mouseX, mouseY, delta);
    }

    private void drawCurrentPage(PoseStack poseStack, int textX, int textY) {
        String pageText = Pages.PAGES_TEXT.get(currentPage % Pages.PAGES_TEXT.size());
        Font font = Minecraft.getInstance().font;
        int maxWidth = 123;
        int lineHeight = 10;
        int y = textY;

        List<FormattedCharSequence> lines = font.split(FormattedText.of(pageText), maxWidth);

        for (FormattedCharSequence line : lines) {
            font.draw(poseStack, line, textX, y, 0x57532E);
            y += lineHeight;
        }
    }


    private void pageBack() {
        if (currentPage > 0)
            --currentPage;
        else
            currentPage = Pages.PAGES_TEXT.size() - 1;
    }

    private void pageForward() {
        if (currentPage < Pages.PAGES_TEXT.size() - 1)
            ++currentPage;
        else
            currentPage = 0;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private static class Pages
    {
        public static final List<String> PAGES_TEXT = List.of(
                "Welcome to the world of Blubby's Mod. Blubby's Mod is a mod that adds a lot of HIGH quality content and mechanics. With this book, you will learn the simple knowledge needed to use this mod properly.",
                "ACCESSORIES\nThe accessories button can be found in the inventory, on click, it opens a 5 slot menu where you can put all your accessories. Accessories are items that give special buffs while inside the Accessories Container.",
                "ARTISAN'S WORKSHOP\nThe Artisan's Workshop is a special Crafting Table used to craft gear or useful items. Usually accessories, special items, and gear. To see all the recipes use JEI.",
                "NIGHTMARE REALM\nThe Nightmare Realm is a dimension that has common fears. To access the dimension, craft and place a Nightmare Gateway and socket a Cursed Gem into it."
        );
    }
}