package com.unixkitty.overworld_quartz.datagen;

import com.unixkitty.overworld_quartz.OverworldQuartz;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ModBlockLootTables extends BlockLoot
{
    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void addTables()
    {
        for (Block block : ForgeRegistries.BLOCKS)
        {
            if (OverworldQuartz.MODID.equals(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace()))
            {
                this.add(block, block1 -> createOreDrop(block1, Items.QUARTZ));
            }
        }
    }

    @Override
    protected void add(@Nonnull Block block, @Nonnull LootTable.Builder builder)
    {
        super.add(block, builder);

        knownBlocks.add(block);
    }

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return this.knownBlocks;
    }
}
