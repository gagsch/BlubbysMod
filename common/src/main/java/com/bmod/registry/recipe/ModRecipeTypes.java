package com.bmod.registry.recipe;

import com.bmod.BlubbysMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.RECIPE_SERIALIZER_REGISTRY);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BlubbysMod.MOD_ID, Registry.RECIPE_TYPE_REGISTRY);

    public static final RegistrySupplier<RecipeSerializer<EnrichmentRecipe>> ENRICHMENT_SHAPELESS_SERIALIZER = RECIPE_SERIALIZERS.register("enrichment_shapeless", EnrichmentShapelessRecipeSerializer::new);
    public static final RegistrySupplier<RecipeSerializer<EnrichmentRecipe>> ENRICHMENT_SERIALIZER = RECIPE_SERIALIZERS.register("enrichment_shaped", EnrichmentRecipeSerializer::new);
    public static final RegistrySupplier<RecipeType<EnrichmentRecipe>> ENRICHMENT_TYPE = RECIPE_TYPES.register(EnrichmentRecipe.Type.ID, () -> EnrichmentRecipe.Type.INSTANCE);
}