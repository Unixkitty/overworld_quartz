package com.unixkitty.overworld_quartz.worldgen;

import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration
{
    private static boolean initialized = false;

    public static void init()
    {
        if (initialized) return;
        initialized = true;

        for (Biome biome : ForgeRegistries.BIOMES.getValues())
        {
            if (biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NETHER)
            {
                biome.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        ModRegistry.OVERWORLD_QUARTZ_ORE.get().getDefaultState(), Config.quartzVeinSize.get()
                                )
                        ).withPlacement(
                                Placement.COUNT_RANGE.configure(
                                        new CountRangeConfig(
                                                Config.quartzVeinsPerChunk.get(),
                                                Config.quartzMinHeight.get(),
                                                0,
                                                Config.quartzMaxHeight.get()
                                        )
                                )
                        )
                );
            }
        }
    }

}
