package com.github.henners3k.toolsmod;

import com.github.henners3k.toolsmod.client.ModItemModelsProperties;
import com.github.henners3k.toolsmod.client.renderer.entity.ModRenderingHandler;
import com.github.henners3k.toolsmod.registries.EnchantmentRegistry;
import com.github.henners3k.toolsmod.registries.EntityRegistry;
import com.github.henners3k.toolsmod.registries.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(H3KsToolsMod.MOD_ID)
public class H3KsToolsMod {
    public static final String MOD_ID = "h3ks_tools";

    public static final ItemGroup ITEM_GROUP = new H3KsToolsItemGroup("h3ks_tools");

    public H3KsToolsMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityRegistry.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        EnchantmentRegistry.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ModItemModelsProperties::registerAll);
        ModRenderingHandler.registerEntities();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

}
