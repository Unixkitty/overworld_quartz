package com.unixkitty.overworld_quartz;

import net.minecraftforge.common.ForgeConfigSpec;

@SuppressWarnings("CanBeFinal")
public class Config
{

    public static ForgeConfigSpec COMMON_CONFIG;
    //public static ForgeConfigSpec CLIENT_CONFIG; This will be needed for client-specific options
    private static final int WORLD_HEIGHT = 256;
    /* BEGIN ENTRIES */

    public static ForgeConfigSpec.IntValue quartzVeinSize;
    public static ForgeConfigSpec.IntValue quartzVeinsPerChunk;
    public static ForgeConfigSpec.IntValue quartzMinHeight;
    public static ForgeConfigSpec.IntValue quartzMaxHeight;
    public static ForgeConfigSpec.BooleanValue generate_ore;

    /* END ENTRIES */

    static
    {
        ForgeConfigSpec.Builder commonConfig = new ForgeConfigSpec.Builder();

        commonConfig.push("settings");
        generate_ore = commonConfig.define("generate_ore", true);
        quartzVeinSize = commonConfig.defineInRange("quartzVeinSize", 14, 1, 64);
        quartzVeinsPerChunk = commonConfig.defineInRange("quartzVeinsPerChunk", 40, 1, 100);
        quartzMinHeight = commonConfig.defineInRange("quartzMinHeight", 0, 0, WORLD_HEIGHT - 2);
        quartzMaxHeight = commonConfig.defineInRange("quartzMaxHeight", WORLD_HEIGHT - 1, 1, WORLD_HEIGHT - 1);
        commonConfig.pop();

        COMMON_CONFIG = commonConfig.build();
    }
}
