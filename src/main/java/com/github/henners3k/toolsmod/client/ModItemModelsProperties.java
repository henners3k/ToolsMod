package com.github.henners3k.toolsmod.client;

import com.github.henners3k.toolsmod.item.StrengthenedBowItem;
import com.github.henners3k.toolsmod.registries.ItemRegistry;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class ModItemModelsProperties {

    private static void registerBow(StrengthenedBowItem item) {
        ItemModelsProperties.register(item, new ResourceLocation("pull"), (stack, world, entity) -> entity == null ? 0.0F : (entity.getUseItem() != stack ? 0.0F : ((stack.getUseDuration() - entity.getUseItemRemainingTicks()) * item.getDrawbackMultiplier()) / 20.0F));
        ItemModelsProperties.register(item, new ResourceLocation("pulling"), (stack, world, entity) -> (entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0f));
    }

    public static void registerAll() {
        registerBow(ItemRegistry.IRON_BOW.get());
        registerBow(ItemRegistry.GOLD_BOW.get());
        registerBow(ItemRegistry.DIAMOND_BOW.get());
        registerBow(ItemRegistry.NETHERITE_BOW.get());
    }

}
