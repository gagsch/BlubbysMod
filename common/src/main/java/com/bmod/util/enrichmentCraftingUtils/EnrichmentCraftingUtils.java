package com.bmod.util.enrichmentCraftingUtils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public class EnrichmentCraftingUtils {

    public static ItemStack getResult(List<Object> inputs) {
        List<Object> inputSubset = inputs.subList(0, 9);
        List<Object> inputWithoutAir = new ArrayList<>(List.of());
        Object requirement = inputs.get(9);

        for (int i = 0; i < 9; i++)
        {
            if (inputSubset.get(i) != Items.AIR) {
                inputWithoutAir.add(inputSubset.get(i));
            }
        }

        for (List<Object> recipe : new EnrichmentCraftingRecipes().getRecipes()) {
            String recipeShape = (String) recipe.get(recipe.size() - 1);
            ItemStack recipeOutput = (ItemStack) recipe.get(recipe.size() - 2);
            Item requiredInput = (Item) recipe.get(recipe.size() - 3);

            List<Object> recipeSubset = recipe.subList(0, recipe.size() - 3);

            if (!requirement.equals(requiredInput) || !(inputWithoutAir.size() == recipeSubset.size())) continue;

            if (Objects.equals(recipeShape, "shapeless") && checkRecipe(inputSubset, recipeSubset)) {
                return recipeOutput;
            }
            else if (checkItemsShapedRecipe(inputSubset, recipeSubset, recipeShape)) {
                return recipeOutput;
            }
        }
        return ItemStack.EMPTY;
    }

    private static boolean checkItemsShapedRecipe(List<Object> inputs, List<Object> recipe, String recipeShape)
    {
        int[] gridBounds = getItemBounds(inputs);
        String inputShape = gridBounds[0] + "x" + gridBounds[1];

        System.out.println("inventory shape is: " + inputShape + " and recipe shape is: " + recipeShape);

        if (!inputShape.equals(recipeShape)) return false;

        boolean isSame = true;

        for (int width = 0; width < gridBounds[0]; width++)
        {
            for (int height = 0; height < gridBounds[1]; height++)
            {
                Object inputItemAtPos = inputs.get((width + (height * 3)) + (gridBounds[2] + (gridBounds[3] * 3)));
                Object recipeItemAtPos = recipe.get(width + (height * gridBounds[0]));

                System.out.println("Getting recipe item at: " + (width + (height * gridBounds[0])));

                if (!(inputItemAtPos == recipeItemAtPos))
                {
                    System.out.println("Broken! " + inputItemAtPos + recipeItemAtPos);
                    isSame = false;
                    break;
                }
            }
            if (!isSame) break;
        }

        return isSame;
    }

    private static int[] getItemBounds(List<Object> inputs) {
        int minX = 3, minY = 3, maxX = -1, maxY = -1;

        // Loop through the grid to find bounds
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (inputs.get(y * 3 + x) != Items.AIR) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        int width = maxX >= 0 ? maxX - minX + 1 : 0;
        int height = maxY >= 0 ? maxY - minY + 1 : 0;

        return new int[] { width, height, minX, minY };
    }

    public static Object get(List<Object> inputs, int x, int y)
    {
        return inputs.get((x - 1) * (y - 1));
    }

    public static boolean checkRecipe(List<Object> inputs, List<Object> recipe) {
        HashMap<Object, Integer> recipeCount = new HashMap<>();
        for (Object item : recipe) {
            recipeCount.put(item, recipeCount.getOrDefault(item, 0) + 1);
        }

        HashMap<Object, Integer> inputCount = new HashMap<>();
        for (Object item : inputs) {
            inputCount.put(item, inputCount.getOrDefault(item, 0) + 1);
        }

        for (Map.Entry<Object, Integer> entry : recipeCount.entrySet()) {
            if (inputCount.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}