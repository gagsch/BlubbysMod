package com.bmod.util.enrichmentCraftingUtils;

import com.bmod.registry.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EnrichmentCraftingRecipes {
    private final HashSet<List<Object>> recipes = new HashSet<>();

    public EnrichmentCraftingRecipes() {
        this.recipes.add(new ArrayList<>(List.of(
                Items.CLOCK,
                ModItems.SOUL_TIME.get(), // Required Item
                new ItemStack(ModItems.CHRONOS_CLOCK.get(), 1), // Output
                "shapeless"
        )));

        this.recipes.add(new ArrayList<>(List.of(
                ModItems.DIVINE_ALLOY.get(),
                ModItems.ESSENCE_STONE.get(),
                ModItems.ESSENCE_INFINITY.get(), // Required Item
                new ItemStack(ModItems.LUCKY_ROCK.get(), 1), // Output
                "shapeless"
        )));

        this.recipes.add(new ArrayList<>(List.of(
                ModItems.SOUL_BALANCE.get(), ModItems.SOUL_ELEMENTS.get(), Items.AIR,
                ModItems.SOUL_TIME.get(), ModItems.SOUL_FRAGMENT.get(), ModItems.SOUL_DIMENSIONS.get(),
                ModItems.SOUL_SPACE.get(), Items.AIR, Items.AIR,
                ModItems.ESSENCE_INFINITY.get(), // Required Item
                new ItemStack(ModItems.SOUL_INFINITY.get(), 1), // Output
                "shapeless"
        )));

        this.recipes.add(new ArrayList<>(List.of(
                ModItems.GOLDEN_CORE.get(), ModItems.IRON_CORE.get(), ModItems.DIAMOND_CORE.get(),
                ModItems.RUBY.get(), ModItems.SOUL_DUST.get(), ModItems.RUBY.get(),
                ModItems.DIVINE_CORE.get(), ModItems.NETHERITE_CORE.get(), ModItems.SCARLITE_CORE.get(), // 3x3
                ModItems.SOUL_INFINITY.get(), // Required Item
                new ItemStack(ModItems.SOUL_INFUSED_RUBY.get(), 1), // Output
                "shapeless"
        )));
    }

    public HashSet<List<Object>> getRecipes() {
        return recipes;
    }

}
