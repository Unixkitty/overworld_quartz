package com.unixkitty.overworld_quartz;

import com.unixkitty.overworld_quartz.init.ModRegistry;
import com.unixkitty.overworld_quartz.worldgen.OreGeneration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OverworldQuartz.MODID)
public class OverworldQuartz
{
    // The MODID value here should match an entry in the META-INF/mods.toml file
    public static final String MODID = "overworld_quartz";
    public static final String MODNAME = "Overworld Quartz";

    private static final Logger LOG = LogManager.getLogger(MODNAME);

    public OverworldQuartz()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(ModRegistry.class);

        ModRegistry.BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::onBiomeLoading);
    }

    public static Logger log()
    {
        return LOG;
    }
}
