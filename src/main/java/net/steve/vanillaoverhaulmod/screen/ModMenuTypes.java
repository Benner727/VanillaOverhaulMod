package net.steve.vanillaoverhaulmod.screen;

import net.minecraft.world.inventory.AnvilMenu;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, VanillaOverhaulMod.MOD_ID);

    public static final RegistryObject<MenuType<ModEnchantmentMenu>> MOD_ENCHANTMENT_MENU =
            registerMenuType(ModEnchantmentMenu::new, "mod_enchantment_menu");

    public static final RegistryObject<MenuType<ModAnvilMenu>> MOD_ANVIL_MENU =
            registerMenuType(ModAnvilMenu::new, "mod_anvil_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
