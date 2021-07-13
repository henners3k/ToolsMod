package com.github.henners3k.toolsmod.registries;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.entities.DynamiteEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, H3KsToolsMod.MOD_ID);

    public static RegistryObject<EntityType<DynamiteEntity>> DYNAMITE = ENTITIES.<EntityType<DynamiteEntity>>register("dynamite", () -> EntityType.Builder.<DynamiteEntity>of(DynamiteEntity::new, EntityClassification.MISC).clientTrackingRange(4).sized(1, 1).updateInterval(10).build("dynamite"));

}
