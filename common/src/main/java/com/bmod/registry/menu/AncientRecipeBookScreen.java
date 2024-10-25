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

import java.util.*;

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

        this.renderRecipe(this.currentPage, xPos, yPos);

        super.render(poseStack, mouseX, mouseY, delta);
    }

    private void renderRecipe(int currentPage, int startX, int startY)
    {
        EnrichmentRecipe recipe = this.enrichmentRecipes.get(Math.min(currentPage, Math.min(this.enrichmentRecipes.size() - 1, this.pagesDiscovered)));

        // Requirement Item
        Minecraft.getInstance().getItemRenderer().renderGuiItem(recipe.getRequired().getItems()[0], startX + 32, startY + 76);

        // 3x3 Crafting Items
        int maxWidth = 3;
        int maxHeight = 3;

        if (!Objects.equals(recipe.recipeType, "shapeless"))
        {
            String[] recipeBoundStrings = recipe.recipeType.split("x");
            maxWidth = Integer.parseInt(recipeBoundStrings[0]);
            maxHeight = Integer.parseInt(recipeBoundStrings[1]);
        }
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                int index = x + (y * maxWidth);

                if (index < recipe.getIngredients().size()) {
                    Ingredient ingredient = recipe.getIngredients().get(index);

                    if (ingredient.getItems().length > 0) {
                        Minecraft.getInstance().getItemRenderer().renderGuiItem(
                                ingredient.getItems()[0],
                                startX + 54 + (x * 18),
                                startY + 58 + (y * 18)
                        );
                    }
                }
            }
        }

        // Crafted Item
        Minecraft.getInstance().getItemRenderer().renderGuiItem(recipe.getResultItem(), startX + 128, startY + 76);
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