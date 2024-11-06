package com.bmod.forge.datagen.workshop_recipe;

import com.bmod.registry.recipe.WorkshopRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WorkshopRecipeBuilder {


    protected ResourceLocation baseLocation;
    protected String blueprint;
    protected List<ResourceLocation> additionLocations = new ArrayList<>();
    protected ResourceLocation outputItemId;
    protected int outputItemCount;

    public static WorkshopRecipeBuilder recipe(Item item) {
        return recipe(item, 1);
    }

    public static WorkshopRecipeBuilder recipe(Item item, int count) {
        WorkshopRecipeBuilder workshopRecipeBuilder = new WorkshopRecipeBuilder();

        workshopRecipeBuilder.outputItemId = location(item);
        workshopRecipeBuilder.outputItemCount = count;
        return workshopRecipeBuilder;
    }

    public WorkshopRecipeBuilder base(Item item)
    {
        this.baseLocation = location(item);
        return this;
    }

    public WorkshopRecipeBuilder addition(Item item)
    {
        this.additionLocations.add(location(item));
        if (this.additionLocations.size() > 3)
        {
            throw new Error();
        }
        return this;
    }

    public WorkshopRecipeBuilder blueprint(String tag)
    {
        this.blueprint = tag;
        return this;
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        JsonObject recipe = new JsonObject();

        JsonArray gridPattern = new JsonArray();
        for (ResourceLocation ingredient : this.additionLocations)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", ingredient.toString());

            gridPattern.add(jsonObject);
        }
        recipe.add("additional", gridPattern);

        JsonObject base = new JsonObject();
        base.addProperty("item", this.baseLocation.toString());
        recipe.add("base", base);

        recipe.addProperty("blueprint", this.blueprint);

        JsonObject result = new JsonObject();
        result.addProperty("count", this.outputItemCount);
        result.addProperty("item", this.outputItemId.toString());
        recipe.add("result", result);

        consumer.accept(new Result(recipe, this.outputItemId));
    }

    public static ResourceLocation location(Item item) {
        return item.builtInRegistryHolder().key().location();
    }

    public static class Result implements FinishedRecipe
    {
        private final JsonObject jsonObject;
        private final ResourceLocation id;

        public Result(JsonObject jsonObject, ResourceLocation id)
        {
            this.jsonObject = jsonObject;
            this.id = id;
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject jsonObject) {
            this.jsonObject.entrySet().forEach(entry -> jsonObject.add(entry.getKey(), entry.getValue()));
        }

        @Override
        public @NotNull ResourceLocation getId() {
            return this.id;
        }

        @Override
        public @NotNull RecipeSerializer<?> getType() {
            return WorkshopRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}