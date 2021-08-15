package com.unixkitty.overworld_quartz.worldgen;

import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration
{
    protected static ConfiguredFeature<?, ?> OVERWORLD_QUARTZ_ORE;

    public static void register()
    {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        OVERWORLD_QUARTZ_ORE = Feature.ORE.configured(
                new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        ModRegistry.OVERWORLD_QUARTZ_ORE.get().defaultBlockState(),
                        Config.quartzVeinSize.get()
                )
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(
                        Config.quartzMinHeight.get(),
                        0,
                        Config.quartzMaxHeight.get()
                )).squared().count(Config.quartzVeinsPerChunk.get())
        );

        Registry.register(registry, "ore_pink_sapphire", OVERWORLD_QUARTZ_ORE);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        if (event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.NETHER)
        {
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OreGeneration.OVERWORLD_QUARTZ_ORE);
        }
    }

}
