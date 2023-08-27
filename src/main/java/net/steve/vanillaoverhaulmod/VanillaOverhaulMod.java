package net.steve.vanillaoverhaulmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.steve.vanillaoverhaulmod.block.ModBlocks;
import net.steve.vanillaoverhaulmod.config.VanillaOverhaulModClientConfigs;
import net.steve.vanillaoverhaulmod.config.VanillaOverhaulModCommonConfigs;
import net.steve.vanillaoverhaulmod.enchantment.ModEnchantments;
import net.steve.vanillaoverhaulmod.entity.ModEntities;
import net.steve.vanillaoverhaulmod.item.ModItems;
import net.steve.vanillaoverhaulmod.loot.ModLootModifiers;
import net.steve.vanillaoverhaulmod.screen.ModMenuTypes;
import net.steve.vanillaoverhaulmod.screen.ModEnchantmentScreen;
import net.steve.vanillaoverhaulmod.screen.ModAnvilScreen;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VanillaOverhaulMod.MOD_ID)
public class VanillaOverhaulMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "vanillaoverhaulmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public VanillaOverhaulMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModEnchantments.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModEntities.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, VanillaOverhaulModClientConfigs.SPEC, "vanillaoverhaulmod-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, VanillaOverhaulModCommonConfigs.SPEC, "vanillaoverhaulmod-common.toml");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.MOD_ENCHANTMENT_MENU.get(), ModEnchantmentScreen::new);
            MenuScreens.register(ModMenuTypes.MOD_ANVIL_MENU.get(), ModAnvilScreen::new);
        }
    }
}
