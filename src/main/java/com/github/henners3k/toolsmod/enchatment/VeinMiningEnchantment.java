package com.github.henners3k.toolsmod.enchatment;

import com.github.henners3k.toolsmod.registries.EnchantmentRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import org.apache.logging.log4j.LogManager;

import java.util.LinkedList;
import java.util.Queue;

public class VeinMiningEnchantment extends Enchantment {

    private static final int MAX_BREAK = 128;

    public VeinMiningEnchantment(Rarity rarity, EquipmentSlotType... slotTypes) {
        super(rarity, EnchantmentType.DIGGER, slotTypes);
    }

    public static void handleBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.VEIN_MINING.get(), player.getMainHandItem()) == 0)
            return;

        boolean silkTouch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, player.getMainHandItem()) > 0;

        BlockPos minedPos = event.getPos();
        Block targetBlock = event.getState().getBlock();
        IWorld iworld = event.getWorld();

        if (!(iworld instanceof ServerWorld)) {
            LogManager.getLogger().error("FAK!");
            return;
        }
        ServerWorld world = (ServerWorld) iworld;

        int n = 0;
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(minedPos);

        do {
            BlockPos currentPos = queue.remove();
            BlockState currentState = world.getBlockState(currentPos);

            TileEntity tileEntity = currentState.hasTileEntity() ? world.getBlockEntity(currentPos) : null;
            Block.dropResources(currentState, world, minedPos, tileEntity, player, player.getMainHandItem());

            if (currentState.getBlock().equals(targetBlock)) {
                world.destroyBlock(currentPos, false, player);
                n += 1;

            }

            for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                    for (int z = -1; z <= 1; z++) {
                        BlockPos pos = currentPos.offset(x, y, z);
                        Block block = world.getBlockState(pos).getBlock();
                        if (block.equals(targetBlock))
                            queue.add(pos);
                    }


        } while (n <= MAX_BREAK && !queue.isEmpty());

//        if (silkTouch) {
//            int maxStack = new ItemStack(targetBlock).getMaxStackSize();
//
//            for (int i = 0; i < n / maxStack; i++) {
//                ItemEntity itemEntity = new ItemEntity(world, minedPos.getX(), minedPos.getY(), minedPos.getZ(), new ItemStack(targetBlock, maxStack));
//                world.addFreshEntity(itemEntity);
//            }
//
//            if (n % maxStack != 0) {
//                ItemEntity itemEntity = new ItemEntity(world, minedPos.getX(), minedPos.getY(), minedPos.getZ(), new ItemStack(targetBlock, n % maxStack));
//                world.addFreshEntity(itemEntity);
//            }
//
//        }

    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
