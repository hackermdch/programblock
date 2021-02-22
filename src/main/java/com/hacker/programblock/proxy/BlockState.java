package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

public class BlockState extends AbstractBlockState {
    private final net.minecraft.block.BlockState target;

    public BlockState(@Nonnull net.minecraft.block.BlockState target) {
        super(target);
        this.target=target;
    }

    @Nonnull
    @Override
    public net.minecraft.block.BlockState getTarget() {
        return target;
    }
}
