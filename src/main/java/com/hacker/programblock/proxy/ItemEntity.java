package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class ItemEntity extends Entity {
    private final net.minecraft.entity.item.ItemEntity target;

    public ItemEntity(net.minecraft.entity.item.ItemEntity target) {
        this.target = target;
        setTarget(target);
    }

    public ItemEntity(World worldIn, double x, double y, double z) {
        target = new net.minecraft.entity.item.ItemEntity(worldIn.getTarget(), x, y, z);
        setTarget(target);
    }

    public ItemEntity(World worldIn, double x, double y, double z, ItemStack stack) {
        target = new net.minecraft.entity.item.ItemEntity(worldIn.getTarget(), x, y, z, stack.getTarget());
        setTarget(target);
    }

    @Nonnull
    @Override
    public net.minecraft.entity.item.ItemEntity getTarget() {
        return target;
    }

    public ItemStack getItem() {
        return new ItemStack(target.getItem());
    }

    public void setItem(ItemStack stack) {
        target.setItem(stack.getTarget());
    }

    public void setPickupDelay(int ticks) {
        target.setPickupDelay(ticks);
    }

    public void setAge(int age) {
        target.age = age;
    }

    public int getAge() {
        return target.age;
    }
}
