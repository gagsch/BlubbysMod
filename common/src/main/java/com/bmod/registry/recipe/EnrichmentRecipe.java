package com.bmod.registry.recipe;

import com.bmod.BlubbysMod;
import com.bmod.util.ItemUtils;
import com.google.gson.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EnrichmentRecipe implements Recipe<SimpleContainer> {
    public final NonNullList<Ingredient> inputItems;
    public final Ingredient requiredItem;
    public final ItemStack outputItemStack;
    public final String recipeType;
    public final int serializerId;
    private final ResourceLocation id;

    public EnrichmentRecipe(NonNullList<Ingredient> inputItems, Ingredient requiredItem, ItemStack outputItemStack, String recipeType, int serializerId, ResourceLocation id) {
        this.inputItems = inputItems;
        this.requiredItem = requiredItem;
        this.outputItemStack = outputItemStack;
        this.recipeType = recipeType;
        this.serializerId = serializerId;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if(level.isClientSide() || !this.requiredItem.test(container.getItem(9))) {
            return false;
        }

        if(Objects.equals(serializerId, 1)) return shapeless(container);

        String[] recipeBoundStrings = recipeType.split("x");
        int[] recipeBounds = {Integer.parseInt(recipeBoundStrings[0]), Integer.parseInt(recipeBoundStrings[1])};

        int firstItem = -1;
        int containerWithoutAir = 0;
        int inputWithoutAir = 0;
        for (int i = 0; i < 9; i++)
        {
            if (container.getItem(i) != ItemStack.EMPTY) {
                containerWithoutAir++;
            }
            if (this.inputItems.get(i).test(container.getItem(i)))
            {
                firstItem = firstItem == -1 ? i : firstItem;
            }
        }

        for (Ingredient inputItem : this.inputItems) {
            if (inputItem != Ingredient.EMPTY) {
                inputWithoutAir++;
            }
        }
        System.out.println("Recipe without air: " + inputWithoutAir);
        System.out.println("Container without air: " + containerWithoutAir);
        if (firstItem == -1 || inputWithoutAir != containerWithoutAir) {
            return false;
        }

        System.out.println("Recipe bound width: " + recipeBounds[0]);
        System.out.println("Recipe bound height: " + recipeBounds[1]);

        for (int x = 0; x < recipeBounds[0]; x++)
        {
            for (int y = 0; y < recipeBounds[1]; y++)
            {
                int recipeIndex = x + (y * recipeBounds[0]);
                int containerIndex = firstItem + x + (y * 3);

                if (containerIndex > 9) {
                    System.out.println("Stopped Recipe! Cause: " + containerIndex + " > 9");
                    return false;
                } else if (!this.inputItems.get(recipeIndex).test(container.getItem(containerIndex))) {
                    System.out.println("Stopped Recipe! Cause: " + Arrays.toString(this.inputItems.get(recipeIndex).getItems()) + " != " + container.getItem(containerIndex));

                    System.out.println("DATA RECIPE INDEX = " + recipeIndex);
                    System.out.println("DATA CONTAINER INDEX = " + containerIndex);

                    return false;
                }
            }
        }

        return true;
    }

    public boolean shapeless(SimpleContainer container)
    {
        int firstItem = -1;

        NonNullList<ItemStack> containerWithoutAir = NonNullList.create();
        NonNullList<Ingredient> inputWithoutAir = NonNullList.create();
        for (int i = 0; i < 9; i++)
        {
            if (container.getItem(i) != ItemStack.EMPTY) {
                containerWithoutAir.add(container.getItem(i));

                firstItem = firstItem == -1 ? i : firstItem;
            }
        }
        for (Ingredient inputItem : this.inputItems) {
            if (inputItem != Ingredient.EMPTY) {
                inputWithoutAir.add(inputItem);
            }
        }
        if (firstItem == -1 || inputWithoutAir.size() != containerWithoutAir.size()) {
            return false;
        }
        List<Ingredient> inputItemsClone = List.copyOf(inputWithoutAir);
        boolean test = true;
        boolean subtest = false;

        for (ItemStack itemStack : containerWithoutAir) {
            if (itemStack != ItemStack.EMPTY) {

                for (Ingredient ingredient : inputItemsClone) {
                    if (!subtest) {
                        subtest = ingredient.test(itemStack);
                    } else break;
                }

            }
            if (!subtest) {
                return false;
            }
        }

        return test;
    }

    @Override
    public @NotNull ItemStack assemble(SimpleContainer container) {
        return outputItemStack.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return outputItemStack.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public Ingredient getRequired() {
        return requiredItem;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return serializerId == 0 ? EnrichmentRecipeSerializer.INSTANCE : EnrichmentShapelessRecipeSerializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }







    public static class Type implements RecipeType<EnrichmentRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "enrichment";
    }








    public static class EnrichmentRecipeSerializer implements RecipeSerializer<EnrichmentRecipe> {

        public static final RecipeSerializer<EnrichmentRecipe> INSTANCE = new EnrichmentRecipeSerializer();
        public static final ResourceLocation ID = new ResourceLocation(BlubbysMod.MOD_ID, "enrichment_shaped");

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

            return new EnrichmentRecipe(inputs, additional, output, recipeWidth + "x" + recipeHeight, 0, pRecipeId);
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

                        Item ite2 = ItemUtils.getItemFromId(item);

                        ingredient = Ingredient.of(ite2);
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
            int maxSize = friendlyByteBuf.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(maxSize, Ingredient.EMPTY);

            String recipeType = friendlyByteBuf.readUtf();

            for(int i = 0; i < maxSize; i++) {
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
            }

            Ingredient additional = Ingredient.fromNetwork(friendlyByteBuf);

            ItemStack output = friendlyByteBuf.readItem();

            return new EnrichmentRecipe(inputs, additional, output, recipeType, 0, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, EnrichmentRecipe recipe) {
            friendlyByteBuf.writeVarInt(recipe.inputItems.size());
            friendlyByteBuf.writeUtf(recipe.recipeType);

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(friendlyByteBuf);
            }

            recipe.requiredItem.toNetwork(friendlyByteBuf);

            friendlyByteBuf.writeItem(recipe.getResultItem());
        }
    }









    public static class EnrichmentShapelessRecipeSerializer implements RecipeSerializer<EnrichmentRecipe> {
        public static final RecipeSerializer<EnrichmentRecipe> INSTANCE = new EnrichmentShapelessRecipeSerializer();
        public static final ResourceLocation ID = new ResourceLocation(BlubbysMod.MOD_ID, "enrichment_shapeless");

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

            return new EnrichmentRecipe(inputs, additional, output, "shapeless", 1, pRecipeId);
        }

        @Override
        public EnrichmentRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int maxSize = pBuffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

            for(int i = 0; i < 9; i++) {
                if (maxSize > i)
                {
                    inputs.set(i, Ingredient.fromNetwork(pBuffer));
                }
            }

            Ingredient additional = Ingredient.fromNetwork(pBuffer);

            ItemStack output = pBuffer.readItem();

            return new EnrichmentRecipe(inputs, additional, output, "shapeless", 1, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, EnrichmentRecipe pRecipe) {
            pBuffer.writeVarInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }

            pRecipe.requiredItem.toNetwork(pBuffer);

            pBuffer.writeItem(pRecipe.getResultItem());
        }
    }
}
