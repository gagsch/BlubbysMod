package com.bmod.registry.recipe.jei;

import com.bmod.BlubbysMod;
import com.bmod.registry.block.ModBlocks;
import com.bmod.registry.recipe.EnrichmentRecipe;
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
import org.jetbrains.annotations.Nullable;

public class EnrichmentCategory implements IRecipeCategory<EnrichmentRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(BlubbysMod.MOD_ID, "enrichment");
    public static final ResourceLocation TEXTURE = new ResourceLocation(BlubbysMod.MOD_ID, "textures/gui/enrichment_table.png");

    public static final RecipeType<EnrichmentRecipe> ENRICHMENT_TYPE = new RecipeType<>(UID, EnrichmentRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public EnrichmentCategory(IGuiHelper iGuiHelper) {
        this.background = iGuiHelper.createDrawable(TEXTURE, 14, 16, 148, 54);
        this.icon = iGuiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ENRICHMENT_TABLE.get()));
    }

    @Override
    public RecipeType<EnrichmentRecipe> getRecipeType() {
        return ENRICHMENT_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.blubbysmod.enrichment_table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, EnrichmentRecipe recipe, IFocusGroup focuses) {
        int j, k;
        for(j = 0; j < 3; ++j) {
            for(k = 0; k < 3; ++k) {
                builder.addSlot(RecipeIngredientRole.INPUT, 33 + k * 18, 1 + j * 18).addIngredients(recipe.getIngredients().get(k + j * 3));
            }
        }

        builder.addSlot(RecipeIngredientRole.CATALYST, 5, 19).addIngredients(recipe.getRequired());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 127, 19).addItemStack(recipe.getResultItem());
    }
}
