package net.steve.vanillaoverhaulmod.event;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;

import java.util.List;
import java.util.stream.Collectors;


@Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.ACACIA_DOOR, 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).clear();
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack,10,8,0.02F));

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
}