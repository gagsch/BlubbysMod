package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.packet.C2S.C2SOpenMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AncientGuideScreen extends Screen {
    public static final ResourceLocation ANCIENT_BOOK_LEFT_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/ancient_guide_left.png");
    public static final ResourceLocation ANCIENT_BOOK_RIGHT_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/ancient_guide_right.png");

    public static int xPos;
    public static int yPos;
    public final int textureWidth;
    public final int textureHeight;
    private static int currentPage = 0;
    private final List<Page> PAGES = NonNullList.of(
            Page.of(""), // Filler Page, needed or the first page won't show

            Page.of("""
                    HELLO WORLD
                    
                    Welcome to the world of Blubby's Mod. \
                    Blubby's Mod is a mod that adds a lot of HIGH \
                    quality content and mechanics. With this book, \
                    you will learn the simple knowledge needed to \
                    use this mod properly.
                    """)
                    .addImage(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/blubbys_mod.png"),
                            40, 30, 108, 64, (button) -> Util.getPlatform().openUri("https://gagsch.xyz/")).setDownSound(SoundEvents.UI_BUTTON_CLICK)),

            Page.of("""
                    ACCESSORIES

                    The accessories button can be found in \
                    the inventory, on click, it opens a 5 slot \
                    menu where you can put all your accessories. \
                    Accessories are items that give special buffs \
                    while inside the Accessories Container.
                    """)
                    .addImage(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/accessory_example.png"),
                            45, 40, 96, 58))
                    .addImage(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/accessory_button_example.png"),
                            80, 115, 24, 24, (button -> new C2SOpenMenu().sendToServer())).setDownSound(SoundEvents.UI_BUTTON_CLICK)),

            Page.of("""
                    ARTISAN'S WORKSHOP

                    The Artisan's Workshop is a special Crafting \
                    Table used to craft gear or useful items. \
                    Usually accessories, special items, and gear. \
                    To see all the recipes use JEI.
                    """)
                    .addImage(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/artisans_table_example.png"),
                            43, 40, 100, 110)),

            Page.of("""
                    NIGHTMARE REALM

                    The Nightmare Realm is a dimension that is \
                    filled common fears. Access the Dimension \
                    by placing a Nightmare Gateway and socketing \
                    a Cursed Gem.
                    """)
                    .addImage(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/nightmare_gateway_example.png"),
                    55, 40, 69, 43)));

    public AncientGuideScreen() {
        super(Component.literal("Ancient Guide"));

        this.textureWidth = 186;
        this.textureHeight = 182;

        xPos = (this.width - textureWidth) / 2;
        yPos = (this.height - textureHeight) / 2;
    }

    @Override
    protected void init() {
        xPos = (this.width - textureWidth) / 2;
        yPos = (this.height - textureHeight) / 2;

        this.addRenderableWidget(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/blank.png"), -50, 159, 22, 22, (button) -> this.doTurnPage(-1), false).setDownSound(SoundEvents.BOOK_PAGE_TURN));
        this.addRenderableWidget(new Image(new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/guide/blank.png"), 217, 159, 22, 22, (button) -> this.doTurnPage(1), false).setDownSound(SoundEvents.BOOK_PAGE_TURN));

        doTurnPage(0);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);

        xPos = (this.width - textureWidth) / 2;
        yPos = (this.height - textureHeight) / 2;
        
        RenderSystem.setShaderTexture(0, ANCIENT_BOOK_LEFT_LOCATION);
        blit(poseStack, xPos + 73, yPos, 0, 0, textureWidth, textureHeight);
        
        RenderSystem.setShaderTexture(0, ANCIENT_BOOK_RIGHT_LOCATION);
        blit(poseStack, xPos - 73, yPos, 0, 0, textureWidth, textureHeight);

        drawCurrentPage(poseStack, (this.width - textureWidth) / 2 - 37, yPos + 25, PAGES.get(currentPage));

        super.render(poseStack, mouseX, mouseY, delta);
    }

    private void drawCurrentPage(PoseStack poseStack, int textX, int textY, Page page) {
        Font font = Minecraft.getInstance().font;
        int maxWidth = 123;
        int lineHeight = 10;
        int y = textY;

        List<FormattedCharSequence> lines = font.split(FormattedText.of(page.getText()), maxWidth);

        for (FormattedCharSequence line : lines) {
            font.draw(poseStack, line, textX, y, 0x57532E);
            y += lineHeight;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void doTurnPage(int turnAmount) {
        PAGES.get(currentPage).images.forEach(this::removeWidget);
        currentPage = (currentPage + turnAmount + PAGES.size()) % PAGES.size();
        PAGES.get(currentPage).images.forEach(this::addRenderableWidget);
    }

    private static class Page
    {
        @SuppressWarnings("all")
        private String text;
        public List<Image> images = new ArrayList<>();
        public Consumer<Page> render = (page) -> {};

        private Page(String text)
        {
            this.text = text;
        }

        public String getText()
        {
            this.render.accept(this);
            return text;
        }

        public static Page of(String text) {
            return new Page(text);
        }

        public Page addImage(Image image)
        {
            this.images.add(image);
            return this;
        }

        public Page onRender(Consumer<Page> consumer)
        {
            this.render = consumer;
            return this;
        }
    }

    public static class Image extends Button {
        private final ResourceLocation location;
        private SoundInstance sound;
        private final int xOffset;
        private final int yOffset;
        private final int xScale;
        private final int yScale;

        public Image(ResourceLocation location, int x, int y, int xScale, int yScale, OnPress onPress, boolean isOnPage) {
            super(x, y, xScale, yScale, CommonComponents.EMPTY, onPress);

            this.location = location;
            this.xOffset = x + (isOnPage ? 73 : 0);
            this.yOffset = y;
            this.xScale = xScale;
            this.yScale = yScale;
        }

        public Image(ResourceLocation location, int x, int y, int xScale, int yScale, OnPress onPress) {
            this(location, x, y, xScale, yScale, onPress, true);
        }

        public Image(ResourceLocation location, int x, int y, int xScale, int yScale) {
            this(location, x, y, xScale, yScale, (button -> {}));
        }

        @Override
        public void playDownSound(SoundManager soundManager) {
            if (this.sound != null)
            {
                soundManager.play(this.sound);
            }
        }

        public Image setDownSound(SoundEvent sound)
        {
            this.sound = SimpleSoundInstance.forUI(sound, 1f);
            return this;
        }

        @Override
        public void render(PoseStack poseStack, int i, int j, float f) {
            this.x = this.xOffset + AncientGuideScreen.xPos;
            this.y = this.yOffset + AncientGuideScreen.yPos;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, this.location);

            this.blit(poseStack, this.x, this.y, 0, 0, this.xScale, this.yScale);
        }
    }
}