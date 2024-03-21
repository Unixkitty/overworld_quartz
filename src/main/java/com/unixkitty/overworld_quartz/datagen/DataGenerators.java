package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OverworldQuartz.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        /*DataGenerator generator = event.getGenerator();
        ModBlockTags blockTagProvider = new ModBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new ModItemTags(generator, event.getExistingFileHelper(), blockTagProvider));
        generator.addProvider(event.includeServer(), new SmeltingRecipes(generator));
        generator.addProvider(event.includeServer(), new ModLootTables(generator));*/
    }
}
