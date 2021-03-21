package com.hacker.programblock.proxy;

import com.hacker.programblock.CustomTNT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("all")
public class TNTEntity extends Entity {
    private final net.minecraft.entity.item.TNTEntity target;

    public TNTEntity(net.minecraft.entity.item.TNTEntity target) {
        this.target = target;
        setTarget(target);
    }

    public TNTEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        target = new CustomTNT(worldIn.getTarget(), x, y, z, igniter != null ? igniter.getTarget() : null);
        setTarget(target);
    }

    @Nonnull
    @Override
    public net.minecraft.entity.item.TNTEntity getTarget() {
        return target;
    }

    public void setFuse(int fuseIn) {
        target.setFuse(fuseIn);
    }

    public int getFuse() {
        return target.getFuse();
    }

    public void setPower(float power) {
        if (target instanceof CustomTNT)
            ((CustomTNT) target).setExplosionRadius(power);
    }

    public float getPower() {
        return target instanceof CustomTNT ? ((CustomTNT) target).getExplosionRadius() : 4;
    }

    public int getFuseTime() {
        return target.getFuseDataManager();
    }
}
