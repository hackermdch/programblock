package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.LivingEntityAccessor;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Predicate;

@SuppressWarnings("all")
public class LivingEntity extends Entity {
    private final net.minecraft.entity.LivingEntity target;

    public LivingEntity(@Nonnull net.minecraft.entity.LivingEntity target) {
        super(target);
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.entity.LivingEntity getTarget() {
        return target;
    }

    public void onKillCommand() {
        target.onKillCommand();
    }

    public boolean canAttack(EntityType<?> typeIn) {
        return target.canAttack(typeIn);
    }

    protected void registerData() {
        ((LivingEntityAccessor) target).invokeregisterData();
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        ((LivingEntityAccessor) target).invokeupdateFallState(y, onGroundIn, state.getTarget(), pos.getTarget());
    }

    public boolean canBreatheUnderwater() {
        return target.canBreatheUnderwater();
    }

    public float getSwimAnimation(float partialTicks) {
        return target.getSwimAnimation(partialTicks);
    }

    public void baseTick() {
        target.baseTick();
    }

    public boolean getMovementSpeed() {
        return target.getMovementSpeed();
    }

    protected void addSprintingEffect() {
        ((LivingEntityAccessor) target).invokeaddSprintingEffect();
    }

    protected boolean func_230296_cM_() {
        return ((LivingEntityAccessor) target).invokefunc_230296_cM_();
    }

    protected float getSpeedFactor() {
        return ((LivingEntityAccessor) target).invokegetSpeedFactor();
    }

    protected boolean func_230295_b_(BlockState p_230295_1_) {
        return ((LivingEntityAccessor) target).invokefunc_230295_b_(p_230295_1_.getTarget());
    }

    protected void func_233641_cN_() {
        ((LivingEntityAccessor) target).invokefunc_233641_cN_();
    }

    protected void func_233642_cO_() {
        ((LivingEntityAccessor) target).invokefunc_233642_cO_();
    }

    protected void frostWalk(BlockPos pos) {
        ((LivingEntityAccessor) target).invokefrostWalk(pos.getTarget());
    }

    public boolean isChild() {
        return target.isChild();
    }

    public float getRenderScale() {
        return target.getRenderScale();
    }

    protected boolean func_241208_cS_() {
        return ((LivingEntityAccessor) target).invokefunc_241208_cS_();
    }

    public boolean canBeRiddenInWater() {
        return target.canBeRiddenInWater();
    }

    protected void onDeathUpdate() {
        ((LivingEntityAccessor) target).invokeonDeathUpdate();
    }

    protected boolean canDropLoot() {
        return ((LivingEntityAccessor) target).invokecanDropLoot();
    }

    protected boolean func_230282_cS_() {
        return ((LivingEntityAccessor) target).invokefunc_230282_cS_();
    }

    protected int decreaseAirSupply(int air) {
        return ((LivingEntityAccessor) target).invokedecreaseAirSupply(air);
    }

    protected int determineNextAir(int currentAir) {
        return ((LivingEntityAccessor) target).invokedetermineNextAir(currentAir);
    }

    protected int getExperiencePoints(PlayerEntity player) {
        return ((LivingEntityAccessor) target).invokegetExperiencePoints(player);
    }

    protected boolean isPlayer() {
        return ((LivingEntityAccessor) target).invokeisPlayer();
    }

    public int getRevengeTimer() {
        return target.getRevengeTimer();
    }

    public void func_230246_e_(@Nullable PlayerEntity p_230246_1_) {
        target.func_230246_e_(p_230246_1_);
    }

    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        target.setRevengeTarget(livingBase.target);
    }

    public int getLastAttackedEntityTime() {
        return target.getLastAttackedEntityTime();
    }

    public void setLastAttackedEntity(Entity entityIn) {
        target.setLastAttackedEntity(entityIn.getTarget());
    }

    public int getIdleTime() {
        return target.getIdleTime();
    }

    public void setIdleTime(int idleTimeIn) {
        target.setIdleTime(idleTimeIn);
    }

    protected void playEquipSound(ItemStack stack) {
        ((LivingEntityAccessor) target).invokeplayEquipSound(stack);
    }

