package net.steve.vanillaoverhaulmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class VanillaOverhaulModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACE_VILLAGER_TRADES;

    public static final ForgeConfigSpec.ConfigValue<Integer> STONE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> DEEPSLATE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> COAL_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> COPPER_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> IRON_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> GOLD_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> LAPIS_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> EMERALD_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> REDSTONE_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> DIAMOND_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> NETHER_GOLD_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> QUARTZ_ORE_EXP;
    public static final ForgeConfigSpec.ConfigValue<Integer> ANCIENT_DEBRIS_EXP;

    static {
        BUILDER.push("Configs for Vanilla Overhaul Mod");

        // HERE DEFINE YOUR CONFIGS
        REPLACE_VILLAGER_TRADES = BUILDER.comment("If True, Replace existing trades. If False, Add on to existing trades.").define("Replace Villager Trades", Boolean.TRUE);

        STONE_EXP = BUILDER.define("Stone Exp", 0);
        DEEPSLATE_EXP = BUILDER.define("Deepslate Exp", 1);
        COAL_ORE_EXP = BUILDER.define("Coal Ore Exp", 2);
        COPPER_ORE_EXP = BUILDER.define("Copper Ore Exp", 2);
        IRON_ORE_EXP = BUILDER.define("Iron Ore Exp", 3);
        GOLD_ORE_EXP = BUILDER.define("Gold Ore Exp", 3);
        LAPIS_ORE_EXP = BUILDER.define("Lapis Ore Exp", 5);
        EMERALD_ORE_EXP = BUILDER.define("Emerald Ore Exp", 8);
        REDSTONE_ORE_EXP = BUILDER.define("Redstone Ore Exp", 6);
        DIAMOND_ORE_EXP = BUILDER.define("Diamond Ore Exp", 15);
        NETHER_GOLD_EXP = BUILDER.define("Nether Gold Exp", 2);
        QUARTZ_ORE_EXP = BUILDER.define("Quartz Ore Exp", 2);
        ANCIENT_DEBRIS_EXP = BUILDER.define("Ancient Debris Exp", 30);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}