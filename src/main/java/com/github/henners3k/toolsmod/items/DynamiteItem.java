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
import javax.annotation.Nullable;

public class DynamiteItem extends Item {
    public DynamiteItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> use(@Nullable World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        boolean hasAmmo = !stack.isEmpty();

        if (!player.abilities.instabuild && !hasAmmo)
            return ActionResult.fail(stack);

        player.startUsingItem(hand);
        return ActionResult.consume(stack);
    }

    @Override
    public int getUseDuration(@Nullable ItemStack stack) {
        return 72000;
    }

    private float getPowerFromTime(int timeLeft) {
        float maxAt = 2.5f * 20F;
        float ticksPassed = getUseDuration(null) - timeLeft;
        float power = 0.05f + ticksPassed / maxAt;
        return Math.min(power, 1f);
    }

    @Override
    public void releaseUsing(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull LivingEntity entity, int timeLeft) {

        float power = getPowerFromTime(timeLeft);
        LogManager.getLogger().info(power);
        if (power <= 0.15f)
            return;

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            if (!world.isClientSide) {
                DynamiteEntity dynamiteEntity = new DynamiteEntity(world, entity);
                dynamiteEntity.setItem(stack);
                dynamiteEntity.shootFromRotation(entity, entity.xRot, entity.yRot, 0.0F, power * 2F, 1.0F);
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
    @Nonnull
    public UseAction getUseAnimation(@Nullable ItemStack stack) {
        return UseAction.SPEAR;
    }

}
