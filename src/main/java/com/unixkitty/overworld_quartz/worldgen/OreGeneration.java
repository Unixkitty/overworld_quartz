package com.unixkitty.overworld_quartz.worldgen;


import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class OreGeneration
{
    protected static ConfiguredFeature<?, ?> OVERWORLD_QUARTZ_ORE_FEATURE = Feature.ORE
            .configured(
                    new OreConfiguration(
                            OreFeatures.STONE_ORE_REPLACEABLES, ModRegistry.OVERWORLD_QUARTZ_ORE.get().defaultBlockState(),
                            Config.quartzVeinSize.get()
                    ));
    protected static PlacedFeature OVERWORLD_QUARTZ_ORE = OVERWORLD_QUARTZ_ORE_FEATURE.placed(
            commonOrePlacement(
                    Config.quartzVeinsPerChunk.get(), HeightRangePlacement.uniform(
                            VerticalAnchor.absolute(Config.quartzMinHeight.get()), VerticalAnchor.absolute(Config.quartzMaxHeight.get())
                    )
            )
    );

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_DECORATION).add(() -> OVERWORLD_QUARTZ_ORE);
        }
    }

    public static void initFeatures()
    {
        FeatureUtils.register(new ResourceLocation(OverworldQuartz.MODID, "ore_quartz_overworld").toString(), OVERWORLD_QUARTZ_ORE_FEATURE);
        PlacementUtils.register(new ResourceLocation(OverworldQuartz.MODID, "ore_quartz_overworld").toString(), OVERWORLD_QUARTZ_ORE);
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier)
    {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }
}
