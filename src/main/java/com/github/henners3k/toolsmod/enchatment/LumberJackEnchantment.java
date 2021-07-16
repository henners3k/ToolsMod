package com.github.henners3k.toolsmod.enchatment;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import java.util.Set;

public class LumberJackEnchantment extends AbstractVeinMiningEnchantment {

    private final static Set<Block> allowedBlocks = Sets.newHashSet(Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.WARPED_STEM, Blocks.CRIMSON_STEM, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD);

    public LumberJackEnchantment(Rarity rarity, EquipmentSlotType... equipmentSlotTypes) {
        super(rarity, EnchantmentType.DIGGER, equipmentSlotTypes);
    }

    @Override
    public int getMinCost(int level) {
        return 10;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 40;
    }

    @Override
    boolean isBlockAllowed(Block block) {
        return allowedBlocks.contains(block);
    }
}
