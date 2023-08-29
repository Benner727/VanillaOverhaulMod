package net.steve.vanillaoverhaulmod.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class ModVillagerTrades {
    public interface ItemListing {
        @Nullable
        MerchantOffer getOffer(Entity pTrader, RandomSource pRandom);
    }

    public static class ItemsForItems implements VillagerTrades.ItemListing {
        private final ItemStack giveItemStack;
        private final int giveAmount;
        private final ItemStack takeItemStack;
        private final int takeAmount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForItems(String pGiveItem, int pGiveAmount, String pTakeItem, int pTakeAmount) {
            this(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", pGiveItem)), pGiveAmount, ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", pTakeItem)), pTakeAmount);
        }

        public ItemsForItems(Item pGiveItem, int pGiveAmount, Item pTakeItem, int pTakeAmount) {
            this(new ItemStack(pGiveItem), pGiveAmount, new ItemStack(pTakeItem), pTakeAmount, 12);
        }

        public ItemsForItems(ItemStack pGiveItemStack, int pGiveAmount, ItemStack pTakeItemStack, int pTakeAmount, int pMaxUses) {
            this.giveItemStack = pGiveItemStack;
            this.giveAmount = pGiveAmount;
            this.takeItemStack = pTakeItemStack;
            this.takeAmount = pTakeAmount;
            this.maxUses = pMaxUses;
            this.villagerXp = 5;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(this.giveItemStack.getItem(), this.giveAmount), new ItemStack(this.takeItemStack.getItem(), this.takeAmount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public static class RandomEnchantBookForLapis implements VillagerTrades.ItemListing {
        private final int villagerXp;

        public RandomEnchantBookForLapis() {
            this.villagerXp = 10;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            List<Enchantment> list = BuiltInRegistries.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
            Enchantment enchantment = list.get(pRandom.nextInt(list.size()));
            int i = Mth.nextInt(pRandom, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
            int j = 2 + pRandom.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.isTreasureOnly()) {
                j *= 2;
            }

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(Items.LAPIS_LAZULI, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
        }
    }

    public static class EnchantBookForLapis implements VillagerTrades.ItemListing {
        private final String enchantName;
        private final int level;
        private final int villagerXp;

        public EnchantBookForLapis(String pEnchantName, int pLevel) {
            this.enchantName = pEnchantName;
            this.level = pLevel;
            this.villagerXp = 30;
        }



        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            List<Enchantment> list = BuiltInRegistries.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
            Enchantment enchantment = list.stream()
                    .filter(e -> e.getDescriptionId().contains(this.enchantName))
                    .findAny()
                    .orElse(null);
            ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, this.level));
            int j = 2 + pRandom.nextInt(5 + this.level * 10) + 3 * this.level;

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(Items.LAPIS_LAZULI, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
        }
    }

   public static class EnchantedItemForItems implements VillagerTrades.ItemListing {
        private final ItemStack giveItemStack;
        private final int giveAmount;
        private final ItemStack takeItemStack;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForItems(String pGiveItem, int pGiveAmount, String pTakeItem) {
            this(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", pGiveItem)), pGiveAmount, ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", pTakeItem)));
        }

        public EnchantedItemForItems(Item pGiveItem, int pGiveAmount, Item pTakeItem) {
            this(new ItemStack(pGiveItem), pGiveAmount, new ItemStack(pTakeItem), 1);
        }

        public EnchantedItemForItems(ItemStack pGiveItemStack, int pGiveAmount, ItemStack pTakeItemStack, int pMaxUses) {
            this.giveItemStack = pGiveItemStack;
            this.giveAmount = pGiveAmount;
            this.takeItemStack = pTakeItemStack;
            this.maxUses = pMaxUses;
            this.villagerXp = 15;;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            int i = 5 + pRandom.nextInt(15);
            ItemStack itemstack = EnchantmentHelper.enchantItem(pRandom, new ItemStack(this.takeItemStack.getItem()), i, false);
            int j = Math.min(this.giveAmount + i, 64);
            return new MerchantOffer(new ItemStack(this.giveItemStack.getItem(), j), itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
