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

        OVERWORLD_QUARTZ_ORE = Feature.ORE.withConfiguration(
                new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                        ModRegistry.OVERWORLD_QUARTZ_ORE.get().getDefaultState(),
                        Config.quartzVeinSize.get()
                )
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(
                        Config.quartzMinHeight.get(),
                        0,
                        Config.quartzMaxHeight.get()
                )).square().func_242731_b(Config.quartzVeinsPerChunk.get())
        );

        Registry.register(registry, "ore_pink_sapphire", OVERWORLD_QUARTZ_ORE);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        if (event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.NETHER)
        {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OreGeneration.OVERWORLD_QUARTZ_ORE);
        }
    }

}
