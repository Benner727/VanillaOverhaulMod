package net.steve.vanillaoverhaulmod.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.*;
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
    public static RegistryObject<Enchantment> SHARPNESS = VANILLA_ENCHANTMENTS.register("sharpness", () -> new ModDamageEnchantment(Enchantment.Rarity.COMMON, 0, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment>  SMITE = VANILLA_ENCHANTMENTS.register("smite", () -> new ModDamageEnchantment(Enchantment.Rarity.UNCOMMON, 1, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> BANE_OF_ARTHROPODS = VANILLA_ENCHANTMENTS.register("bane_of_arthropods", () -> new ModDamageEnchantment(Enchantment.Rarity.UNCOMMON, 2, EquipmentSlot.MAINHAND));
    public static RegistryObject<Enchantment> BLOCK_EFFICIENCY = VANILLA_ENCHANTMENTS.register("efficiency", () -> new ModDiggingEnchantment(Enchantment.Rarity.COMMON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        VANILLA_ENCHANTMENTS.register((eventBus));
    }
}
