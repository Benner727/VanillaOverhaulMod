package net.steve.vanillaoverhaulmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.steve.vanillaoverhaulmod.VanillaOverhaulMod;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VanillaOverhaulMod.MOD_ID);

    public static final RegistryObject<EntityType<TestEntity>> EXAMPLE_ENTITY = ENTITY_TYPES.register("test_entity",
            () -> EntityType.Builder.<TestEntity>of(TestEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .build(new ResourceLocation(VanillaOverhaulMod.MOD_ID, "example_entity").toString())
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}