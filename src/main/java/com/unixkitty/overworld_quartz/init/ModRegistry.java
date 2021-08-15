package com.unixkitty.overworld_quartz.init;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import com.unixkitty.overworld_quartz.worldgen.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = OverworldQuartz.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OverworldQuartz.MODID);

    public static final RegistryObject<Block> OVERWORLD_QUARTZ_ORE = BLOCKS.register("overworld_quartz_ore", () ->
            new OreBlock(Block.Properties.copy(Blocks.COAL_ORE))
            {
                @Override
                protected int xpOnDrop(Random rand)
                {
                    return MathHelper.nextInt(rand, 2, 5);
                }
            }
    );

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        // BlockItems for all blocks
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
                event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)).setRegistryName(Objects.requireNonNull(block.getRegistryName())))
        );
    }

    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event)
    {
        OreGeneration.register();
    }
}
