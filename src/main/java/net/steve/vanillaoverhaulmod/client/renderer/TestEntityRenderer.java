package net.steve.vanillaoverhaulmod.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;
import net.steve.vanillaoverhaulmod.client.model.TestEntityModel;
import net.steve.vanillaoverhaulmod.entity.TestEntity;

public class TestEntityRenderer extends HumanoidMobRenderer<TestEntity, TestEntityModel<TestEntity>> {
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(VanillaOverhaulMod.MOD_ID, "textures/entity/test_entity.png");

    public TestEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TestEntityModel<>(pContext.bakeLayer(TestEntityModel.LAYER_LOCATION)), 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity pEntity) {
        return RESOURCE_LOCATION;
    }
}
