package com.hacker.programblock.proxy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nullable;

@SuppressWarnings("all")
public class Firework {
    private final CompoundNBT nbt;
    private final ListNBT explosions;

    public Firework() {
        nbt = new CompoundNBT();
        explosions = new ListNBT();
        nbt.putByte("Flight", (byte) 0);
        nbt.put("Explosions", explosions);
    }

    public Firework setFlight(int flight) {
        nbt.putByte("Flight", (byte) Math.min(Math.max(-128, flight), 127));
        return this;
    }

    public Firework addExplosion(boolean flicker, boolean trail, int type, int[] colors) {
        return addExplosion(flicker, trail, type, colors, null);
    }

    public Firework addExplosion(boolean flicker, boolean trail, int type, int[] colors, @Nullable int[] fadeColors) {
        CompoundNBT explosion = new CompoundNBT();
        explosion.putBoolean("Flicker", flicker);
        explosion.putBoolean("Trail", trail);
        explosion.putInt("Type", type);
        explosion.putIntArray("Colors", colors);
        if (fadeColors != null)
            explosion.putIntArray("FadeColors", fadeColors);
        explosions.add(explosion);
        return this;
    }

    CompoundNBT get() {
        return nbt;
    }
}
