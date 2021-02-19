package com.hacker.programblock.mixin.accessor;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Invoker("registerData")
    void invokeregisterData();

    @Invoker("preparePlayerToSpawn")
    void invokepreparePlayerToSpawn();

    @Invoker("setRotation")
    void invokesetRotation(float yaw, float pitch);

    @Invoker("recenterBoundingBox")
    void invokerecenterBoundingBox();

    @Invoker("decrementTimeUntilPortal")
    void invokedecrementTimeUntilPortal();

    @Invoker("setOnFireFromLava")
    void invokesetOnFireFromLava();

    @Invoker("outOfWorld")
    void invokeoutOfWorld();

    @Invoker("getJumpFactor")
    float invokegetJumpFactor();

    @Invoker("getSpeedFactor")
    float invokegetSpeedFactor();

    @Invoker("determineNextStepDistance")
    float invokedetermineNextStepDistance();

    @Invoker("doBlockCollisions")
    void invokedoBlockCollisions();

    @Invoker("onInsideBlock")
    void invokeonInsideBlock(BlockState state);

    @Invoker("playStepSound")
    void invokeplayStepSound(BlockPos pos, BlockState blockIn);

    @Invoker("playSwimSound")
    void invokeplaySwimSound(float volume);

    @Invoker("playFlySound")
    float invokeplayFlySound(float volume);

    @Invoker("makeFlySound")
    boolean invokemakeFlySound();

    @Invoker("canTriggerWalking")
    boolean invokecanTriggerWalking();

    @Invoker("updateFallState")
    void invokeupdateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos);

    @Invoker("func_233566_aG_")
    boolean invokefunc_233566_aG_();

    @Invoker("updateWaterState")
    void invokeupdateWaterState();

    @Invoker("doWaterSplashEffect")
    void invokedoWaterSplashEffect();

    @Invoker("handleRunningEffect")
    void invokehandleRunningEffect();

    @Invoker("markVelocityChanged")
    void invokemarkVelocityChanged();

    @Invoker("shouldSetPosAfterLoading")
    boolean invokeshouldSetPosAfterLoading();

    @Invoker("readAdditional")
    void invokereadAdditional(CompoundNBT compound);

    @Invoker("writeAdditional")
    void invokewriteAdditional(CompoundNBT compound);

    @Invoker("canBeRidden")
    boolean invokecanBeRidden(Entity entityIn);

    @Invoker("isPoseClear")
    boolean invokeisPoseClear(Pose pose);

    @Invoker("addPassenger")
    void invokeaddPassenger(Entity passenger);

    @Invoker("removePassenger")
    void invokeremovePassenger(Entity passenger);

    @Invoker("canFitPassenger")
    boolean invokecanFitPassenger(Entity passenger);

    @Invoker("updatePortal")
    void invokeupdatePortal();

    @Invoker("getFlag")
    boolean invokegetFlag(int flag);

    @Invoker("setFlag")
    void invokesetFlag(int flag, boolean set);

    @Invoker("pushOutOfBlocks")
    void invokepushOutOfBlocks(double x, double y, double z);

    @Invoker("setDead")
    void invokesetDead();

    @Invoker("getEyeHeight")
    float invokegetEyeHeight(Pose poseIn, EntitySize sizeIn);

    @Invoker("getFireImmuneTicks")
    int invokegetFireImmuneTicks();

    @Invoker("getPermissionLevel")
    int invokegetPermissionLevel();
}