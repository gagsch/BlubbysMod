package com.bmod.registry.recipe;

import com.bmod.BlubbysMod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.bmod.registry.screen.menu.WorkshopMenu.*;

public class WorkshopRecipe implements Recipe<SimpleContainer> {
    public final ItemStack output;
    public final Ingredient base;
    public final NonNullList<Ingredient> additions;
    public final String blueprint;
    public final ResourceLocation id;

    public WorkshopRecipe(ItemStack output, Ingredient base, NonNullList<Ingredient> additions, String blueprint, ResourceLocation id)
    {
        this.output = output;
        this.base = base;
        this.additions = additions;
        this.blueprint = blueprint;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide() || (!container.getItem(BLUEPRINT_SLOT).getOrCreateTag().getString("blueprint").equals(this.blueprint) && !container.getItem(BLUEPRINT_SLOT).getOrCreateTag().getString("blueprint").isEmpty()) || !this.base.test(container.getItem(BASE_SLOT))) {
            return false;
        }

        List<ItemStack> containerItems = new ArrayList<>();
        for (int i = 2; i <= 4; i++) {
            containerItems.add(container.getItem(i));
        }

        for (Ingredient requiredIngredient : additions) {
            boolean foundMatch = false;

            Iterator<ItemStack> iterator = containerItems.iterator();
            while (iterator.hasNext()) {
                ItemStack item = iterator.next();
                if (requiredIngredient.test(item)) {
                    foundMatch = true;
                    iterator.remove();
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }

        // All required additions were matched in the container slots
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(SimpleContainer container) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return this.output.copy();
    }

    public @NotNull Ingredient base() {
        return this.base;
    }

    public @NotNull String blueprint() {
        return this.blueprint;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.additions;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<WorkshopRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "workshop";
    }

    public static class Serializer implements RecipeSerializer<WorkshopRecipe> {
        public static final RecipeSerializer<WorkshopRecipe> INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(BlubbysMod.MOD_ID, "workshop");

        @Override
        public @NotNull WorkshopRecipe fromJson(ResourceLocation recipeId, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));

            JsonArray additionsArray = GsonHelper.getAsJsonArray(jsonObject, "additional");

            NonNullList<Ingredient> additions = NonNullList.withSize(3, Ingredient.EMPTY);

            for(int i = 0; i < 3; i++) {
                if (additionsArray.size() > i)
                {
                    additions.set(i, Ingredient.fromJson(additionsArray.get(i)));
                }
            }

            String blueprint = GsonHelper.getAsString(jsonObject, "blueprint");

            return new WorkshopRecipe(output, base, additions, blueprint, recipeId);
        }

        @Override
        public @NotNull WorkshopRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf friendlyByteBuf) {
            ItemStack output = friendlyByteBuf.readItem();
            Ingredient base = Ingredient.fromNetwork(friendlyByteBuf);

            NonNullList<Ingredient> additions = NonNullList.withSize(3, Ingredient.EMPTY);
            additions.set(0, Ingredient.fromNetwork(friendlyByteBuf));
            additions.set(1, Ingredient.fromNetwork(friendlyByteBuf));
            additions.set(2, Ingredient.fromNetwork(friendlyByteBuf));

            String blueprint = friendlyByteBuf.readUtf();

            return new WorkshopRecipe(output, base, additions, blueprint, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, WorkshopRecipe recipe) {
            friendlyByteBuf.writeItem(recipe.getResultItem());
            recipe.base.toNetwork(friendlyByteBuf);

            recipe.getIngredients().get(0).toNetwork(friendlyByteBuf);
            recipe.getIngredients().get(1).toNetwork(friendlyByteBuf);
            recipe.getIngredients().get(2).toNetwork(friendlyByteBuf);

            friendlyByteBuf.writeUtf(recipe.blueprint);
        }
    }
}