    public void writeAdditional(CompoundNBT compound) {
        target.writeAdditional(compound);
    }

    public void readAdditional(CompoundNBT compound) {
        target.readAdditional(compound);
    }

    protected void updatePotionEffects() {
        ((LivingEntityAccessor) target).invokeupdatePotionEffects();
    }

    protected void updatePotionMetadata() {
        ((LivingEntityAccessor) target).invokeupdatePotionMetadata();
    }

    public double getVisibilityMultiplier(@Nullable Entity lookingEntity) {
        return target.getVisibilityMultiplier(lookingEntity.getTarget());
    }

    public boolean canAttack(LivingEntity target) {
        return this.target.canAttack(target.target);
    }

    public boolean canAttack(LivingEntity livingentityIn, EntityPredicate predicateIn) {
        return target.canAttack(livingentityIn.target, predicateIn);
    }

    protected void resetPotionEffectMetadata() {
        ((LivingEntityAccessor) target).invokeresetPotionEffectMetadata();
    }

    public boolean clearActivePotions() {
        return target.clearActivePotions();
    }

    public boolean isPotionActive(Effect potionIn) {
        return target.isPotionActive(potionIn);
    }

    public boolean addPotionEffect(EffectInstance effectInstanceIn) {
        return target.addPotionEffect(effectInstanceIn);
    }

    public boolean isPotionApplicable(EffectInstance potioneffectIn) {
        return target.isPotionApplicable(potioneffectIn);
    }

    public void func_233646_e_(EffectInstance p_233646_1_) {
        target.func_233646_e_(p_233646_1_);
    }

    public boolean isEntityUndead() {
        return target.isEntityUndead();
    }

    public boolean removePotionEffect(Effect effectIn) {
        return target.removePotionEffect(effectIn);
    }

    protected void onNewPotionEffect(EffectInstance id) {
        ((LivingEntityAccessor) target).invokeonNewPotionEffect(id);
    }

    protected void onChangedPotionEffect(EffectInstance id, boolean reapply) {
        ((LivingEntityAccessor) target).invokeonChangedPotionEffect(id, reapply);
    }

    protected void onFinishedPotionEffect(EffectInstance effect) {
        ((LivingEntityAccessor) target).invokeonFinishedPotionEffect(effect);
    }

    public void heal(float healAmount) {
        target.heal(healAmount);
    }

    public float getHealth() {
        return target.getHealth();
    }

    public void setHealth(float health) {
        target.setHealth(health);
    }

    public boolean getShouldBeDead() {
        return target.getShouldBeDead();
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return target.attackEntityFrom(source, amount);
    }

    protected void blockUsingShield(LivingEntity entityIn) {
        ((LivingEntityAccessor) target).invokeblockUsingShield(entityIn.target);
    }

    protected void constructKnockBackVector(LivingEntity entityIn) {
        ((LivingEntityAccessor) target).invokeconstructKnockBackVector(entityIn.target);
    }

    protected void playHurtSound(DamageSource source) {
        ((LivingEntityAccessor) target).invokeplayHurtSound(source);
    }

    public void onDeath(DamageSource cause) {
        target.onDeath(cause);
    }

    protected void createWitherRose(@Nullable LivingEntity entitySource) {
        ((LivingEntityAccessor) target).invokecreateWitherRose(entitySource.target);
    }

    protected void spawnDrops(DamageSource damageSourceIn) {
        ((LivingEntityAccessor) target).invokespawnDrops(damageSourceIn);
    }

    protected void dropInventory() {
        ((LivingEntityAccessor) target).invokedropInventory();
    }

    protected void dropExperience() {
        ((LivingEntityAccessor) target).invokedropExperience();
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        ((LivingEntityAccessor) target).invokedropSpecialItems(source, looting, recentlyHitIn);
    }

    protected void dropLoot(DamageSource damageSourceIn, boolean attackedRecently) {
        ((LivingEntityAccessor) target).invokedropLoot(damageSourceIn, attackedRecently);
    }

