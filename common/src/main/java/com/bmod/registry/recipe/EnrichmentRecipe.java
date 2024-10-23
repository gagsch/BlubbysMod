package com.bmod.registry.recipe;

import com.bmod.BlubbysMod;
import com.bmod.util.ItemUtils;
import com.google.gson.*;
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
    public final int pageNumber;
    private final ResourceLocation id;

    private int width;
    private int height;

    public EnrichmentRecipe(NonNullList<Ingredient> inputItems, Ingredient requiredItem, ItemStack outputItemStack, String recipeType, int pageNumber, ResourceLocation id) {
        this.inputItems = inputItems;
        this.requiredItem = requiredItem;
        this.outputItemStack = outputItemStack;
        this.recipeType = recipeType;
        this.pageNumber = pageNumber;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide() || !this.requiredItem.test(container.getItem(9))) {
            return false;
        }

        if (Objects.equals(recipeType, "shapeless")) return shapeless(container);

        String[] recipeBoundStrings = recipeType.split("x");
        width = Integer.parseInt(recipeBoundStrings[0]);
        height = Integer.parseInt(recipeBoundStrings[1]);

        for (int startRow = 0; startRow <= 3 - height; ++startRow) {
            for (int startCol = 0; startCol <= 3 - width; ++startCol) {
                if (this.shaped(container, startCol, startRow, true) || this.shaped(container, startCol, startRow, false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shaped(SimpleContainer container, int startCol, int startRow, boolean isNormal) {
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int gridX = startCol + i;
                int gridY = startRow + j;

                if (gridX >= 3 || gridY >= 3) {
                    return false;
                }

                int recipeIndex;
                if (isNormal) {
                    recipeIndex = i + j * width;
                } else {
                    recipeIndex = (width - i - 1) + j * width;
                }

                Ingredient ingredient = inputItems.get(recipeIndex);

                ItemStack currentItem = container.getItem(gridX + gridY * 3);

                if (!ingredient.test(currentItem)) {
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
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    public Ingredient getRequired() {
        return requiredItem;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    public int getPageNumber() { return pageNumber; }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Objects.equals(recipeType, "shapeless") ? EnrichmentShapelessRecipeSerializer.INSTANCE : EnrichmentRecipeSerializer.INSTANCE;
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

            Item additionalObject = GsonHelper.getAsItem(jsonObject, "requirement");
            Ingredient additional = Ingredient.of(additionalObject);

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

            int pageNumber = GsonHelper.getAsInt(jsonObject, "page_number");

            return new EnrichmentRecipe(inputs, additional, output, recipeWidth + "x" + recipeHeight, pageNumber, pRecipeId);
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

            int ancientPageNumber = friendlyByteBuf.readVarInt();

            return new EnrichmentRecipe(inputs, additional, output, recipeType, ancientPageNumber, pRecipeId);
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

            friendlyByteBuf.writeVarInt(recipe.pageNumber);
        }
    }

    public static class EnrichmentShapelessRecipeSerializer implements RecipeSerializer<EnrichmentRecipe> {
        public static final RecipeSerializer<EnrichmentRecipe> INSTANCE = new EnrichmentShapelessRecipeSerializer();
        public static final ResourceLocation ID = new ResourceLocation(BlubbysMod.MOD_ID, "enrichment_shapeless");

        @Override
        public EnrichmentRecipe fromJson(ResourceLocation recipeId, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            Item additionalObject = GsonHelper.getAsItem(jsonObject, "requirement");

            NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

            for(int i = 0; i < 9; i++) {
                if (ingredients.size() > i)
                {
                    inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
                }
            }

            Ingredient additional = Ingredient.of(additionalObject);

            int pageNumber = GsonHelper.getAsInt(jsonObject, "page_number");

            return new EnrichmentRecipe(inputs, additional, output, "shapeless", pageNumber, recipeId);
        }

        @Override
        public EnrichmentRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf friendlyByteBuf) {
            int maxSize = friendlyByteBuf.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(9, Ingredient.EMPTY);

            for(int i = 0; i < 9; i++) {
                if (maxSize > i)
                {
                    inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
                }
            }

            Ingredient additional = Ingredient.fromNetwork(friendlyByteBuf);

            ItemStack output = friendlyByteBuf.readItem();

            int pageNumber = friendlyByteBuf.readVarInt();

            return new EnrichmentRecipe(inputs, additional, output, "shapeless", pageNumber, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, EnrichmentRecipe recipe) {
            friendlyByteBuf.writeVarInt(recipe.inputItems.size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(friendlyByteBuf);
            }

            recipe.requiredItem.toNetwork(friendlyByteBuf);

            friendlyByteBuf.writeItem(recipe.getResultItem());

            friendlyByteBuf.writeVarInt(recipe.pageNumber);
        }
    }
}
