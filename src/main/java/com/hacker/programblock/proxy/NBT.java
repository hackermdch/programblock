package com.hacker.programblock.proxy;

import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("all")
public class NBT implements IProxy<CompoundNBT> {
    private final CompoundNBT target;

    public NBT() {
        target = new CompoundNBT();
    }

    public NBT(CompoundNBT target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public CompoundNBT getTarget() {
        return target;
    }

    public byte getId() {
        return target.getId();
    }

    public int size() {
        return target.size();
    }

    public NBT put(String key, NBT value) {
        return new NBT((CompoundNBT) target.put(key, value.target));
    }

    public void putByte(String key, byte value) {
        target.putByte(key, value);
    }

    public void putShort(String key, short value) {
        target.putShort(key, value);
    }

    public void putInt(String key, int value) {
        target.putInt(key, value);
    }

    public void putLong(String key, long value) {
        target.putLong(key, value);
    }

    public void putUniqueId(String key, UUID value) {
        target.putUniqueId(key, value);
    }

    public boolean hasUniqueId(String key) {
        return target.hasUniqueId(key);
    }

    public void putFloat(String key, float value) {
        target.putFloat(key, value);
    }

    public void putDouble(String key, double value) {
        target.putDouble(key, value);
    }

    public void putString(String key, String value) {
        target.putString(key, value);
    }

    public void putByteArray(String key, byte[] value) {
        target.putByteArray(key, value);
    }

    public void putIntArray(String key, int[] value) {
        target.putIntArray(key, value);
    }

    public void putIntArray(String key, List<Integer> value) {
        target.putIntArray(key, value);
    }

    public void putLongArray(String key, long[] value) {
        target.putLongArray(key, value);
    }

    public void putLongArray(String key, List<Long> value) {
        target.putLongArray(key, value);
    }

    public void putBoolean(String key, boolean value) {
        target.putBoolean(key, value);
    }

    public byte getTagId(String key) {
        return target.getTagId(key);
    }

    public boolean contains(String key) {
        return target.contains(key);
    }

    public boolean contains(String key, int type) {
        return target.contains(key, type);
    }

    public NBT get(String key) {
        return new NBT(target.getCompound(key));
    }

    public byte getByte(String key) {
        return target.getByte(key);
    }

    public short getShort(String key) {
        return target.getShort(key);
    }

    public int getInt(String key) {
        return target.getInt(key);
    }

    public long getLong(String key) {
        return target.getLong(key);
    }

    public float getFloat(String key) {
        return target.getFloat(key);
    }

    public double getDouble(String key) {
        return target.getDouble(key);
    }

    public boolean getBoolean(String key) {
        return target.getBoolean(key);
    }

    public String getString(String key) {
        return target.getString(key);
    }

    public void remove(String key) {
        target.remove(key);
    }

    public boolean isEmpty() {
        return target.isEmpty();
    }

    public boolean equals(Object p_equals_1_) {
        return target.equals(p_equals_1_);
    }

    public int hashCode() {
        return target.hashCode();
    }
}
