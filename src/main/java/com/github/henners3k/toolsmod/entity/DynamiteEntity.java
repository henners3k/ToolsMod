package com.github.henners3k.toolsmod.entity;

import com.github.henners3k.toolsmod.registries.EntityRegistry;
import com.github.henners3k.toolsmod.registries.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DynamiteEntity extends ProjectileItemEntity {

    public DynamiteEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
        super(type, world);
    }

    public DynamiteEntity(World world, LivingEntity entity) {
        super(EntityRegistry.DYNAMITE.get(), entity, world);
    }

    public DynamiteEntity(World world, double x, double y, double z) {
        super(EntityRegistry.DYNAMITE.get(), x, y, z, world);
    }

    private void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 2.0F, Explosion.Mode.BREAK);
        this.remove();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult res) {
        super.onHitEntity(res);
        explode();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItemRaw()), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @Override
    protected void onHitBlock(BlockRayTraceResult res) {
        super.onHitBlock(res);
        explode();
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.DYNAMITE.get();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
