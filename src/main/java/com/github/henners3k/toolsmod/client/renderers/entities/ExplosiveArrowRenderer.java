package com.github.henners3k.toolsmod.client.renderers.entities;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.entities.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(H3KsToolsMod.MOD_ID, "textures/entity/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
    }

    @Override
    @Nonnull
    public ResourceLocation getTextureLocation(@Nullable ExplosiveArrowEntity p_110775_1_) {
        return TEXTURE_LOCATION;
    }
}
