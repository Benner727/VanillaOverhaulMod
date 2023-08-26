package net.steve.vanillaoverhaulmod.config;

import net.minecraftforge.common.ForgeConfigSpec;
public class VanillaOverhaulModClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs for Vanilla Overhaul Mod");

        // HERE DEFINE YOUR CONFIGS

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}