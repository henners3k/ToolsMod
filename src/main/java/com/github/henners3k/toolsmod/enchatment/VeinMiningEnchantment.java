package com.github.henners3k.toolsmod.enchatment;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class VeinMiningEnchantment extends AbstractVeinMiningEnchantment {

    public VeinMiningEnchantment(Rarity rarity, EquipmentSlotType... equipmentSlotTypes) {
        super(rarity, EnchantmentType.DIGGER, equipmentSlotTypes);
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    boolean isBlockAllowed(Block block) {
        return block instanceof OreBlock;
    }
}
