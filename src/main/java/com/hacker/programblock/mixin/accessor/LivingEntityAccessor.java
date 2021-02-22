package com.hacker.programblock.mixin.accessor;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(LivingEntity.class)
@SuppressWarnings("unused")
public interface LivingEntityAccessor {
    @Invoker("registerData")
    void invokeregisterData();

    @Invoker("updateFallState")
    void invokeupdateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos);

    @Invoker("addSprintingEffect")
    void invokeaddSprintingEffect();

    @Invoker("func_230296_cM_")
    boolean invokefunc_230296_cM_();

    @Invoker("getSpeedFactor")
    float invokegetSpeedFactor();

    @Invoker("func_230295_b_")
    boolean invokefunc_230295_b_(BlockState p_230295_1_);

    @Invoker("func_233641_cN_")
    void invokefunc_233641_cN_();

    @Invoker("func_233642_cO_")
    void invokefunc_233642_cO_();

    @Invoker("frostWalk")
    void invokefrostWalk(BlockPos pos);

    @Invoker("func_241208_cS_")
    boolean invokefunc_241208_cS_();

    @Invoker("onDeathUpdate")
    void invokeonDeathUpdate();

    @Invoker("canDropLoot")
    boolean invokecanDropLoot();

    @Invoker("func_230282_cS_")
    boolean invokefunc_230282_cS_();

    @Invoker("decreaseAirSupply")
    int invokedecreaseAirSupply(int air);

    @Invoker("determineNextAir")
    int invokedetermineNextAir(int currentAir);

    @Invoker("getExperiencePoints")
    int invokegetExperiencePoints(PlayerEntity player);

    @Invoker("isPlayer")
    boolean invokeisPlayer();

    @Invoker("playEquipSound")
    void invokeplayEquipSound(ItemStack stack);

    @Invoker("updatePotionEffects")
    void invokeupdatePotionEffects();

    @Invoker("updatePotionMetadata")
    void invokeupdatePotionMetadata();

    @Invoker("resetPotionEffectMetadata")
    void invokeresetPotionEffectMetadata();

    @Invoker("onNewPotionEffect")
    void invokeonNewPotionEffect(EffectInstance id);

    @Invoker("onChangedPotionEffect")
    void invokeonChangedPotionEffect(EffectInstance id, boolean reapply);

    @Invoker("onFinishedPotionEffect")
    void invokeonFinishedPotionEffect(EffectInstance effect);

    @Invoker("blockUsingShield")
    void invokeblockUsingShield(LivingEntity entityIn);

    @Invoker("constructKnockBackVector")
    void invokeconstructKnockBackVector(LivingEntity entityIn);

    @Invoker("playHurtSound")
    void invokeplayHurtSound(DamageSource source);

    @Invoker("createWitherRose")
    void invokecreateWitherRose(@Nullable LivingEntity entitySource);

    @Invoker("spawnDrops")
    void invokespawnDrops(DamageSource damageSourceIn);

    @Invoker("dropInventory")
    void invokedropInventory();

    @Invoker("dropExperience")
    void invokedropExperience();

    @Invoker("dropSpecialItems")
    void invokedropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn);

    @Invoker("dropLoot")
    void invokedropLoot(DamageSource damageSourceIn, boolean attackedRecently);

    @Invoker("calculateFallDamage")
    int invokecalculateFallDamage(float distance, float damageMultiplier);

    @Invoker("playFallSound")
    void invokeplayFallSound();

    @Invoker("damageArmor")
    void invokedamageArmor(DamageSource damageSource, float damage);

    @Invoker("damageShield")
    void invokedamageShield(float damage);

    @Invoker("applyArmorCalculations")
    float invokeapplyArmorCalculations(DamageSource source, float damage);

    @Invoker("applyPotionDamageCalculations")
    float invokeapplyPotionDamageCalculations(DamageSource source, float damage);

    @Invoker("damageEntity")
    void invokedamageEntity(DamageSource damageSrc, float damageAmount);

    @Invoker("outOfWorld")
    void invokeoutOfWorld();

    @Invoker("updateArmSwingProgress")
    void invokeupdateArmSwingProgress();

    @Invoker("getSoundVolume")
    float invokegetSoundVolume();

    @Invoker("getSoundPitch")
    float invokegetSoundPitch();

    @Invoker("isMovementBlocked")
    boolean invokeisMovementBlocked();

    @Invoker("getJumpUpwardsMotion")
    float invokegetJumpUpwardsMotion();

    @Invoker("jump")
    void invokejump();

    @Invoker("handleFluidSneak")
    void invokehandleFluidSneak();

    @Invoker("handleFluidJump")
    void invokehandleFluidJump(ITag<Fluid> fluidTag);

    @Invoker("getWaterSlowDown")
    float invokegetWaterSlowDown();

    @Invoker("updateDistance")
    float invokeupdateDistance(float p_110146_1_, float p_110146_2_);

    @Invoker("updateEntityActionState")
    void invokeupdateEntityActionState();

    @Invoker("collideWithNearbyEntities")
    void invokecollideWithNearbyEntities();

    @Invoker("updateSpinAttack")
    void invokeupdateSpinAttack(AxisAlignedBB p_204801_1_, AxisAlignedBB p_204801_2_);

    @Invoker("collideWithEntity")
    void invokecollideWithEntity(Entity entityIn);

    @Invoker("spinAttack")
    void invokespinAttack(LivingEntity p_204804_1_);

    @Invoker("markVelocityChanged")
    void invokemarkVelocityChanged();

    @Invoker("markPotionsDirty")
    void invokemarkPotionsDirty();

    @Invoker("setLivingFlag")
    void invokesetLivingFlag(int key, boolean value);

    @Invoker("triggerItemUseEffects")
    void invoketriggerItemUseEffects(ItemStack stack, int count);

    @Invoker("onItemUseFinish")
    void invokeonItemUseFinish();

    @Invoker("getEyeHeight")
    float invokegetEyeHeight(Pose poseIn, EntitySize sizeIn);

    @Invoker("getStandingEyeHeight")
    float invokegetStandingEyeHeight(Pose poseIn, EntitySize sizeIn);

    @Invoker(value = "invalidateCaps", remap = false)
    void invokeinvalidateCaps();
}