    public void applyKnockback(float strength, double ratioX, double ratioZ) {
        target.applyKnockback(strength, ratioX, ratioZ);
    }

    public void setOnGround(boolean grounded) {
        target.setOnGround(grounded);
    }

    public boolean isOnLadder() {
        return target.isOnLadder();
    }

    public boolean isAlive() {
        return target.isAlive();
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return target.onLivingFall(distance, damageMultiplier);
    }

    protected int calculateFallDamage(float distance, float damageMultiplier) {
        return ((LivingEntityAccessor) target).invokecalculateFallDamage(distance, damageMultiplier);
    }

    protected void playFallSound() {
        ((LivingEntityAccessor) target).invokeplayFallSound();
    }

    public void performHurtAnimation() {
        target.performHurtAnimation();
    }

    public int getTotalArmorValue() {
        return target.getTotalArmorValue();
    }

    protected void damageArmor(DamageSource damageSource, float damage) {
        ((LivingEntityAccessor) target).invokedamageArmor(damageSource, damage);
    }

    protected void damageShield(float damage) {
        ((LivingEntityAccessor) target).invokedamageShield(damage);
    }

    protected float applyArmorCalculations(DamageSource source, float damage) {
        return ((LivingEntityAccessor) target).invokeapplyArmorCalculations(source, damage);
    }

