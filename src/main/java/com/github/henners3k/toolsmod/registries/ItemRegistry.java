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
    public static RegistryObject<H3KBowItem> IRON_BOW = ITEMS.register("iron_bow", () -> new H3KBowItem(1.5, 1, new Item.Properties().durability(1627)));
    public static RegistryObject<H3KBowItem> GOLD_BOW = ITEMS.register("gold_bow", () -> new H3KBowItem(1, 2, new Item.Properties().durability(384)));

}
