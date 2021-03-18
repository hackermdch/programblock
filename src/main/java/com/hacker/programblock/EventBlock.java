package com.hacker.programblock;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("all")
public class EventBlock extends ContainerBlock {
    public EventBlock() {
        super(Properties.create(Material.IRON, MaterialColor.GREEN).setRequiresTool().hardnessAndResistance(-1.0F, 3600000.0F).noDrops());
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof EventBlockTileEntity && player.canUseCommandBlock()) {
            openEventBlock(player, (EventBlockTileEntity) tileentity);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }

    private static void openEventBlock(PlayerEntity player, EventBlockTileEntity eventBlock) {
        if (player.world.isRemote) {
            GUIProxy.openEventBlock(eventBlock);
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new EventBlockTileEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
