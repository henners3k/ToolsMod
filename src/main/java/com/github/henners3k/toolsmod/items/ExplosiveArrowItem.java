package com.github.henners3k.toolsmod.items;

import com.github.henners3k.toolsmod.entities.ExplosiveArrowEntity;
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
        ExplosiveArrowEntity arrowEntity = new ExplosiveArrowEntity(world, entity);
        arrowEntity.setEffectsFromItem(itemStack);
        return arrowEntity;
    }
}
