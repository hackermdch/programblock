package com.hacker.programblock.proxy;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

@SuppressWarnings("all")
public class FireworkRocketEntity extends Entity {
    private final net.minecraft.entity.projectile.FireworkRocketEntity target;

    public FireworkRocketEntity(net.minecraft.entity.projectile.FireworkRocketEntity target) {
        super(target);
        this.target = target;
    }

    public FireworkRocketEntity(World worldIn, double x, double y, double z) {
        super(new net.minecraft.entity.projectile.FireworkRocketEntity(worldIn.getTarget(), x, y, z, net.minecraft.item.ItemStack.EMPTY));
        target = (net.minecraft.entity.projectile.FireworkRocketEntity) super.getTarget();
    }

    public FireworkRocketEntity(World worldIn, double x, double y, double z, ItemStack givenItem) {
        super(new net.minecraft.entity.projectile.FireworkRocketEntity(worldIn.getTarget(), x, y, z, givenItem.getTarget()));
        target = (net.minecraft.entity.projectile.FireworkRocketEntity) super.getTarget();
    }

    public FireworkRocketEntity(World world) {
        super(new net.minecraft.entity.projectile.FireworkRocketEntity(EntityType.FIREWORK_ROCKET, world.getTarget()));
        target = (net.minecraft.entity.projectile.FireworkRocketEntity) super.getTarget();
    }

    @Nonnull
    @Override
    public net.minecraft.entity.projectile.FireworkRocketEntity getTarget() {
        return target;
    }

    public void setFirework(Firework firework) {
        net.minecraft.item.ItemStack s = new net.minecraft.item.ItemStack(Items.FIREWORK_ROCKET, 1);
        CompoundNBT firw = new CompoundNBT();
        firw.put("Fireworks", firework.get());
        s.setTag(firw);
        target.dataManager.set(net.minecraft.entity.projectile.FireworkRocketEntity.FIREWORK_ITEM, s);
    }
}
