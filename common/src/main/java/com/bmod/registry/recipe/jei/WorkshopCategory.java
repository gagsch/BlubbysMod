package com.bmod.registry.recipe.jei;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.item.ModItems;
import com.bmod.registry.recipe.WorkshopRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WorkshopCategory implements IRecipeCategory<WorkshopRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(BlubbysMod.MOD_ID, "workshop");
    public static final ResourceLocation TEXTURE = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/workshop.png");

    public static final RecipeType<WorkshopRecipe> WORKSHOP_TYPE = new RecipeType<>(UID, WorkshopRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public WorkshopCategory(IGuiHelper iGuiHelper) {
        this.background = iGuiHelper.createDrawable(TEXTURE, 14, 16, 148, 53);
        this.icon = iGuiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.WORKSHOP.get()));
    }

    @Override
    public @NotNull RecipeType<WorkshopRecipe> getRecipeType() {
        return WORKSHOP_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.blubbysmod.workshop");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull WorkshopRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 66, 32).addItemStack(recipe.getResultItem());

        builder.addSlot(RecipeIngredientRole.INPUT, 48, 2).addIngredients(recipe.getBase());

        ItemStack stack = new ItemStack(ModItems.BLUEPRINT.get());
        stack.getOrCreateTag().putString("blueprint", recipe.getBlueprint());
        builder.addSlot(RecipeIngredientRole.CATALYST, 30, 2).addItemStack(stack);

        for(int i = 0; i < 3; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, 66 + i * 18, 2).addIngredients(recipe.getIngredients().get(i));
        }
    }
}
