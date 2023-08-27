package net.steve.vanillaoverhaulmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;
import net.steve.vanillaoverhaulmod.entity.ModEntities;
import net.steve.vanillaoverhaulmod.client.renderer.TestEntityRenderer;
import net.steve.vanillaoverhaulmod.client.model.TestEntityModel;

@Mod.EventBusSubscriber(modid = VanillaOverhaulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.EXAMPLE_ENTITY.get(), TestEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TestEntityModel.LAYER_LOCATION, TestEntityModel::createBodyLayer);
    }
}