package com.bmod.registry.recipe.jei;

import com.bmod.BlubbysMod;
import com.bmod.registry.menu.EnrichmentTableScreen;
import com.bmod.registry.recipe.EnrichmentRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(BlubbysMod.MOD_ID, "jei_mod_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new EnrichmentCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager rm = Minecraft.getInstance().level.getRecipeManager();

       List<EnrichmentRecipe> enrichmentRecipes = rm.getAllRecipesFor(EnrichmentRecipe.Type.INSTANCE);
       System.out.println("Number of Enrichment Recipes: " + enrichmentRecipes.size());
       enrichmentRecipes.forEach(recipe -> System.out.println("Recipe: " + recipe.getId()));

       registration.addRecipes(EnrichmentCategory.ENRICHMENT_TYPE, enrichmentRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(EnrichmentTableScreen.class, 107, 35, 22, 15, EnrichmentCategory.ENRICHMENT_TYPE);
    }
}