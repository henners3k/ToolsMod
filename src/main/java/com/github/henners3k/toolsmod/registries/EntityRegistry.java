package com.github.henners3k.toolsmod.registries;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.entity.DynamiteEntity;
import com.github.henners3k.toolsmod.entity.ExplosiveArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, H3KsToolsMod.MOD_ID);

    public static RegistryObject<EntityType<DynamiteEntity>> DYNAMITE = ENTITIES.<EntityType<DynamiteEntity>>register("dynamite", () -> EntityType.Builder.<DynamiteEntity>of(DynamiteEntity::new, EntityClassification.MISC).clientTrackingRange(4).sized(1, 1).updateInterval(10).build("dynamite"));
    public static RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITIES.<EntityType<ExplosiveArrowEntity>>register("explosive_arrow", () -> EntityType.Builder.<ExplosiveArrowEntity>of(ExplosiveArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("explosive_arrow"));

}
