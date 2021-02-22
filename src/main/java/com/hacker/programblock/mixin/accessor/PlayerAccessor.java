package com.hacker.programblock.mixin.accessor;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerEntity.class)
public interface PlayerAccessor {
    @Invoker("registerData")
    void invokeregisterData();

    @Invoker("wantsToStopRiding")
    boolean invokewantsToStopRiding();

    @Invoker("isStayingOnGroundSurface")
    boolean invokeisStayingOnGroundSurface();

    @Invoker("updateEyesInWaterPlayer")
    boolean invokeupdateEyesInWaterPlayer();

    @Invoker("updatePose")
    void invokeupdatePose();

    @Invoker("getFireImmuneTicks")
    int invokegetFireImmuneTicks();

    @Invoker("updateEntityActionState")
    void invokeupdateEntityActionState();

    @Invoker("dropInventory")
    void invokedropInventory();

    @Invoker("destroyVanishingCursedItems")
    void invokedestroyVanishingCursedItems();

    @Invoker("blockUsingShield")
    void invokeblockUsingShield(LivingEntity entityIn);

    @Invoker("damageArmor")
    void invokedamageArmor(DamageSource damageSource, float damage);

    @Invoker("damageShield")
    void invokedamageShield(float damage);

    @Invoker("damageEntity")
    void invokedamageEntity(DamageSource damageSrc, float damageAmount);

    @Invoker("func_230296_cM_")
    boolean invokefunc_230296_cM_();

    @Invoker("isMovementBlocked")
    boolean invokeisMovementBlocked();

    @Invoker("spinAttack")
    void invokespinAttack(LivingEntity p_204804_1_);

    @Invoker("isNormalCube")
    boolean invokeisNormalCube(BlockPos pos);

    @Invoker("doWaterSplashEffect")
    void invokedoWaterSplashEffect();

    @Invoker("getExperiencePoints")
    int invokegetExperiencePoints(PlayerEntity player);

    @Invoker("isPlayer")
    boolean invokeisPlayer();

    @Invoker("canTriggerWalking")
    boolean invokecanTriggerWalking();

    @Invoker("spawnShoulderEntities")
    void invokespawnShoulderEntities();

    @Invoker("setLeftShoulderEntity")
    void invokesetLeftShoulderEntity(CompoundNBT tag);

    @Invoker("setRightShoulderEntity")
    void invokesetRightShoulderEntity(CompoundNBT tag);

    @Invoker("getSpeedFactor")
    float invokegetSpeedFactor();

    @Invoker("func_230295_b_")
    boolean invokefunc_230295_b_(BlockState p_230295_1_);
}