package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

public class SoundEvent implements IProxy<net.minecraft.util.SoundEvent> {
    private final net.minecraft.util.SoundEvent target;

    protected SoundEvent(net.minecraft.util.SoundEvent target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.util.SoundEvent getTarget() {
        return target;
    }
}
