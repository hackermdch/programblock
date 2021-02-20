package com.hacker.programblock.mixin.accessor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockAccessor {
    @Invoker("fillStateContainer")
    void invokefillStateContainer(StateContainer.Builder<Block, BlockState> builder);

    @Invoker("setDefaultState")
    void invokesetDefaultState(BlockState state);
}