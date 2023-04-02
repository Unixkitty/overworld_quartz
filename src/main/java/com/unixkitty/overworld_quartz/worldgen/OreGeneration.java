package com.unixkitty.overworld_quartz.worldgen;


import com.google.common.base.Suppliers;
import com.unixkitty.overworld_quartz.Config;
import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class OreGeneration
{
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, OverworldQuartz.MODID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_QUARTZ_ORE_PLACEMENT = Suppliers.memoize(() ->
            List.of(
                    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModRegistry.OVERWORLD_QUARTZ_ORE.get().defaultBlockState())
            )
    );

    public static final RegistryObject<ConfiguredFeature<?, ?>> OVERWORLD_QUARTZ_ORE_FEATURE = configureFeature("ore_overworld_quartz", OVERWORLD_QUARTZ_ORE_PLACEMENT, Config.quartzVeinSize);

    private static RegistryObject<ConfiguredFeature<?, ?>> configureFeature(String id, Supplier<List<OreConfiguration.TargetBlockState>> placementSupplier, int veinSize)
    {
        return CONFIGURED_FEATURES.register(id, () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(placementSupplier.get(), veinSize)));
    }

    //===========================================
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, OverworldQuartz.MODID);

    public static RegistryObject<PlacedFeature> OVERWORLD_QUARTZ_ORE_PLACED_FEATURE = basicOrePlacedFeature("ore_overworld_quartz", OVERWORLD_QUARTZ_ORE_FEATURE, Config.quartzVeinsPerChunk, Config.quartzMinHeight, Config.quartzMaxHeight);

    private static RegistryObject<PlacedFeature> basicOrePlacedFeature(String id, RegistryObject<ConfiguredFeature<?, ?>> configuredFeature, int veinsPerChunk, int minHeight, int maxHeight)
    {
        return PLACED_FEATURES.register(
                id,
                () -> new PlacedFeature(
                        configuredFeature.getHolder().get(), commonOrePlacement(
                        veinsPerChunk,
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(minHeight),
                                VerticalAnchor.absolute(maxHeight)
                        )
                )
                )
        );
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier)
    {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }
}