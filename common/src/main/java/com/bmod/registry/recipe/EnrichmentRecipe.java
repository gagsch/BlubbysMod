package com.bmod.registry.recipe;

import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EnrichmentRecipe implements Recipe<SimpleContainer> {
    public final NonNullList<Ingredient> inputItems;
    public final Ingredient requiredItem;
    public final ItemStack outputItemStack;
    public final String recipeType;
    private final ResourceLocation id;

    public EnrichmentRecipe(NonNullList<Ingredient> inputItems, Ingredient requiredItem, ItemStack outputItemStack, String recipeType, ResourceLocation id) {
        this.inputItems = inputItems;
        this.requiredItem = requiredItem;
        this.outputItemStack = outputItemStack;
        this.recipeType = recipeType;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if(level.isClientSide() || !this.requiredItem.test(container.getItem(9))) {
            return false;
        }

        if(Objects.equals(recipeType, "shapeless")) return shapeless(container);

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
        return new EnrichmentRecipeSerializer();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<EnrichmentRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "enrichment";
    }
}
