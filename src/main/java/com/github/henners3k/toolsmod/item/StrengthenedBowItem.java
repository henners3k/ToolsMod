package com.github.henners3k.toolsmod.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StrengthenedBowItem extends BowItem {

    private final double damageMultiplier;
    private final float drawbackMultiplier;

    public StrengthenedBowItem(double damageMultiplier, float drawbackMultiplier, Item.Properties properties) {
        super(properties);
        this.damageMultiplier = damageMultiplier;
        this.drawbackMultiplier = drawbackMultiplier;
    }

    public float getDrawbackMultiplier() {
        return drawbackMultiplier;
    }

    @Nonnull
    public UseAction getUseAnimation(@Nullable ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void releaseUsing(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull LivingEntity entity, int n) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            boolean flag = player.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack projectileStack = player.getProjectile(stack);

            int i = this.getUseDuration(stack) - n;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, player, i, !projectileStack.isEmpty() || flag);
            if (i < 0) return;

            if (!projectileStack.isEmpty() || flag) {
                if (projectileStack.isEmpty()) {
                    projectileStack = new ItemStack(Items.ARROW);
                }

                float power = this.getPowerForTimeWithDM(i);
                if (!((double) power < 0.1D)) {
                    boolean flag1 = player.abilities.instabuild || (projectileStack.getItem() instanceof ArrowItem && ((ArrowItem) projectileStack.getItem()).isInfinite(projectileStack, stack, player));
                    if (!world.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem) (projectileStack.getItem() instanceof ArrowItem ? projectileStack.getItem() : Items.ARROW);
                        AbstractArrowEntity arrowEntity = arrowitem.createArrow(world, projectileStack, player);
                        arrowEntity = customArrow(arrowEntity);
                        arrowEntity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, power * 3.0F, 1.0F);
                        if (power == 1.0F)
                            arrowEntity.setCritArrow(true);

                        arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() * damageMultiplier);

                        int powerEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stack);
                        if (powerEnchantmentLevel > 0)
                            arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() + (double) powerEnchantmentLevel * 0.5D + 0.5D);

                        int punchEnchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stack);
                        if (punchEnchantmentLevel > 0)
                            arrowEntity.setKnockback(punchEnchantmentLevel);


                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stack) > 0)
                            arrowEntity.setSecondsOnFire(100);


                        stack.hurtAndBreak(1, player, (player2) -> player2.broadcastBreakEvent(player.getUsedItemHand()));

                        if (flag1 || player.abilities.instabuild && (projectileStack.getItem() == Items.SPECTRAL_ARROW || projectileStack.getItem() == Items.TIPPED_ARROW)) {
                            arrowEntity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        world.addFreshEntity(arrowEntity);
                    }

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + power * 0.5F);
                    if (!flag1 && !player.abilities.instabuild) {
                        projectileStack.shrink(1);
                        if (projectileStack.isEmpty()) {
                            player.inventory.removeItem(projectileStack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack stack) {
        return (int) (super.getUseDuration(stack) / drawbackMultiplier);
    }

    private float getPowerForTimeWithDM(int time) {
        float f = (float) time / 20.0F;
        f *= drawbackMultiplier;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }
}
