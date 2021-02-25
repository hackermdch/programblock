package com.hacker.programblock.proxy;

import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class Box implements IProxy<AxisAlignedBB> {
    private final AxisAlignedBB target;

    public Box(AxisAlignedBB target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getTarget() {
        return target;
    }

    public Box(double x1, double y1, double z1, double x2, double y2, double z2) {
        target = new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
    }

    public Box(BlockPos pos) {
        target = new AxisAlignedBB(pos.getTarget());
    }

    public Box(BlockPos pos1, BlockPos pos2) {
        target = new AxisAlignedBB(pos1.getTarget(), pos2.getTarget());
    }

    public Box(Vector3d min, Vector3d max) {
        target = new AxisAlignedBB(min, max);
    }

    public double getMin(Direction.Axis axis) {
        return target.getMin(axis);
    }

    public double getMax(Direction.Axis axis) {
        return target.getMax(axis);
    }

    public boolean equals(Object p_equals_1_) {
        return target.equals(p_equals_1_);
    }

    public int hashCode() {
        return target.hashCode();
    }

    public String toString() {
        return target.toString();
    }

    public boolean intersects(Box other) {
        return target.intersects(other.target);
    }

    public boolean intersects(double x1, double y1, double z1, double x2, double y2, double z2) {
        return target.intersects(x1, y1, z1, x2, y2, z2);
    }

    public boolean intersects(Vector3d min, Vector3d max) {
        return target.intersects(min, max);
    }

    public boolean contains(Vector3d vec) {
        return target.contains(vec);
    }

    public boolean contains(double x, double y, double z) {
        return target.contains(x, y, z);
    }

    public double getAverageEdgeLength() {
        return target.getAverageEdgeLength();
    }

    public double getXSize() {
        return target.getXSize();
    }

    public double getYSize() {
        return target.getYSize();
    }

    public double getZSize() {
        return target.getZSize();
    }

    public boolean hasNaN() {
        return target.hasNaN();
    }
}
