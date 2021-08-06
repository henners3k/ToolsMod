package com.github.henners3k.toolsmod.enchatment;

import com.github.henners3k.toolsmod.registries.EnchantmentRegistry;
import com.github.henners3k.toolsmod.utils.AxisUtils;
import com.github.henners3k.toolsmod.utils.Vector3iUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class ExcavationEnchantment extends Enchantment {


    public ExcavationEnchantment(Rarity rarity, EquipmentSlotType... equipmentSlotTypes) {
        super(rarity, EnchantmentType.DIGGER, equipmentSlotTypes);
    }

    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        ItemStack itemStack = event.getPlayer().getItemInHand(Hand.MAIN_HAND);
        int excavationLevel = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.EXCAVATION.get(), itemStack);

        if (excavationLevel == 0 || !(event.getWorld() instanceof World))
            return;

        World world = (World) event.getWorld();

        BlockState state = event.getState();
        Block block = state.getBlock();

        if (block instanceof OreBlock || itemStack.getToolTypes().stream().noneMatch(tool -> block.isToolEffective(state, tool)))
            return;

        PlayerEntity player = event.getPlayer();
        RayTraceResult res = player.pick(20.0D, 0.0F, false);
        if (res.getType() != RayTraceResult.Type.BLOCK)
            return;

        BlockRayTraceResult blockRes = (BlockRayTraceResult) res;

        Direction.Axis ignoreAxis = blockRes.getDirection().getAxis();

        Direction.Axis axisA = ignoreAxis == Direction.Axis.X ? Direction.Axis.Y : Direction.Axis.X;
        Direction.Axis axisB = AxisUtils.other(ignoreAxis, axisA);

        BlockPos breakPos = event.getPos();

        for (int a = -1; a <= 1; a++)
            for (int b = -1; b <= 1; b++) {

                Vector3i offset = new Vector3iUtils.Builder().with(axisA, a).with(axisB, b).build();

                BlockPos currentPos = breakPos.offset(offset);
                BlockState currentState = world.getBlockState(currentPos);
                Block currentBlock = currentState.getBlock();

                if (currentBlock instanceof OreBlock || itemStack.getToolTypes().stream().noneMatch(tool -> currentBlock.isToolEffective(state, tool)))
                    continue;

                world.destroyBlock(currentPos, false, player);
                TileEntity tileentity = currentState.hasTileEntity() ? world.getBlockEntity(currentPos) : null;
                Block.dropResources(currentState, world, breakPos, tileentity, player, player.getMainHandItem());
            }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

}
