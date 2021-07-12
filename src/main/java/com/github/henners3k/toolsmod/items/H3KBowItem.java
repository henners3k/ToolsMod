package com.github.henners3k.toolsmod.items;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class H3KBowItem extends BowItem {

    public H3KBowItem(Item.Properties properties) {
        super(properties);
    }

    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.BOW;
    }


}
