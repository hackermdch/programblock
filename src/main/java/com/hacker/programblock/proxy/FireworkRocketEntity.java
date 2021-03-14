package com.hacker.programblock.proxy;

import net.minecraft.entity.EntityType;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class FireworkRocketEntity extends Entity {
    private final net.minecraft.entity.projectile.FireworkRocketEntity target;

    public FireworkRocketEntity(net.minecraft.entity.projectile.FireworkRocketEntity target) {
        this.target = target;
    }

    public FireworkRocketEntity(World worldIn, double x, double y, double z) {
        target = new net.minecraft.entity.projectile.FireworkRocketEntity(worldIn.getTarget(), x, y, z, net.minecraft.item.ItemStack.EMPTY);
    }

    public FireworkRocketEntity(World worldIn, double x, double y, double z, Firework firework) {
        target = new net.minecraft.entity.projectile.FireworkRocketEntity(worldIn.getTarget(), x, y, z, firework.get().getTarget());
    }

    public FireworkRocketEntity(World worldIn, double x, double y, double z, ItemStack givenItem) {
        target = new net.minecraft.entity.projectile.FireworkRocketEntity(worldIn.getTarget(), x, y, z, givenItem.getTarget());
    }

    public FireworkRocketEntity(World world) {
        target = new net.minecraft.entity.projectile.FireworkRocketEntity(EntityType.FIREWORK_ROCKET, world.getTarget());
    }

    @Nonnull
    @Override
    public net.minecraft.entity.projectile.FireworkRocketEntity getTarget() {
        return target;
    }
}