    protected float applyPotionDamageCalculations(DamageSource source, float damage) {
        return ((LivingEntityAccessor) target).invokeapplyPotionDamageCalculations(source, damage);
    }

    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        ((LivingEntityAccessor) target).invokedamageEntity(damageSrc, damageAmount);
    }

    public final float getMaxHealth() {
        return target.getMaxHealth();
    }

    public final int getArrowCountInEntity() {
        return target.getArrowCountInEntity();
    }

    public final void setArrowCountInEntity(int count) {
        target.setArrowCountInEntity(count);
    }

    public final int getBeeStingCount() {
        return target.getBeeStingCount();
    }

    public final void setBeeStingCount(int p_226300_1_) {
        target.setBeeStingCount(p_226300_1_);
    }

    public void swingArm(Hand hand) {
        target.swingArm(hand);
    }

    public void swing(Hand handIn, boolean updateSelf) {
        target.swing(handIn, updateSelf);
    }

    public void handleStatusUpdate(byte id) {
        target.handleStatusUpdate(id);
    }

    protected void outOfWorld() {
        ((LivingEntityAccessor) target).invokeoutOfWorld();
    }

    protected void updateArmSwingProgress() {
        ((LivingEntityAccessor) target).invokeupdateArmSwingProgress();
    }

    public double getAttributeValue(Attribute attribute) {
        return target.getAttributeValue(attribute);
    }

    public double getBaseAttributeValue(Attribute attribute) {
        return target.getBaseAttributeValue(attribute);
    }

    public boolean canEquip(Item item) {
        return target.canEquip(item);
    }

    public boolean func_233634_a_(Predicate<Item> p_233634_1_) {
        return target.func_233634_a_(p_233634_1_);
    }

    public void setHeldItem(Hand hand, ItemStack stack) {
        target.setHeldItem(hand, stack);
    }

    public boolean hasItemInSlot(EquipmentSlotType slotIn) {
        return target.hasItemInSlot(slotIn);
    }

    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
        target.setItemStackToSlot(slotIn, stack);
    }

    public float getArmorCoverPercentage() {
        return target.getArmorCoverPercentage();
    }

    public void setSprinting(boolean sprinting) {
        target.setSprinting(sprinting);
    }

    protected float getSoundVolume() {
        return ((LivingEntityAccessor) target).invokegetSoundVolume();
    }

    protected float getSoundPitch() {
        return ((LivingEntityAccessor) target).invokegetSoundPitch();
    }

    protected boolean isMovementBlocked() {
        return ((LivingEntityAccessor) target).invokeisMovementBlocked();
    }

    public void applyEntityCollision(Entity entityIn) {
        target.applyEntityCollision(entityIn.getTarget());
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return target.getAlwaysRenderNameTagForRender();
    }

    protected float getJumpUpwardsMotion() {
        return ((LivingEntityAccessor) target).invokegetJumpUpwardsMotion();
    }

    protected void jump() {
        ((LivingEntityAccessor) target).invokejump();
    }

    protected void handleFluidSneak() {
        ((LivingEntityAccessor) target).invokehandleFluidSneak();
    }

    protected void handleFluidJump(ITag<Fluid> fluidTag) {
        ((LivingEntityAccessor) target).invokehandleFluidJump(fluidTag);
    }

    protected float getWaterSlowDown() {
        return ((LivingEntityAccessor) target).invokegetWaterSlowDown();
    }

    public boolean func_230285_a_(Fluid p_230285_1_) {
        return target.func_230285_a_(p_230285_1_);
    }

    public void travel(Vector3d travelVector) {
        target.travel(travelVector);
    }

    public void func_233629_a_(LivingEntity p_233629_1_, boolean p_233629_2_) {
        target.func_233629_a_(p_233629_1_.target, p_233629_2_);
    }

    public float getAIMoveSpeed() {
        return target.getAIMoveSpeed();
    }

    public void setAIMoveSpeed(float speedIn) {
        target.setAIMoveSpeed(speedIn);
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        return target.attackEntityAsMob(entityIn.getTarget());
    }

    public void tick() {
        target.tick();
    }

    protected float updateDistance(float p_110146_1_, float p_110146_2_) {
        return ((LivingEntityAccessor) target).invokeupdateDistance(p_110146_1_, p_110146_2_);
    }

    public void livingTick() {
        target.livingTick();
    }

    public boolean isWaterSensitive() {
        return target.isWaterSensitive();
    }

    protected void updateEntityActionState() {
        ((LivingEntityAccessor) target).invokeupdateEntityActionState();
    }

    protected void collideWithNearbyEntities() {
        ((LivingEntityAccessor) target).invokecollideWithNearbyEntities();
    }

    protected void updateSpinAttack(AxisAlignedBB p_204801_1_, AxisAlignedBB p_204801_2_) {
        ((LivingEntityAccessor) target).invokeupdateSpinAttack(p_204801_1_, p_204801_2_);
    }

    protected void collideWithEntity(Entity entityIn) {
        ((LivingEntityAccessor) target).invokecollideWithEntity(entityIn.getTarget());
    }

    protected void spinAttack(LivingEntity p_204804_1_) {
        ((LivingEntityAccessor) target).invokespinAttack(p_204804_1_.target);
    }

    public void startSpinAttack(int p_204803_1_) {
        target.startSpinAttack(p_204803_1_);
    }

    public boolean isSpinAttacking() {
        return target.isSpinAttacking();
    }

    public void stopRiding() {
        target.stopRiding();
    }

    public void updateRidden() {
        target.updateRidden();
    }

    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        target.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
    }

    public void setHeadRotation(float yaw, int pitch) {
        target.setHeadRotation(yaw, pitch);
    }

    public void setJumping(boolean jumping) {
        target.setJumping(jumping);
    }

    public void triggerItemPickupTrigger(ItemEntity item) {
        target.triggerItemPickupTrigger(item);
    }

    public void onItemPickup(Entity entityIn, int quantity) {
        target.onItemPickup(entityIn.getTarget(), quantity);
    }

    public boolean canEntityBeSeen(Entity entityIn) {
        return target.canEntityBeSeen(entityIn.getTarget());
    }

    public float getYaw(float partialTicks) {
        return target.getYaw(partialTicks);
    }

    public float getSwingProgress(float partialTickTime) {
        return target.getSwingProgress(partialTickTime);
    }

    public boolean isServerWorld() {
        return target.isServerWorld();
    }

    public boolean canBeCollidedWith() {
        return target.canBeCollidedWith();
    }

    public boolean canBePushed() {
        return target.canBePushed();
    }

    protected void markVelocityChanged() {
        ((LivingEntityAccessor) target).invokemarkVelocityChanged();
    }

    public float getRotationYawHead() {
        return target.getRotationYawHead();
    }

    public void setRotationYawHead(float rotation) {
        target.setRotationYawHead(rotation);
    }

    public void setRenderYawOffset(float offset) {
        target.setRenderYawOffset(offset);
    }

    public float getAbsorptionAmount() {
        return target.getAbsorptionAmount();
    }

    public void setAbsorptionAmount(float amount) {
        target.setAbsorptionAmount(amount);
    }

    public void sendEnterCombat() {
        target.sendEnterCombat();
    }

    public void sendEndCombat() {
        target.sendEndCombat();
    }

    protected void markPotionsDirty() {
        ((LivingEntityAccessor) target).invokemarkPotionsDirty();
    }

    public boolean isHandActive() {
        return target.isHandActive();
    }

    protected void setLivingFlag(int key, boolean value) {
        ((LivingEntityAccessor) target).invokesetLivingFlag(key, value);
    }

    public void setActiveHand(Hand hand) {
        target.setActiveHand(hand);
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        target.notifyDataManagerChange(key);
    }

    public void lookAt(EntityAnchorArgument.Type anchor, Vector3d target) {
        this.target.lookAt(anchor, target);
    }

    protected void triggerItemUseEffects(ItemStack stack, int count) {
        ((LivingEntityAccessor) target).invoketriggerItemUseEffects(stack, count);
    }

    protected void onItemUseFinish() {
        ((LivingEntityAccessor) target).invokeonItemUseFinish();
    }

    public int getItemInUseCount() {
        return target.getItemInUseCount();
    }

    public int getItemInUseMaxCount() {
        return target.getItemInUseMaxCount();
    }

    public void stopActiveHand() {
        target.stopActiveHand();
    }

    public void resetActiveHand() {
        target.resetActiveHand();
    }

    public boolean isActiveItemStackBlocking() {
        return target.isActiveItemStackBlocking();
    }

    public boolean hasStoppedClimbing() {
        return target.hasStoppedClimbing();
    }

    public boolean isElytraFlying() {
        return target.isElytraFlying();
    }

    public boolean isActualySwimming() {
        return target.isActualySwimming();
    }

    public int getTicksElytraFlying() {
        return target.getTicksElytraFlying();
    }

    public boolean attemptTeleport(double x, double y, double z, boolean p_213373_7_) {
        return target.attemptTeleport(x, y, z, p_213373_7_);
    }

    public boolean canBeHitWithPotion() {
        return target.canBeHitWithPotion();
    }

    public boolean attackable() {
        return target.attackable();
    }

    public void setPartying(BlockPos pos, boolean isPartying) {
        target.setPartying(pos.getTarget(), isPartying);
    }

    public boolean canPickUpItem(ItemStack itemstackIn) {
        return target.canPickUpItem(itemstackIn);
    }

    public void setBedPosition(BlockPos p_213369_1_) {
        target.setBedPosition(p_213369_1_.getTarget());
    }

    public void clearBedPosition() {
        target.clearBedPosition();
    }

    public boolean isSleeping() {
        return target.isSleeping();
    }

    public void startSleeping(BlockPos pos) {
        target.startSleeping(pos.getTarget());
    }

    public void wakeUp() {
        target.wakeUp();
    }

    public boolean isEntityInsideOpaqueBlock() {
        return target.isEntityInsideOpaqueBlock();
    }

    protected final float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return ((LivingEntityAccessor) target).invokegetEyeHeight(poseIn, sizeIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return ((LivingEntityAccessor) target).invokegetStandingEyeHeight(poseIn, sizeIn);
    }

    public void sendBreakAnimation(EquipmentSlotType p_213361_1_) {
        target.sendBreakAnimation(p_213361_1_);
    }

    public void sendBreakAnimation(Hand p_213334_1_) {
        target.sendBreakAnimation(p_213334_1_);
    }

    public boolean curePotionEffects(ItemStack curativeItem) {
        return target.curePotionEffects(curativeItem);
    }

    public boolean shouldRiderFaceForward(PlayerEntity player) {
        return target.shouldRiderFaceForward(player);
    }

    protected void invalidateCaps() {
        ((LivingEntityAccessor) target).invokeinvalidateCaps();
    }
}
