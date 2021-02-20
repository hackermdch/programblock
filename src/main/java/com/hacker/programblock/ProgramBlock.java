package com.hacker.programblock;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class ProgramBlock extends ContainerBlock {
    public ProgramBlock() {
        super(Properties.create(Material.IRON, MaterialColor.BLUE).setRequiresTool().hardnessAndResistance(-1.0F, 3600000.0F).noDrops());
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof ProgramBlockTileEntity && player.canUseCommandBlock()) {
            openProgramBlock(player, (ProgramBlockTileEntity) tileentity);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            return ActionResultType.PASS;
        }
    }

    private static void openProgramBlock(PlayerEntity player, ProgramBlockTileEntity programBlock) {
        if (player.world.isRemote) {
            Minecraft.getInstance().displayGuiScreen(new ProgramBlockScreen(programBlock));
        }
    }

    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new ProgramBlockTileEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
