package net.steve.vanillaoverhaulmod.event;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;

import java.util.List;
import java.util.stream.Collectors;


@Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID)
public class ModEvents {
    @Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void addCustomVillagerTrades(VillagerTradesEvent event) {
            if (event.getType() == VillagerProfession.LIBRARIAN) {
                Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
                ItemStack stack = new ItemStack(Items.ACACIA_DOOR, 1);
                int villagerLevel = 1;

                trades.get(villagerLevel).clear();
                trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        stack, 10, 8, 0.02F));

                List<Enchantment> list = BuiltInRegistries.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
                Enchantment enchantment = list.stream()
                        .filter(e -> "enchantment.minecraft.efficiency".equals(e.getDescriptionId()))
                        .findAny()
                        .orElse(null);
                if (enchantment != null) {
                    ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, 5));
                    trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                            new ItemStack(Items.LAPIS_LAZULI, 1),
                            itemstack, 10, 8, 0.02F));
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
        System.out.println(event.getState().getBlock().getName());

        Player player = event.getPlayer();

        if (event.getState().getBlock().equals(Block.byItem(Items.REDSTONE_ORE))) {
            player.giveExperiencePoints(100);
        }
    }

    @SubscribeEvent
    public static void onExpDropEvent(LivingExperienceDropEvent event) {
        event.setCanceled(true);
    }
}