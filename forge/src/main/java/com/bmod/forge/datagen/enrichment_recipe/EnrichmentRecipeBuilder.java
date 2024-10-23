package com.bmod.forge.datagen.enrichment_recipe;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class EnrichmentRecipeBuilder<T extends EnrichmentRecipeBuilder<T>> {
    protected ResourceLocation requirementItemId;
    protected ResourceLocation outputItemId;
    protected int outputItemCount;
    protected final int page_number;
    public static int donePageNumbers = 0;

    public EnrichmentRecipeBuilder() {
        this.page_number = donePageNumbers++;
    }

    public static EnrichmentShapelessRecipe shapeless(Item item)
    {
        return EnrichmentRecipeBuilder.shapeless(item, 1);
    }

    public static EnrichmentShapelessRecipe shapeless(Item item, int count)
    {
        return new EnrichmentShapelessRecipe().recipe(item, count);
    }

    public static EnrichmentShapedRecipe shapedRecipe(Item item)
    {
        return EnrichmentRecipeBuilder.shapedRecipe(item, 1);
    }

    public static EnrichmentShapedRecipe shapedRecipe(Item item, int count)
    {
        return new EnrichmentShapedRecipe().recipe(item, count);
    }

    @SuppressWarnings("unchecked")
    public T requirement(Item item) {
        this.requirementItemId = location(item);
        return (T) this;
    }

    public T recipe(Item item) {
        return this.recipe(item, 1);
    }

    @SuppressWarnings("unchecked")
    public T recipe(Item item, int count) {
        this.outputItemId = location(item);
        this.outputItemCount = count;
        return (T) this;
    }

    public void save(Consumer<FinishedRecipe> consumer, JsonObject jsonObject, RecipeSerializer<?> type, ResourceLocation id) {
        consumer.accept(new Result(jsonObject, type, id));
    }

    public ResourceLocation location(Item item) {
        return item.builtInRegistryHolder().key().location();
    }

    public static class Result implements FinishedRecipe
    {
        private final JsonObject jsonObject;
        private final RecipeSerializer<?> type;
        private final ResourceLocation id;

        public Result(JsonObject jsonObject, RecipeSerializer<?> type, ResourceLocation id)
        {
            this.jsonObject = jsonObject;
            this.type = type;
            this.id = id;
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject jsonObject) {
            this.jsonObject.entrySet().forEach(entry -> jsonObject.add(entry.getKey(), entry.getValue()));
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return type;
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
