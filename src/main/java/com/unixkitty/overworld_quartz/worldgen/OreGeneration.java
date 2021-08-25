package com.unixkitty.overworld_quartz.worldgen;

import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration
{
    protected static ConfiguredFeature<?, ?> OVERWORLD_QUARTZ_ORE;

    public static void register()
    {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;

        OVERWORLD_QUARTZ_ORE = Feature.ORE.configured(
                new OreConfiguration(
                        OreConfiguration.Predicates.NATURAL_STONE, ModRegistry.OVERWORLD_QUARTZ_ORE.get().defaultBlockState(), Config.quartzVeinSize.get()
                )
        ).rangeUniform(
                VerticalAnchor.aboveBottom(Config.quartzMinHeight.get()), VerticalAnchor.belowTop(Config.quartzMaxHeight.get())
        ).squared().count(Config.quartzVeinsPerChunk.get());

        Registry.register(registry, "ore_quartz_overworld", OVERWORLD_QUARTZ_ORE);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreGeneration.OVERWORLD_QUARTZ_ORE);
        }
    }

}
