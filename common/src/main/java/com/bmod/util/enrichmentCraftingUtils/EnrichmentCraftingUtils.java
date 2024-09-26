package com.bmod.util.enrichmentCraftingUtils;

import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrichmentCraftingUtils {
    public static ItemStack getResult(List<Object> inputs) {
        List<Object> inputSubset = inputs.subList(0, 9);
        Object requirement = inputs.get(9);

        for (List<Object> recipe : new EnrichmentCraftingRecipes().getRecipes()) {
            boolean shapelessCrafting = (boolean) recipe.get(recipe.size() - 1);
            ItemStack recipeOutput = (ItemStack) recipe.get(recipe.size() - 2);
            RequirementItem requiredInput = (RequirementItem) recipe.get(recipe.size() - 3);

            System.out.println(recipe + ":" + inputs);

            if (!requirement.equals(requiredInput.getRequirement())) {
                continue;
            }

            if (shapelessCrafting && checkRecipe(inputSubset, recipe.subList(0, recipe.size() - 3))) {
                return recipeOutput;
            }

            switch (recipe.size()) {
                case 4: // 1x1
                    if (inputSubset.contains(recipe.get(0))) {
                        return recipeOutput;
                    }
                    break;

                case 7: // 2x2
                    List<Object> inputSubject1 = createInputSubject(inputSubset, 0);
                    List<Object> inputSubject2 = createInputSubject(inputSubset, 1);
                    List<Object> inputSubject3 = createInputSubject(inputSubset, 3);
                    List<Object> inputSubject4 = createInputSubject(inputSubset, 4);

                    boolean check = checkRecipe(inputSubject1, recipe.subList(0, 4)) ||
                            checkRecipe(inputSubject2, recipe.subList(0, 4)) ||
                            checkRecipe(inputSubject3, recipe.subList(0, 4)) ||
                            checkRecipe(inputSubject4, recipe.subList(0, 4));

                    if (check)
                        return recipeOutput;
                    break;

                case 12:
                    boolean isSame = true;
                    for (int i = 0; i < 9; i++) {
                        if (!recipe.get(i).equals(inputSubset.get(i))) {
                            isSame = false;
                            break;
                        }
                    }
                    if (isSame) {
                        return recipeOutput;
                    }
                    break;
                default:
                    if (checkRecipe(inputSubset, recipe.subList(0, recipe.size() - 3)))
                    {
                        return recipeOutput;
                    }
                    break;
            }
        }
        return ItemStack.EMPTY;
    }

    private static List<Object> createInputSubject(List<Object> inputSubset, int base) {
        return List.of(
                inputSubset.get(base),
                inputSubset.get(base + 1),
                inputSubset.get(base + 3),
                inputSubset.get(base + 4)
        );
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