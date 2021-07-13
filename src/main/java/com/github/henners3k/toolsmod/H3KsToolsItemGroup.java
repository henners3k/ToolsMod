package com.github.henners3k.toolsmod;

import com.github.henners3k.toolsmod.registries.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class H3KsToolsItemGroup extends ItemGroup {
    public H3KsToolsItemGroup(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.DIAMOND_BOW.get());
    }
}
