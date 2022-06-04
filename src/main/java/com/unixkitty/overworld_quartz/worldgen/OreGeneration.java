package com.unixkitty.overworld_quartz.worldgen;


import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
    private static final String quartz_ore_feature_id = "ore_overworld_quartz";

    public static Holder<ConfiguredFeature<OreConfiguration, ?>> OVERWORLD_QUARTZ_ORE_FEATURE = FeatureUtils.register(
            quartz_ore_feature_id,
            Feature.ORE,
            new OreConfiguration(
                    OreFeatures.STONE_ORE_REPLACEABLES,
                    ModRegistry.OVERWORLD_QUARTZ_ORE.get().defaultBlockState(),
                    Config.quartzVeinSize.get()
            )
    );

    public static Holder<PlacedFeature> OVERWORLD_QUARTZ_ORE_PLACED_FEATURE = PlacementUtils.register(
            quartz_ore_feature_id,
            OVERWORLD_QUARTZ_ORE_FEATURE,
            commonOrePlacement(
                    Config.quartzVeinsPerChunk.get(),
                    HeightRangePlacement.uniform(
                            VerticalAnchor.absolute(Config.quartzMinHeight.get()),
                            VerticalAnchor.absolute(Config.quartzMaxHeight.get())
                    )
            )
    );

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        if (!Config.generate_ore.get()) return;

        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_QUARTZ_ORE_PLACED_FEATURE);
        }
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier)
    {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }
}
