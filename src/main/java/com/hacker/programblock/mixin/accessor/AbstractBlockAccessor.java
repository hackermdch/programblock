package com.hacker.programblock.mixin.accessor;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor {
    @Invoker(value = "isAir", remap = false)
    boolean invokeisAir(BlockState state);
}