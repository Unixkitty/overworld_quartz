package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class CraftingTableRecipes extends RecipeProvider
{
    public CraftingTableRecipes(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer)
    {
        super.buildCraftingRecipes(consumer);
    }

    @Nonnull
    @Override
    public String getName()
    {
        return OverworldQuartz.MODID + " " + this.getClass().getSimpleName();
    }
}
