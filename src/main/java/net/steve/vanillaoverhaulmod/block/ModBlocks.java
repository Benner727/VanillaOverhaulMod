package net.steve.vanillaoverhaulmod.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> VANILLA_BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "minecraft");

    public static final RegistryObject<Block> ENCHANTING_TABLE = registerVanillaBlock("enchanting_table",
            () ->new ModEnchantmentTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .lightLevel((p_187437_) -> {return 7; })
                    .strength(5.0F, 1200.0F)));

    private static <T extends Block> RegistryObject<T> registerVanillaBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = VANILLA_BLOCKS.register(name, block);
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        VANILLA_BLOCKS.register((eventBus));
    }
}
