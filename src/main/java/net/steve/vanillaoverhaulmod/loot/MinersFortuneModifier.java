package net.steve.vanillaoverhaulmod.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class MinersFortuneModifier extends LootModifier {
    public static final Supplier<Codec<MinersFortuneModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, MinersFortuneModifier::new)));

    protected MinersFortuneModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack ctxTool = context.getParamOrNull(LootContextParams.TOOL);
        if (ctxTool != null && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, ctxTool) < 1) {
            if (isFortunable(context.getParam(LootContextParams.BLOCK_STATE))) {
                Entity player = context.getParamOrNull(LootContextParams.THIS_ENTITY);

                if (player != null) {
                    int playerLevel = ((Player) player).experienceLevel;
                    int lootModifier = getLootModifier(playerLevel, context.getRandom().nextFloat());

                    for (int i = 1; i < lootModifier; i++) {
                        ObjectArrayList<ItemStack> bonusLoot = new ObjectArrayList<ItemStack>();

                        for (ItemStack itemStack : generatedLoot) {
                            bonusLoot.add(itemStack);
                        }

                        generatedLoot.addAll(bonusLoot);
                    }
                }
            }
        }

        return generatedLoot;
    }

    private boolean isFortunable(BlockState block) {
        String blockName = block.getBlock().asItem().getDescriptionId();

        System.out.println(blockName);

        return true;
    }

    private int getLootModifier(int playerLevel, float lootRoll) {
        int lootModifier = 1;

        int fortuneLevel = Math.floorDiv(Math.max(playerLevel, 100), 20);
        switch (fortuneLevel) {
            case 1 -> lootModifier = getFortuneOneModifier(lootRoll);
            case 2 -> lootModifier = getFortuneTwoModifier(lootRoll);
            case 3 -> lootModifier = getFortuneThreeModifier(lootRoll);
            case 4 -> lootModifier = getFortuneFourModifier(lootRoll);
            case 5 -> lootModifier = getFortuneFiveModifier(lootRoll);
        }

        return lootModifier;
    }

    private int getFortuneOneModifier(float lootRoll) {
        int lootModifier = 1;

        if (lootRoll <= 0.33f)
            lootModifier = 2;

        return lootModifier;
    }

    private int getFortuneTwoModifier(float lootRoll) {
        int lootModifier = 1;

        if (lootRoll <= 0.25f)
            lootModifier = 2;
        else if (lootRoll <= 0.50f)
            lootModifier = 3;

        return lootModifier;
    }

    private int getFortuneThreeModifier(float lootRoll) {
        int lootModifier = 1;

        if (lootRoll <= 0.20f)
            lootModifier = 2;
        else if (lootRoll <= 0.40f)
            lootModifier = 3;
        else if (lootRoll <= 0.60f)
            lootModifier = 4;

        return lootModifier;
    }

    private int getFortuneFourModifier(float lootRoll) {
        int lootModifier = 1;

        if (lootRoll <= 0.16f)
            lootModifier = 2;
        else if (lootRoll <= 0.33f)
            lootModifier = 3;
        else if (lootRoll <= 0.40f)
            lootModifier = 4;
        else if (lootRoll <= 0.66f)
            lootModifier = 5;

        return lootModifier;
    }

    private int getFortuneFiveModifier(float lootRoll) {
        int lootModifier = 1;

        if (lootRoll <= 0.15f)
            lootModifier = 2;
        else if (lootRoll <= 0.30f)
            lootModifier = 3;
        else if (lootRoll <= 0.45f)
            lootModifier = 4;
        else if (lootRoll <= 0.60f)
            lootModifier = 5;
        else if (lootRoll <= 0.75f)
            lootModifier = 6;

        return lootModifier;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}