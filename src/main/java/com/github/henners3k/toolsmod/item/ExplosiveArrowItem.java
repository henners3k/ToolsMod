package com.github.henners3k.toolsmod.item;

import com.github.henners3k.toolsmod.entity.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveArrowItem extends ArrowItem {

    public ExplosiveArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack itemStack, LivingEntity entity) {
        return new ExplosiveArrowEntity(world, entity);
    }
}
