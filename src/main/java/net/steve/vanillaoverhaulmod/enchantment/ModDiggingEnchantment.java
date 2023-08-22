package net.steve.vanillaoverhaulmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DiggingEnchantment;

public class ModDiggingEnchantment extends DiggingEnchantment {
    protected ModDiggingEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
