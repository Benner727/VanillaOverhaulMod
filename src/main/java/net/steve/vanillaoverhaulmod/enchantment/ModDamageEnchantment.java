package net.steve.vanillaoverhaulmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;

public class ModDamageEnchantment extends DamageEnchantment {
    public ModDamageEnchantment(Rarity pRarity, int pType, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pType, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
