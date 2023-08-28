package net.steve.vanillaoverhaulmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class VanillaOverhaulModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACE_VILLAGER_TRADES;

    static {
        BUILDER.push("Configs for Vanilla Overhaul Mod");

        // HERE DEFINE YOUR CONFIGS
        REPLACE_VILLAGER_TRADES = BUILDER.comment("If True, Replace existing trades. If False, Add on to existing trades.").define("Replace Villager Trades", Boolean.TRUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}