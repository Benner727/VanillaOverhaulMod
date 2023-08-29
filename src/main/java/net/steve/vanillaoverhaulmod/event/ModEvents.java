package net.steve.vanillaoverhaulmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;
import net.steve.vanillaoverhaulmod.config.VanillaOverhaulModCommonConfigs;
import net.steve.vanillaoverhaulmod.util.ModVillagerTrades;
import net.steve.vanillaoverhaulmod.util.VillageTradesResource;
import net.steve.vanillaoverhaulmod.util.VillagerTradesManager;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID)
public class ModEvents {
    @Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void addCustomVillagerTrades(VillagerTradesEvent event) {
            if (event.getType() == VillagerProfession.LIBRARIAN) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

                for (int i = 1; i <= 5; i++) {
                    replaceVillagerTrades("librarian", i, trades);
                }
            }
        }

        private static void replaceVillagerTrades(String profession, int villagerLevel, Int2ObjectMap<List<VillagerTrades.ItemListing>> trades) {
            ArrayList<VillageTradesResource.Trade> new_trades = VillagerTradesManager.getTrades("librarian", villagerLevel);

            if (new_trades.size() > 0 && VanillaOverhaulModCommonConfigs.REPLACE_VILLAGER_TRADES.get()) {
                trades.get(villagerLevel).clear();
            }

            for (VillageTradesResource.Trade trade : new_trades) {
                if (trade.type.equals("ItemsForItems")) {
                    trades.get(villagerLevel).add(new ModVillagerTrades.ItemsForItems(
                            trade.values.give, trade.values.giveAmount, trade.values.take, trade.values.takeAmount));
                }
                else if (trade.type.equals("EnchantBookForLapis")) {
                    trades.get(villagerLevel).add(new ModVillagerTrades.EnchantBookForLapis(trade.values.name, trade.values.level));
                }
                else if (trade.type.equals("RandomEnchantBookForLapis")) {
                    System.out.println("RandomEnchantBookForLapis");
                    trades.get(villagerLevel).add(new ModVillagerTrades.RandomEnchantBookForLapis());
                }
            }
        }

        @SubscribeEvent
        public static void addCustomWanderingVillagerTrades(WandererTradesEvent event) {
            List<VillagerTrades.ItemListing> generic = event.getGenericTrades();
            List<VillagerTrades.ItemListing> rare = event.getRareTrades();

            generic.clear();

            ItemStack stack = new ItemStack(Items.ACACIA_DOOR, 1);
            generic.add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack, 10, 8, 0.02F));
        }

        @SubscribeEvent
        public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
            Entity entity = event.getEntity();
            if (entity instanceof ExperienceOrb) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onPlayerDeath(PlayerEvent.Clone event) {
            int exp = event.getOriginal().totalExperience;
            event.getEntity().giveExperiencePoints(exp);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        String block = event.getState().getBlock().getDescriptionId();

        switch (block) {
            case "block.minecraft.stone" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.STONE_EXP.get());
            case "block.minecraft.deepslate" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.DEEPSLATE_EXP.get());
            case "block.minecraft.coal_ore", "block.minecraft.deepslate_coal_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.COAL_ORE_EXP.get());
            case "block.minecraft.copper_ore", "block.minecraft.deepslate_copper_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.COPPER_ORE_EXP.get());
            case "block.minecraft.iron_ore", "block.minecraft.deepslate_iron_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.IRON_ORE_EXP.get());
            case "block.minecraft.gold_ore", "block.minecraft.deepslate_gold_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.GOLD_ORE_EXP.get());
            case "block.minecraft.lapis_ore", "block.minecraft.deepslate_lapis_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.LAPIS_ORE_EXP.get());
            case "block.minecraft.emerald_ore", "block.minecraft.deepslate_emerald_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.EMERALD_ORE_EXP.get());
            case "block.minecraft.redstone_ore", "block.minecraft.deepslate_redstone_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.REDSTONE_ORE_EXP.get());
            case "block.minecraft.diamond_ore", "block.minecraft.deepslate_diamond_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.DIAMOND_ORE_EXP.get());
            case "block.minecraft.nether_gold_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.NETHER_GOLD_EXP.get());
            case "block.minecraft.nether_quartz_ore" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.QUARTZ_ORE_EXP.get());
            case "block.minecraft.ancient_debris" ->
                    player.giveExperiencePoints(VanillaOverhaulModCommonConfigs.ANCIENT_DEBRIS_EXP.get());
        }
    }

    @SubscribeEvent
    public static void onExpDropEvent(LivingExperienceDropEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void dataLoading(AddReloadListenerEvent event) {
        event.addListener(VillagerTradesManager.instance);
    }
}