package com.unixkitty.overworld_quartz;

import net.minecraftforge.common.ForgeConfigSpec;

@SuppressWarnings("CanBeFinal")
public class Config
{

    public static ForgeConfigSpec COMMON_CONFIG;
    //public static ForgeConfigSpec CLIENT_CONFIG; This will be needed for client-specific options

    //Values over this cause too much lag? Needs testing
    private static final int OREGEN_THRESHOLD = 30;
    private static final int WORLD_HEIGHT = 256;
    /* BEGIN ENTRIES */

    public static ForgeConfigSpec.IntValue quartzVeinSize;
    public static ForgeConfigSpec.IntValue quartzVeinsPerChunk;
    public static ForgeConfigSpec.IntValue quartzMinHeight;
    public static ForgeConfigSpec.IntValue quartzMaxHeight;

    /* END ENTRIES */

    static
    {
        ForgeConfigSpec.Builder commonConfig = new ForgeConfigSpec.Builder();

        commonConfig.push("settings");
        quartzVeinSize = commonConfig.defineInRange("quartzVeinSize", 14, 1, OREGEN_THRESHOLD);
        quartzVeinsPerChunk = commonConfig.defineInRange("quartzVeinsPerChunk", 10, 1, OREGEN_THRESHOLD);
        quartzMinHeight = commonConfig.defineInRange("quartzMinHeight", 16, 1, WORLD_HEIGHT - 2);
        quartzMaxHeight = commonConfig.defineInRange("quartzMaxHeight", 80, 1, WORLD_HEIGHT - 1);
        commonConfig.pop();

        COMMON_CONFIG = commonConfig.build();
    }
}
