package com.github.henners3k.toolsmod.items;

import com.github.henners3k.toolsmod.entities.DynamiteEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
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
        boolean hasAmmo = !stack.isEmpty();

        if (!player.abilities.instabuild && !hasAmmo)
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
                DynamiteEntity dynamiteEntity = new DynamiteEntity(world, entity);
                dynamiteEntity.setItem(stack);
                dynamiteEntity.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, 1.5F, 1.0F);
                world.addFreshEntity(dynamiteEntity);
            }


            if (!player.abilities.instabuild) {
                stack.shrink(1);
                if (stack.isEmpty())
                    player.inventory.removeItem(stack);
            }

        }
    }

    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.SPEAR;
    }


}
