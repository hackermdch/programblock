package com.hacker.programblock.mixin.accessor;

import net.minecraft.util.math.vector.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Vector3i.class)
public interface Vector3iAccessor {
    @Invoker("setX")
    void invokesetX(int xIn);

    @Invoker("setY")
    void invokesetY(int yIn);

    @Invoker("setZ")
    void invokesetZ(int zIn);
}