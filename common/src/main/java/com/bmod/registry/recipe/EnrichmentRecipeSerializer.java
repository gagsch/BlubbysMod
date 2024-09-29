package com.bmod.registry.recipe;

import com.bmod.util.ItemUtils;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EnrichmentRecipeSerializer implements RecipeSerializer<EnrichmentRecipe> {

    @Override
    public EnrichmentRecipe fromJson(ResourceLocation pRecipeId, JsonObject jsonObject) {
        JsonObject keyJson = jsonObject.getAsJsonObject("key");
        String[][] pattern = parsePattern(jsonObject.getAsJsonArray("pattern"));
        Map<Character, Ingredient> key = parseKey(keyJson);

        NonNullList<Ingredient> inputs = NonNullList.withSize(pattern.length * pattern[0].length, Ingredient.EMPTY);

        int recipeWidth = pattern[0].length;
        int recipeHeight = pattern.length;

        System.out.println("width " + recipeWidth);
        System.out.println("height " + recipeHeight);

        for (int y = 0; y < recipeHeight; y++) {
            for (int x = 0; x < recipeWidth; x++) {
                char charAt = pattern[y][x].charAt(0);
                if (charAt == ' ') {
                    inputs.set(y * recipeWidth + x, Ingredient.EMPTY);
                } else {
                    Ingredient ingredient = key.get(charAt);
                    if (ingredient == null) {
                        throw new JsonSyntaxException("Pattern character '" + charAt + "' does not have a matching key entry.");
                    }
                    inputs.set(y * recipeWidth + x, ingredient);
                }
            }
        }

        JsonObject additionalObject = GsonHelper.getAsJsonObject(jsonObject, "requirement");
        Ingredient additional = Ingredient.fromJson(additionalObject);

        ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

        return new EnrichmentRecipe(inputs, additional, output, recipeWidth + "x" + recipeHeight, pRecipeId);
    }

    private String[][] parsePattern(JsonArray patternJson) {
        int height = patternJson.size();
        String[][] pattern = new String[height][];

        for (int i = 0; i < height; i++) {
            pattern[i] = patternJson.get(i).getAsString().split("");
        }

        return pattern;
    }


    private Map<Character, Ingredient> parseKey(JsonObject keyJson) {
        Map<Character, Ingredient> key = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : keyJson.entrySet()) {
            char symbol = entry.getKey().charAt(0);

            JsonElement element = entry.getValue();

            Ingredient ingredient;
            if (element.isJsonObject()) {
                JsonObject ingredientJson = element.getAsJsonObject();
                if (ingredientJson.has("item")) {
                    String item = ingredientJson.get("item").getAsString();
                    ingredient = Ingredient.of(ItemUtils.getItemFromId(item));
                } else {
                    throw new JsonParseException("Invalid ingredient: " + entry.getKey());
                }
            } else {
                throw new JsonParseException("Invalid key format for: " + entry.getKey());
            }

            key.put(symbol, ingredient);
        }

        return key;
    }


    @Override
    public EnrichmentRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf friendlyByteBuf) {
        NonNullList<Ingredient> inputs = NonNullList.create();

        String recipeType = friendlyByteBuf.readUtf();

        inputs.replaceAll(ignored -> Ingredient.fromNetwork(friendlyByteBuf));

        Ingredient additional = Ingredient.fromNetwork(friendlyByteBuf);

        ItemStack output = friendlyByteBuf.readItem();

        return new EnrichmentRecipe(inputs, additional, output, recipeType, pRecipeId);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, EnrichmentRecipe recipe) {
        friendlyByteBuf.writeUtf(recipe.recipeType);

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.toNetwork(friendlyByteBuf);
        }

        recipe.requiredItem.toNetwork(friendlyByteBuf);

        friendlyByteBuf.writeItem(recipe.getResultItem());
    }
}