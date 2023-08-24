package net.steve.vanillaoverhaulmod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModArmorMaterials {
    public static final ModArmorMaterial MOD_IRON = new ModArmorMaterial(
            new int[] { 165, 240, 225, 195 },
            new int[] { 2, 6, 5, 2 },
            9,
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Items.IRON_INGOT),
            "iron",
            0.0f,
            0.0f
    );

    public static final ModArmorMaterial MOD_GOLD = new ModArmorMaterial(
            new int[] { 77, 112, 105, 91 },
            new int[] { 2, 5, 3, 1 },
            25,
            SoundEvents.ARMOR_EQUIP_GOLD,
            () -> Ingredient.of(Items.GOLD_INGOT),
            "gold",
            0.0f,
            0.0f
    );

    public static final ModArmorMaterial MOD_DIAMOND = new ModArmorMaterial(
            new int[] { 363, 528, 495, 429 },
            new int[] { 3, 8, 6, 3 },
            10,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(Items.DIAMOND),
            "diamond",
            2.0f,
            0.0f
    );

    public static final ModArmorMaterial MOD_NETHERITE = new ModArmorMaterial(
            new int[] { 407, 592, 555, 481 },
            new int[] { 3, 8, 6, 3 },
            15,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.of(Items.NETHERITE_INGOT),
            "netherite",
            3.0f,
            0.1f
    );
}
