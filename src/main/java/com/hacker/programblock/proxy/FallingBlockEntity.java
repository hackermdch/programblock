package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class FallingBlockEntity extends Entity {
    private final net.minecraft.entity.item.FallingBlockEntity target;

    public FallingBlockEntity(@Nonnull net.minecraft.entity.item.FallingBlockEntity target) {
        super(target);
        this.target = target;
    }

    public FallingBlockEntity(World worldIn, double x, double y, double z, BlockState blockState) {
        super(new net.minecraft.entity.item.FallingBlockEntity(worldIn.getTarget(), x, y, z, blockState.getTarget()));
        this.target = (net.minecraft.entity.item.FallingBlockEntity) super.getTarget();
        target.fallTime = 1;
    }

    @Nonnull
    @Override
    public net.minecraft.entity.item.FallingBlockEntity getTarget() {
        return target;
    }

    public void setFallTime(int time) {
        target.fallTime = time;
    }

    public int getFallTime() {
        return target.fallTime;
    }

    public BlockState getBlockState() {
        return new BlockState(target.fallTile);
    }

    public void setBlockState(BlockState state) {
        target.fallTile = state.getTarget();
    }
}
