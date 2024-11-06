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

    public static final RegistrySupplier<RecipeSerializer<WorkshopRecipe>> WORKSHOP_SERIALIZER = RECIPE_SERIALIZERS.register("workshop", () -> WorkshopRecipe.Serializer.INSTANCE);
    public static final RegistrySupplier<RecipeType<WorkshopRecipe>> WORKSHOP_TYPE = RECIPE_TYPES.register(WorkshopRecipe.Type.ID, () -> WorkshopRecipe.Type.INSTANCE);
}