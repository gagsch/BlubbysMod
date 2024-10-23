package com.bmod.forge.datagen.enrichment_recipe;

import com.bmod.registry.recipe.ModRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EnrichmentShapelessRecipe extends EnrichmentRecipeBuilder<EnrichmentShapelessRecipe> {
    protected List<ResourceLocation> ingredients = new ArrayList<>();

    public EnrichmentShapelessRecipe() {
        super();
    }

    public EnrichmentShapelessRecipe ingredient(Item item) {
        this.ingredients.add(location(item));
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        JsonObject recipe = new JsonObject();

        JsonArray gridPattern = new JsonArray();
        for (ResourceLocation ingredient : ingredients)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", ingredient.toString());

            gridPattern.add(jsonObject);
        }
        recipe.add("ingredients", gridPattern);

        recipe.addProperty("requirement", this.requirementItemId.toString());

        JsonObject result = new JsonObject();
        result.addProperty("count", this.outputItemCount);
        result.addProperty("item", this.outputItemId.toString());
        recipe.add("result", result);

        recipe.addProperty("page_number", this.page_number);

        super.save(consumer, recipe, ModRecipeTypes.ENRICHMENT_SHAPELESS_SERIALIZER.get(), this.outputItemId);
    }
}
