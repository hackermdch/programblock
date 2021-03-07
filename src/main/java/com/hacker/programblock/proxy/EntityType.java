package com.hacker.programblock.proxy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("all")
public class EntityType<T extends Entity> implements IProxy<net.minecraft.entity.EntityType<?>> {
    private final net.minecraft.entity.EntityType<?> target;

    public EntityType(net.minecraft.entity.EntityType<?> target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.entity.EntityType<?> getTarget() {
        return target;
    }

    @Nullable
    public T create(World worldIn) throws IllegalAccessException, InstantiationException {
        net.minecraft.entity.Entity r = target.create(worldIn.getTarget());
        return r != null ? (T) new Entity(r) : null;
    }
}
