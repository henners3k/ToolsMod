package com.github.henners3k.toolsmod.registries;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.items.H3KBowItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, H3KsToolsMod.MOD_ID);

    // Bows
    public static RegistryObject<H3KBowItem> IRON_BOW = ITEMS.register("iron_bow", () -> new H3KBowItem(new Item.Properties().durability(1627)));

}
