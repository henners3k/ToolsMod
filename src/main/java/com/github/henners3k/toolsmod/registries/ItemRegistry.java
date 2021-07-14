package com.github.henners3k.toolsmod.registries;

import com.github.henners3k.toolsmod.H3KsToolsMod;
import com.github.henners3k.toolsmod.item.DynamiteItem;
import com.github.henners3k.toolsmod.item.ExplosiveArrowItem;
import com.github.henners3k.toolsmod.item.StrengthenedBowItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, H3KsToolsMod.MOD_ID);

    // Bows
    public static RegistryObject<StrengthenedBowItem> IRON_BOW = ITEMS.register("iron_bow", () -> new StrengthenedBowItem(1.5, 1, new Item.Properties().durability(1627).tab(H3KsToolsMod.ITEM_GROUP)));
    public static RegistryObject<StrengthenedBowItem> GOLD_BOW = ITEMS.register("gold_bow", () -> new StrengthenedBowItem(1, 2, new Item.Properties().durability(208).tab(H3KsToolsMod.ITEM_GROUP)));
    public static RegistryObject<StrengthenedBowItem> DIAMOND_BOW = ITEMS.register("diamond_bow", () -> new StrengthenedBowItem(1.75, 1, new Item.Properties().durability(10160).tab(H3KsToolsMod.ITEM_GROUP)));
    public static RegistryObject<StrengthenedBowItem> NETHERITE_BOW = ITEMS.register("netherite_bow", () -> new StrengthenedBowItem(2, 2, new Item.Properties().durability(13219).tab(H3KsToolsMod.ITEM_GROUP)));

    // Dynamite Related
    public static RegistryObject<DynamiteItem> DYNAMITE = ITEMS.register("dynamite", () -> new DynamiteItem(new Item.Properties().tab(H3KsToolsMod.ITEM_GROUP)));
    public static RegistryObject<ExplosiveArrowItem> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow", () -> new ExplosiveArrowItem((new Item.Properties().tab(H3KsToolsMod.ITEM_GROUP))));
    // Materials
    public static RegistryObject<Item> DIAMOND_SHARD = ITEMS.register("diamond_shard", () -> new Item(new Item.Properties().tab(H3KsToolsMod.ITEM_GROUP)));
}
