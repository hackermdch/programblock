package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.Vector3iAccessor;
import net.minecraft.dispenser.IPosition;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class Vector3i implements IProxy<net.minecraft.util.math.vector.Vector3i> {
    private final net.minecraft.util.math.vector.Vector3i target;

    public Vector3i(net.minecraft.util.math.vector.Vector3i target) {
        this.target = target;
    }

    public Vector3i(int xIn, int yIn, int zIn) {
        target = new net.minecraft.util.math.vector.Vector3i(xIn, yIn, zIn);
    }

    public Vector3i(double xIn, double yIn, double zIn) {
        target = new net.minecraft.util.math.vector.Vector3i(xIn, yIn, zIn);
    }

    @Nonnull
    @Override
    public net.minecraft.util.math.vector.Vector3i getTarget() {
        return target;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object p_equals_1_) {
        return target.equals(p_equals_1_);
    }

    public int hashCode() {
        return target.hashCode();
    }

    public int compareTo(Vector3i p_compareTo_1_) {
        return target.compareTo(p_compareTo_1_.target);
    }

    public int getX() {
        return target.getX();
    }

    public int getY() {
        return target.getY();
    }

    public int getZ() {
        return target.getZ();
    }

    protected void setX(int xIn) {
        ((Vector3iAccessor) target).invokesetX(xIn);
    }

    protected void setY(int yIn) {
        ((Vector3iAccessor) target).invokesetY(yIn);
    }

    protected void setZ(int zIn) {
        ((Vector3iAccessor) target).invokesetZ(zIn);
    }

    public boolean withinDistance(Vector3i vector, double distance) {
        return target.withinDistance(vector.target, distance);
    }

    public boolean withinDistance(IPosition position, double distance) {
        return target.withinDistance(position, distance);
    }

    public double distanceSq(Vector3i to) {
        return target.distanceSq(to.target);
    }

    public double distanceSq(IPosition position, boolean useCenter) {
        return target.distanceSq(position, useCenter);
    }

    public double distanceSq(double x, double y, double z, boolean useCenter) {
        return target.distanceSq(x, y, z, useCenter);
    }

    public int manhattanDistance(Vector3i vector) {
        return target.manhattanDistance(vector.target);
    }

    public int func_243648_a(Direction.Axis p_243648_1_) {
        return target.func_243648_a(p_243648_1_);
    }
}
