package net.steve.vanillaoverhaulmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> VANILLA_ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    public static RegistryObject<Enchantment> MENDING = VANILLA_ENCHANTMENTS.register("mending", () -> new ModMendingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.values()));
    public static RegistryObject<Enchantment> ALL_DAMAGE_PROTECTION = VANILLA_ENCHANTMENTS.register("protection", () -> new ModProtectionEnchantment(Enchantment.Rarity.COMMON, ProtectionEnchantment.Type.ALL, ARMOR_SLOTS));
    public static RegistryObject<Enchantment> FIRE_DAMAGE_PROTECTION = VANILLA_ENCHANTMENTS.register("fire_protection", () -> new ModProtectionEnchantment(Enchantment.Rarity.UNCOMMON, ProtectionEnchantment.Type.FIRE, ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLAST_DAMAGE_PROTECTION = VANILLA_ENCHANTMENTS.register("blast_protection", () -> new ModProtectionEnchantment(Enchantment.Rarity.RARE, ProtectionEnchantment.Type.EXPLOSION, ARMOR_SLOTS));
    public static RegistryObject<Enchantment> PROJECTILE_PROTECTION = VANILLA_ENCHANTMENTS.register("projectile_protection", () -> new ModProtectionEnchantment(Enchantment.Rarity.UNCOMMON, ProtectionEnchantment.Type.PROJECTILE, ARMOR_SLOTS));

    public static void register(IEventBus eventBus) {
        VANILLA_ENCHANTMENTS.register((eventBus));
    }
}
