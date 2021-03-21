package com.hacker.programblock;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("all")
public class CustomTNT extends TNTEntity {
    private float explosionRadius = 4;

    public CustomTNT(EntityType<? extends TNTEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public CustomTNT(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(worldIn, x, y, z, igniter);
    }

    public void setExplosionRadius(float radius) {
        explosionRadius = radius;
    }

    public float getExplosionRadius() {
        return explosionRadius;
    }

    @Override
    protected void explode() {
        world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), explosionRadius, Explosion.Mode.BREAK);
    }
}
