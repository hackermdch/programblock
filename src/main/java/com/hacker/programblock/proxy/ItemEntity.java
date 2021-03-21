package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class ItemEntity extends Entity {
    private final net.minecraft.entity.item.ItemEntity target;

    public ItemEntity(net.minecraft.entity.item.ItemEntity target) {
        this.target = target;
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
