package com.hacker.programblock.proxy;

import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class BlockPos extends Vector3i {
    private final net.minecraft.util.math.BlockPos target;

    public BlockPos(net.minecraft.util.math.BlockPos target) {
        super(target);
        this.target = target;
    }

    public BlockPos(int xIn, int yIn, int zIn) {
        super(xIn, yIn, zIn);
        target = new net.minecraft.util.math.BlockPos(xIn, yIn, zIn);
    }

    public BlockPos(double xIn, double yIn, double zIn) {
        super(xIn, yIn, zIn);
        target = new net.minecraft.util.math.BlockPos(xIn, yIn, zIn);
    }

    @Nonnull
    @Override
    public net.minecraft.util.math.BlockPos getTarget() {
        return target;
    }

    public BlockPos add(double x, double y, double z) {
        return new BlockPos(target.add(x, y, z));
    }

    /**
     * Add the given coordinates to the coordinates of this BlockPos
     */
    public BlockPos add(int x, int y, int z) {
        return new BlockPos(target.add(x, y, z));
    }

    /**
     * Add the given Vector to this BlockPos
     */
    public BlockPos add(net.minecraft.util.math.vector.Vector3i vec) {
        return this.add(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Subtract the given Vector from this BlockPos
     */
    public BlockPos subtract(net.minecraft.util.math.vector.Vector3i vec) {
        return this.add(-vec.getX(), -vec.getY(), -vec.getZ());
    }

    /**
     * Offset this BlockPos 1 block up
     */
    public BlockPos up() {
        return this.offset(Direction.UP);
    }

    /**
     * Offset this BlockPos n blocks up
     */
    public BlockPos up(int n) {
        return this.offset(Direction.UP, n);
    }

    /**
     * Offset this BlockPos 1 block down
     */
    public BlockPos down() {
        return this.offset(Direction.DOWN);
    }

    /**
     * Offset this BlockPos n blocks down
     */
    public BlockPos down(int n) {
        return this.offset(Direction.DOWN, n);
    }

    /**
     * Offset this BlockPos 1 block in northern direction
     */
    public BlockPos north() {
        return this.offset(Direction.NORTH);
    }

    /**
     * Offset this BlockPos n blocks in northern direction
     */
    public BlockPos north(int n) {
        return this.offset(Direction.NORTH, n);
    }

    /**
     * Offset this BlockPos 1 block in southern direction
     */
    public BlockPos south() {
        return this.offset(Direction.SOUTH);
    }

    /**
     * Offset this BlockPos n blocks in southern direction
     */
    public BlockPos south(int n) {
        return this.offset(Direction.SOUTH, n);
    }

    /**
     * Offset this BlockPos 1 block in western direction
     */
    public BlockPos west() {
        return this.offset(Direction.WEST);
    }

    /**
     * Offset this BlockPos n blocks in western direction
     */
    public BlockPos west(int n) {
        return this.offset(Direction.WEST, n);
    }

    /**
     * Offset this BlockPos 1 block in eastern direction
     */
    public BlockPos east() {
        return this.offset(Direction.EAST);
    }

    /**
     * Offset this BlockPos n blocks in eastern direction
     */
    public BlockPos east(int n) {
        return this.offset(Direction.EAST, n);
    }

    /**
     * Offset this BlockPos 1 block in the given direction
     */
    public BlockPos offset(Direction facing) {
        return new BlockPos(target.offset(facing));
    }

    /**
     * Offsets this BlockPos n blocks in the given direction
     */
    public BlockPos offset(Direction facing, int n) {
        return new BlockPos(target.offset(facing, n));
    }
}
