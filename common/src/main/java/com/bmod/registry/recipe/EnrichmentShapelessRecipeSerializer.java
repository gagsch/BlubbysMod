package com.bmod.registry.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

public class EnrichmentShapelessRecipeSerializer implements RecipeSerializer<EnrichmentRecipe> {
    @Override
    public EnrichmentRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

        JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
        JsonObject additionalObject = GsonHelper.getAsJsonObject(pSerializedRecipe, "requirement");

        NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

        for(int i = 0; i < 9; i++) {
            if (ingredients.size() > i)
            {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
        }

        Ingredient additional = Ingredient.fromJson(additionalObject);

        if(pRecipeId == null)
        {
            Minecraft.getInstance().close();
        }

        return new EnrichmentRecipe(inputs, additional, output, "shapeless", pRecipeId);
    }

    @Override
    public EnrichmentRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        int maxSize = pBuffer.readInt();
        NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

        for(int i = 0; i < 9; i++) {
            if (maxSize > i)
            {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
        }

        Ingredient additional = Ingredient.fromNetwork(pBuffer);

        ItemStack output = pBuffer.readItem();

        return new EnrichmentRecipe(inputs, additional, output, "shapeless", pRecipeId);
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, EnrichmentRecipe pRecipe) {
        pBuffer.writeInt(pRecipe.inputItems.size());


        for (Ingredient ingredient : pRecipe.getIngredients()) {
            ingredient.toNetwork(pBuffer);
        }

        pRecipe.requiredItem.toNetwork(pBuffer);

        pBuffer.writeItem(pRecipe.getResultItem());
    }
}