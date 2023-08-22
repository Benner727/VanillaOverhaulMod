package net.steve.vanillaoverhaulmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;

public class ModProtectionEnchantment extends ProtectionEnchantment {
    public ModProtectionEnchantment(Rarity pRarity, Type pType, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pType, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
