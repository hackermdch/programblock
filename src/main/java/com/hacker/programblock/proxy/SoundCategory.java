package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public enum SoundCategory implements IProxy<net.minecraft.util.SoundCategory> {
    MASTER(net.minecraft.util.SoundCategory.MASTER),
    MUSIC(net.minecraft.util.SoundCategory.MUSIC),
    RECORDS(net.minecraft.util.SoundCategory.RECORDS),
    WEATHER(net.minecraft.util.SoundCategory.WEATHER),
    BLOCKS(net.minecraft.util.SoundCategory.BLOCKS),
    HOSTILE(net.minecraft.util.SoundCategory.HOSTILE),
    NEUTRAL(net.minecraft.util.SoundCategory.NEUTRAL),
    PLAYERS(net.minecraft.util.SoundCategory.PLAYERS),
    AMBIENT(net.minecraft.util.SoundCategory.AMBIENT),
    VOICE(net.minecraft.util.SoundCategory.VOICE);

    private final net.minecraft.util.SoundCategory target;

    private SoundCategory(net.minecraft.util.SoundCategory target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.util.SoundCategory getTarget() {
        return target;
    }
}
