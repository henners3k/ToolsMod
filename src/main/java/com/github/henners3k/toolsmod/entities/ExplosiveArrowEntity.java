package com.github.henners3k.toolsmod.entities;

import com.github.henners3k.toolsmod.registries.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrowEntity {
    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> type, World world) {
        super(type, world);
    }

    public ExplosiveArrowEntity(World world, LivingEntity livingEntity) {
        super(EntityRegistry.EXPLOSIVE_ARROW.get(), livingEntity, world);
    }

    @Override
    protected void onHit(RayTraceResult res) {
        super.onHit(res);
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 2.0F, Explosion.Mode.BREAK);
        this.remove();
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
