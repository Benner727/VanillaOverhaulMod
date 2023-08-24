package net.steve.vanillaoverhaulmod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> VANILLA_ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    // Iron tools
    public static final RegistryObject<Item> IRON_SWORD = VANILLA_ITEMS.register("iron_sword", () -> new SwordItem(ModTiers.MOD_IRON, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SHOVEL = VANILLA_ITEMS.register("iron_shovel", () -> new ShovelItem(ModTiers.MOD_IRON, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_PICKAXE = VANILLA_ITEMS.register("iron_pickaxe", () -> new PickaxeItem(ModTiers.MOD_IRON, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_AXE = VANILLA_ITEMS.register("iron_axe", () -> new AxeItem(ModTiers.MOD_IRON, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HOE = VANILLA_ITEMS.register("iron_hoe", () -> new HoeItem(ModTiers.MOD_IRON, -2, -1.0F, new Item.Properties()));

    // Diamond tools
    public static final RegistryObject<Item> DIAMOND_SWORD = VANILLA_ITEMS.register("diamond_sword", () -> new SwordItem(ModTiers.MOD_DIAMOND, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SHOVEL = VANILLA_ITEMS.register("diamond_shovel", () -> new ShovelItem(ModTiers.MOD_DIAMOND, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_PICKAXE = VANILLA_ITEMS.register("diamond_pickaxe", () -> new PickaxeItem(ModTiers.MOD_DIAMOND, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_AXE = VANILLA_ITEMS.register("diamond_axe", () -> new AxeItem(ModTiers.MOD_DIAMOND, 5.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HOE = VANILLA_ITEMS.register("diamond_hoe", () -> new HoeItem(ModTiers.MOD_DIAMOND, -3, 0.0F, new Item.Properties()));

    // Gold tools
    public static final RegistryObject<Item> GOLDEN_SWORD = VANILLA_ITEMS.register("golden_sword", () -> new SwordItem(ModTiers.MOD_GOLD, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SHOVEL = VANILLA_ITEMS.register("golden_shovel", () -> new ShovelItem(ModTiers.MOD_GOLD, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_PICKAXE = VANILLA_ITEMS.register("golden_pickaxe", () -> new PickaxeItem(ModTiers.MOD_GOLD, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_AXE = VANILLA_ITEMS.register("golden_axe", () -> new AxeItem(ModTiers.MOD_GOLD, 6.0F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_HOE = VANILLA_ITEMS.register("golden_hoe", () -> new HoeItem(ModTiers.MOD_GOLD, 0, -3.0F, new Item.Properties()));

    // Netherite tools
    public static final RegistryObject<Item> NETHERITE_SWORD = VANILLA_ITEMS.register("netherite_sword", () -> new SwordItem(ModTiers.MOD_NETHERITE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_SHOVEL = VANILLA_ITEMS.register("netherite_shovel", () -> new ShovelItem(ModTiers.MOD_NETHERITE, 1.5F, -3.0F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_PICKAXE = VANILLA_ITEMS.register("netherite_pickaxe", () -> new PickaxeItem(ModTiers.MOD_NETHERITE, 1, -2.8F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_AXE = VANILLA_ITEMS.register("netherite_axe", () -> new AxeItem(ModTiers.MOD_NETHERITE, 5.0F, -3.0F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_HOE = VANILLA_ITEMS.register("netherite_hoe", () -> new HoeItem(ModTiers.MOD_NETHERITE, -4, 0.0F, (new Item.Properties()).fireResistant()));

    // Iron armor
    public static final RegistryObject<Item> IRON_HELMET = VANILLA_ITEMS.register("iron_helmet", () -> new ArmorItem(ModArmorMaterials.MOD_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> IRON_CHESTPLATE = VANILLA_ITEMS.register("iron_chestplate", () -> new ArmorItem(ModArmorMaterials.MOD_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> IRON_LEGGINGS = VANILLA_ITEMS.register("iron_leggings", () -> new ArmorItem(ModArmorMaterials.MOD_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> IRON_BOOTS = VANILLA_ITEMS.register("iron_boots", () -> new ArmorItem(ModArmorMaterials.MOD_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Diamond Armor
    public static final RegistryObject<Item> DIAMOND_HELMET = VANILLA_ITEMS.register("diamond_helmet", () -> new ArmorItem(ModArmorMaterials.MOD_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_CHESTPLATE = VANILLA_ITEMS.register("diamond_chestplate", () -> new ArmorItem(ModArmorMaterials.MOD_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_LEGGINGS = VANILLA_ITEMS.register("diamond_leggings", () -> new ArmorItem(ModArmorMaterials.MOD_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_BOOTS = VANILLA_ITEMS.register("diamond_boots", () -> new ArmorItem(ModArmorMaterials.MOD_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Gold Armor
    public static final RegistryObject<Item> GOLDEN_HELMET = VANILLA_ITEMS.register("golden_helmet", () -> new ArmorItem(ModArmorMaterials.MOD_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_CHESTPLATE = VANILLA_ITEMS.register("golden_chestplate", () -> new ArmorItem(ModArmorMaterials.MOD_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_LEGGINGS = VANILLA_ITEMS.register("golden_leggings", () -> new ArmorItem(ModArmorMaterials.MOD_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_BOOTS = VANILLA_ITEMS.register("golden_boots", () -> new ArmorItem(ModArmorMaterials.MOD_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Netherite Armor
    public static final RegistryObject<Item> NETHERITE_HELMET = VANILLA_ITEMS.register("netherite_helmet", () -> new ArmorItem(ModArmorMaterials.MOD_NETHERITE, ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_CHESTPLATE = VANILLA_ITEMS.register("netherite_chestplate", () -> new ArmorItem(ModArmorMaterials.MOD_NETHERITE, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_LEGGINGS = VANILLA_ITEMS.register("netherite_leggings", () -> new ArmorItem(ModArmorMaterials.MOD_NETHERITE, ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> NETHERITE_BOOTS = VANILLA_ITEMS.register("netherite_boots", () -> new ArmorItem(ModArmorMaterials.MOD_NETHERITE, ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant()));

    // Other
    public static final RegistryObject<Item> ELYTRA = VANILLA_ITEMS.register("elytra", () -> new ElytraItem((new Item.Properties()).durability(1000).rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus eventBus) {
        VANILLA_ITEMS.register(eventBus);
    }
}
