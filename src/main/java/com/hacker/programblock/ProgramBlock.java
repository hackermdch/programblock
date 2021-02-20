package com.hacker.programblock;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

@SuppressWarnings("deprecation")
public class ProgramBlock extends ContainerBlock {
    public ProgramBlock() {
        super(Properties.create(Material.IRON, MaterialColor.BROWN).setRequiresTool().hardnessAndResistance(-1.0F, 3600000.0F).noDrops());
    }

    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ProgramBlockTileEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
