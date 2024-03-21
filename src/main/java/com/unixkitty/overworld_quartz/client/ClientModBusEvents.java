package com.unixkitty.overworld_quartz.client;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.init.ModRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = OverworldQuartz.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBusEvents
{
    @SubscribeEvent
    public static void onBuildCreativeTabs(final BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
        {
            ModRegistry.ITEMS.getEntries().forEach(itemRegistryObject -> event.accept(itemRegistryObject.get()));
        }
    }
}
