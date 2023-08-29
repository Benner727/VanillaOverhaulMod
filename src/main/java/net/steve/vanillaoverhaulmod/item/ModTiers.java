package net.steve.vanillaoverhaulmod.item;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraft.tags.BlockTags;

public class ModTiers {

    public static final ForgeTier MOD_IRON = new ForgeTier(2, 512, 6.0F, 2.0F, 14,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(Items.IRON_INGOT));
    public static final ForgeTier MOD_DIAMOND = new ForgeTier(3, 1536, 8.0F, 3.0F, 10,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.DIAMOND));
    public static final ForgeTier MOD_GOLD = new ForgeTier(2, 132, 5.0F, 0.0F, 22,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.GOLD_INGOT));
    public static final ForgeTier MOD_NETHERITE = new ForgeTier(4, 2304, 9.0F, 4.0F, 15,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.NETHERITE_INGOT));
}
