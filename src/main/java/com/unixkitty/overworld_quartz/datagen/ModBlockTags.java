package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class ModBlockTags extends BlockTagsProvider
{
    public ModBlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper)
    {
        super(generatorIn, OverworldQuartz.MODID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        tag(Tags.Blocks.ORES_QUARTZ).add(ModRegistry.OVERWORLD_QUARTZ_ORE.get(), ModRegistry.DEEPSLATE_QUARTZ_ORE.get());

        tag(BlockTags.STONE_ORE_REPLACEABLES).add(ModRegistry.OVERWORLD_QUARTZ_ORE.get());

        tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(ModRegistry.DEEPSLATE_QUARTZ_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModRegistry.OVERWORLD_QUARTZ_ORE.get(), ModRegistry.DEEPSLATE_QUARTZ_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModRegistry.OVERWORLD_QUARTZ_ORE.get(), ModRegistry.DEEPSLATE_QUARTZ_ORE.get());
    }

    @Nonnull
    @Override
    public String getName()
    {
        return OverworldQuartz.MODNAME + " " + this.getClass().getSimpleName();
    }
}
