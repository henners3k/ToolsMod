package com.github.henners3k.toolsmod.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;

public class DynamiteItem extends Item {
    public DynamiteItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean isEmpty = player.getProjectile(stack).isEmpty();

        if (!player.abilities.instabuild && !isEmpty)
            return ActionResult.fail(stack);

        player.startUsingItem(hand);
        return ActionResult.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int usedFor) {

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            if (!world.isClientSide) {
                // THROW DYNAMITE

                LogManager.getLogger().info("BOOM");
            }

            if (!player.abilities.instabuild) {
                stack.shrink(1);
                if (stack.isEmpty())
                    player.inventory.removeItem(stack);
            }

        }
    }

}
