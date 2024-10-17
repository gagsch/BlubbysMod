package com.bmod.registry.menu;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.elements.AncientPageButton;
import com.bmod.registry.recipe.EnrichmentRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AncientRecipeBookScreen extends Screen {
    public static final ResourceLocation ANCIENT_BOOK_LOCATION = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/ancient_recipe_book.png");

    public final int textureWidth;
    public final int textureHeight;
    private final int pagesDiscovered;
    private int currentPage;
    public final List<EnrichmentRecipe> enrichmentRecipes;

    public AncientRecipeBookScreen(int pagesDiscovered) {
        super(Component.literal("Ancient Recipe Book"));
        assert Minecraft.getInstance().level != null;
        RecipeManager rm = Minecraft.getInstance().level.getRecipeManager();

        this.textureWidth = 186;
        this.textureHeight = 182;
        this.pagesDiscovered = pagesDiscovered;
        this.currentPage = 0;
        this.enrichmentRecipes = new ArrayList<>(rm.getAllRecipesFor(EnrichmentRecipe.Type.INSTANCE));
        this.enrichmentRecipes.sort(Comparator.comparingInt(recipe -> recipe.pageNumber));
    }

    @Override
    protected void init() {
        int xPos = (this.width - textureWidth) / 2;
        int yPos = (this.height - textureHeight) / 2;

        PageButton forwardButton = this.addRenderableWidget(new AncientPageButton(xPos + 116, yPos +135, true, (button) -> this.pageForward(), true));
        PageButton backButton = this.addRenderableWidget(new AncientPageButton(xPos + 43, yPos + 135, false, (button) -> this.pageBack(), true));
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

        this.renderRecipe(this.currentPage, xPos, yPos);

        super.render(poseStack, mouseX, mouseY, delta);
    }

    private void renderRecipe(int currentPage, int startX, int startY)
    {
        EnrichmentRecipe recipe = this.enrichmentRecipes.get(Math.min(currentPage, Math.min(this.enrichmentRecipes.size() - 1, this.pagesDiscovered)));

        // Requirement Item
        Minecraft.getInstance().getItemRenderer().renderGuiItem(recipe.getRequired().getItems()[0], startX + 83, startY + 32);

        // 3x3 Crafting Items
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int index = x + (y * 3);

                if (index < recipe.getIngredients().size()) {
                    Ingredient ingredient = recipe.getIngredients().get(index);

                    if (ingredient.getItems().length > 0) {
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(
                                ingredient.getItems()[0],
                                startX + 65 + (x * 18),
                                startY + 54 + (y * 18)
                        );
                    }
                }
            }
        }

        // Crafted Item
        Minecraft.getInstance().getItemRenderer().renderGuiItem(recipe.getResultItem(), startX + 83, startY + 128);
    }

    private void pageBack() {
        if (this.currentPage > 0)
            --this.currentPage;
        else
            this.currentPage = this.enrichmentRecipes.size() - 1;
    }

    private void pageForward() {
        if (this.currentPage < Math.min(this.enrichmentRecipes.size() - 1, this.pagesDiscovered))
            ++this.currentPage;
        else
            this.currentPage = 0;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}