package com.github.henners3k.toolsmod.client.renderer.entity;

import com.github.henners3k.toolsmod.registries.EntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public final class ModRenderingHandler {

    public static void registerEntities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DYNAMITE.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
    }
}
