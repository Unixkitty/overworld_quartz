package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class ModItemTags extends ItemTagsProvider
{
    public ModItemTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper, ModBlockTags blockTagProvider)
    {
        super(generatorIn, blockTagProvider, OverworldQuartz.MODID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        copy(Tags.Blocks.ORES_QUARTZ, Tags.Items.ORES_QUARTZ);
    }

    @Nonnull
    @Override
    public String getName()
    {
        return OverworldQuartz.MODNAME + " " + this.getClass().getSimpleName();
    }
}
