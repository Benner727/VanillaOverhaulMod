package net.steve.vanillaoverhaulmod.util;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VillagerTradesManager extends SimpleJsonResourceReloadListener {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final ArrayList<VillageTradesResource.VillagerTrade> villager_trades = new ArrayList<VillageTradesResource.VillagerTrade>();
    public static final VillagerTradesManager instance = new VillagerTradesManager();

    public VillagerTradesManager() {
        super(GSON_INSTANCE, "villager_trades");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        List<ResourceLocation> finalLocations = new ArrayList<>();
        ResourceLocation resourcelocation = new ResourceLocation(VanillaOverhaulMod.MOD_ID, "villager_trades/villager_trades.json");

        for (Resource iresource : resourceManagerIn.getResourceStack(resourcelocation)) {
            try (InputStream inputstream = iresource.open();
                 Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
            ) {
                JsonObject villagerTradesJson = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                JsonArray villagerTradesArray = villagerTradesJson.get("villager_trades").getAsJsonArray();

                for (JsonElement entry : villagerTradesArray) {
                    JsonObject jo = (JsonObject) entry;
                    String profession = jo.get("profession").getAsString();
                    int level = jo.get("level").getAsInt();

                    VillageTradesResource.VillagerTrade tempVillagerTrade = new VillageTradesResource().new VillagerTrade(profession, level);
                    JsonArray trades = jo.get("trades").getAsJsonArray();

                    for (JsonElement trade : trades) {
                        JsonObject trade_jo = (JsonObject) trade;
                        JsonObject values = trade_jo.getAsJsonObject("values");

                        if (trade_jo.get("type").getAsString().equals("EnchantBookForLapis")) {
                            tempVillagerTrade.trades.add(new VillageTradesResource().new Trade(
                                    trade_jo.get("type").getAsString(),
                                    values.get("name").getAsString(),
                                    values.get("level").getAsInt()));
                        } else if (trade_jo.get("type").getAsString().equals("ItemsForItems")) {
                            tempVillagerTrade.trades.add(new VillageTradesResource().new Trade(
                                    trade_jo.get("type").getAsString(),
                                    values.get("give").getAsString(),
                                    values.get("give_amount").getAsInt(),
                                    values.get("take").getAsString(),
                                    values.get("take_amount").getAsInt()));
                        } else if (trade_jo.get("type").getAsString().equals("EnchantedItemForItems")) {
                            tempVillagerTrade.trades.add(new VillageTradesResource().new Trade(
                                    trade_jo.get("type").getAsString(),
                                    values.get("give").getAsString(),
                                    values.get("give_amount").getAsInt(),
                                    values.get("take").getAsString(),
                                   1));
                        } else if (trade_jo.get("type").getAsString().equals("RandomEnchantBookForLapis")) {
                            tempVillagerTrade.trades.add(new VillageTradesResource().new Trade(trade_jo.get("type").getAsString()));
                        }
                    }

                    villager_trades.add(tempVillagerTrade);
                }

            } catch (RuntimeException | IOException ioexception) {
                LOGGER.error("Couldn't read villager trades list {} in data pack {}", resourcelocation, iresource.sourcePackId(), ioexception);
            }
        }
    }

    public static ArrayList<VillageTradesResource.Trade> getTrades(String profession, int level) {
        ArrayList<VillageTradesResource.Trade> trades = new ArrayList<>();
        for (VillageTradesResource.VillagerTrade villager_trade : villager_trades) {
            if (villager_trade.profession.equals(profession) && villager_trade.level == level) {
                trades = villager_trade.trades;
                break;
            }
        }
        return trades;
    }
}