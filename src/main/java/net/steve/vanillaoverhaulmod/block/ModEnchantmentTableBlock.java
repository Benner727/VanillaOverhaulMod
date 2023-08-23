package net.steve.vanillaoverhaulmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.steve.vanillaoverhaulmod.screen.ModEnchantmentMenu;

import javax.annotation.Nullable;

public class ModEnchantmentTableBlock extends EnchantmentTableBlock {
    public ModEnchantmentTableBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof EnchantmentTableBlockEntity) {
            Component component = ((Nameable)blockentity).getDisplayName();
            return new SimpleMenuProvider((p_207906_, p_207907_, p_207908_) -> {
                return new ModEnchantmentMenu(p_207906_, p_207907_, ContainerLevelAccess.create(pLevel, pPos));
            }, component);
        } else {
            return null;
        }
    }
}
