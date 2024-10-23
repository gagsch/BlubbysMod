package com.bmod.forge.datagen.enrichment_recipe;

import com.bmod.registry.recipe.ModRecipeTypes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class EnrichmentShapedRecipe extends EnrichmentRecipeBuilder<EnrichmentShapedRecipe> {
    protected List<String> pattern = new ArrayList<>();
    protected HashMap<Character, ResourceLocation> keys = new HashMap<>();

    public EnrichmentShapedRecipe() {
        super();
    }

    public static EnrichmentShapedRecipe shaped(Item item)
    {
        return EnrichmentShapedRecipe.shaped(item, 1);
    }

    public static EnrichmentShapedRecipe shaped(Item item, int count)
    {
        return new EnrichmentShapedRecipe().recipe(item, count);
    }

    public EnrichmentShapedRecipe pattern(String patternChars) {
        this.pattern.add(patternChars);
        return this;
    }

    public EnrichmentShapedRecipe key(char key, Item item) {
        this.keys.put(key, location(item));
        return this;
    }

    public EnrichmentShapedRecipe save(Consumer<FinishedRecipe> consumer) {
        JsonObject recipe = new JsonObject();

        JsonObject charKeys = new JsonObject();
        for (Character key : this.keys.keySet()) {
            JsonObject itemObject = new JsonObject();
            itemObject.addProperty("item", this.keys.get(key).toString());

            charKeys.add(key.toString(), itemObject);
        }
        recipe.add("key", charKeys);

        JsonArray gridPattern = new JsonArray();
        for (String patternRow : pattern)
        {
            gridPattern.add(patternRow);
        }
        recipe.add("pattern", gridPattern);

        recipe.addProperty("requirement", this.requirementItemId.toString());

        JsonObject result = new JsonObject();
        result.addProperty("count", this.outputItemCount);
        result.addProperty("item", this.outputItemId.toString());
        recipe.add("result", result);

        recipe.addProperty("page_number", this.page_number);

        super.save(consumer, recipe, ModRecipeTypes.ENRICHMENT_SERIALIZER.get(), this.outputItemId);
        return this;
    }
}
