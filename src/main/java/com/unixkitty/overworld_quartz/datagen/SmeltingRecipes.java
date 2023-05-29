package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class SmeltingRecipes extends RecipeProvider
{
    public SmeltingRecipes(DataGenerator generatorIn)
    {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer)
    {
        addBasicOreCooking(consumer, ModRegistry.OVERWORLD_QUARTZ_ORE.get(), Items.QUARTZ, "overworld_quartz_ore");
        addBasicOreCooking(consumer, ModRegistry.DEEPSLATE_QUARTZ_ORE.get(), Items.QUARTZ, "deepslate_quartz_ore");
    }

    protected void addBasicOreCooking(Consumer<FinishedRecipe> consumer, ItemLike input, ItemLike result, String name)
    {
        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        result,
                        0.2F,
                        200)
                .unlockedBy("has_" + name, has(input))
                .save(consumer, new ResourceLocation(OverworldQuartz.MODID, "smelting/" + name + "_from_smelting"));

        SimpleCookingRecipeBuilder.blasting(
                        Ingredient.of(input),
                        result,
                        0.2F,
                        100)
                .unlockedBy("has_" + name, has(input))
                .save(consumer, new ResourceLocation(OverworldQuartz.MODID, "blasting/" + name + "_from_blasting"));
    }

    @Nonnull
    @Override
    public String getName()
    {
        return OverworldQuartz.MODNAME + " " + this.getClass().getSimpleName();
    }
}
