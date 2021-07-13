package com.github.henners3k.toolsmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosiveArrowEntity extends ArrowEntity {
    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> type, World world) {
        super(type, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity livingEntity) {
        super(world, livingEntity);
    }

    @Override
    protected void onHit(RayTraceResult res) {
        super.onHit(res);
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 2.0F, Explosion.Mode.BREAK);
        this.remove();
    }
}
