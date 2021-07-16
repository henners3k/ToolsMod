package com.github.henners3k.toolsmod.subscribers;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.enchatment.VeinMiningEnchantment;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = H3KsToolsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {
    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        VeinMiningEnchantment.handleBlockBreak(event);
    }
}
