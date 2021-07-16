package com.github.henners3k.toolsmod.enchatment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import org.apache.logging.log4j.LogManager;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractVeinMiningEnchantment extends Enchantment {

    private static final int MAX_BREAK = 128;

    protected AbstractVeinMiningEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... equipmentSlotTypes) {
        super(rarity, type, equipmentSlotTypes);
    }

    public static void onOriginalBlockMined(BlockEvent.BreakEvent event, AbstractVeinMiningEnchantment... enchantments) {
        for (AbstractVeinMiningEnchantment enchantment : enchantments)
            enchantment.onOriginalBlockMined(event, enchantment);
    }

    abstract boolean isBlockAllowed(Block block);


    protected void onOriginalBlockMined(BlockEvent.BreakEvent event, AbstractVeinMiningEnchantment enchantment) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getItemEnchantmentLevel(enchantment, player.getMainHandItem()) == 0)
            return;

        BlockPos minedPos = event.getPos();
        Block targetBlock = event.getState().getBlock();
        IWorld iworld = event.getWorld();

        if (!isBlockAllowed(targetBlock))
            return;

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

            if (currentState.getBlock().equals(targetBlock)) {
                world.destroyBlock(currentPos, false, player);
                TileEntity tileentity = currentState.hasTileEntity() ? world.getBlockEntity(currentPos) : null;
                Block.dropResources(currentState, world, minedPos, tileentity, player, player.getMainHandItem());
                n += 1;
            }

            for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                  A  for (int z = -1; z <= 1; z++) {
                        BlockPos pos = currentPos.offset(x, y, z);
                        Block block = world.getBlockState(pos).getBlock();
                        if (block.equals(targetBlock))
                            queue.add(pos);
                    }


        } while (n <= MAX_BREAK && !queue.isEmpty());

    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
