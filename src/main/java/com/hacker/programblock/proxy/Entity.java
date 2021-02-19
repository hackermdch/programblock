package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.EntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.scoreboard.Team;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;

@SuppressWarnings("unused")
public class Entity {
    private final net.minecraft.entity.Entity target;

    public Entity(net.minecraft.entity.Entity target) {
        this.target = target;
    }

    public boolean func_242278_a(BlockPos p_242278_1_, BlockState p_242278_2_) {
        return target.func_242278_a(p_242278_1_, p_242278_2_);
    }

    public int getTeamColor() {
        return target.getTeamColor();
    }

    public boolean isSpectator() {
        return target.isSpectator();
    }

    public final void detach() {
        target.detach();
    }

    public void setPacketCoordinates(double x, double y, double z) {
        target.setPacketCoordinates(x, y, z);
    }

    public void func_242277_a(Vector3d p_242277_1_) {
        target.func_242277_a(p_242277_1_);
    }

    public int getEntityId() {
        return target.getEntityId();
    }

    public void setEntityId(int id) {
        target.setEntityId(id);
    }

    public boolean addTag(String tag) {
        return target.addTag(tag);
    }

    public boolean removeTag(String tag) {
        return target.removeTag(tag);
    }

    public void onKillCommand() {
        target.onKillCommand();
    }

    protected void registerData() {
        ((EntityAccessor) target).invokeregisterData();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object p_equals_1_) {
        return target.equals(p_equals_1_);
    }

    public int hashCode() {
        return target.hashCode();
    }

    protected void preparePlayerToSpawn() {
        ((EntityAccessor) target).invokepreparePlayerToSpawn();
    }

    public void remove() {
        target.remove();
    }

    public void remove(boolean keepData) {
        target.remove(keepData);
    }

    public void setPose(Pose poseIn) {
        target.setPose(poseIn);
    }

    public boolean isEntityInRange(Entity entity, double distance) {
        return target.isEntityInRange(entity.target, distance);
    }

    protected void setRotation(float yaw, float pitch) {
        ((EntityAccessor) target).invokesetRotation(yaw, pitch);
    }

    public void setPosition(double x, double y, double z) {
        target.setPosition(x, y, z);
    }

    protected void recenterBoundingBox() {
        ((EntityAccessor) target).invokerecenterBoundingBox();
    }

    public void rotateTowards(double yaw, double pitch) {
        target.rotateTowards(yaw, pitch);
    }

    public void tick() {
        target.tick();
    }

    public void baseTick() {
        target.baseTick();
    }

    public void func_242279_ag() {
        target.func_242279_ag();
    }

    public boolean func_242280_ah() {
        return target.func_242280_ah();
    }

    protected void decrementTimeUntilPortal() {
        ((EntityAccessor) target).invokedecrementTimeUntilPortal();
    }

    public int getMaxInPortalTime() {
        return target.getMaxInPortalTime();
    }

    protected void setOnFireFromLava() {
        ((EntityAccessor) target).invokesetOnFireFromLava();
    }

    public void setFire(int seconds) {
        target.setFire(seconds);
    }

    public void forceFireTicks(int ticks) {
        target.forceFireTicks(ticks);
    }

    public int getFireTimer() {
        return target.getFireTimer();
    }

    public void extinguish() {
        target.extinguish();
    }

    protected void outOfWorld() {
        ((EntityAccessor) target).invokeoutOfWorld();
    }

    public boolean isOffsetPositionInLiquid(double x, double y, double z) {
        return target.isOffsetPositionInLiquid(x, y, z);
    }

    public void setOnGround(boolean grounded) {
        target.setOnGround(grounded);
    }

    public boolean isOnGround() {
        return target.isOnGround();
    }

    public void move(MoverType typeIn, Vector3d pos) {
        target.move(typeIn, pos);
    }

    protected float getJumpFactor() {
        return ((EntityAccessor) target).invokegetJumpFactor();
    }

    protected float getSpeedFactor() {
        return ((EntityAccessor) target).invokegetSpeedFactor();
    }

    protected float determineNextStepDistance() {
        return ((EntityAccessor) target).invokedetermineNextStepDistance();
    }

    public void resetPositionToBB() {
        target.resetPositionToBB();
    }

    protected void doBlockCollisions() {
        ((EntityAccessor) target).invokedoBlockCollisions();
    }

    protected void onInsideBlock(BlockState state) {
        ((EntityAccessor) target).invokeonInsideBlock(state);
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        ((EntityAccessor) target).invokeplayStepSound(pos, blockIn);
    }

    protected void playSwimSound(float volume) {
        ((EntityAccessor) target).invokeplaySwimSound(volume);
    }

    protected float playFlySound(float volume) {
        return ((EntityAccessor) target).invokeplayFlySound(volume);
    }

    protected boolean makeFlySound() {
        return ((EntityAccessor) target).invokemakeFlySound();
    }

    public void playSound(SoundEvent soundIn, float volume, float pitch) {
        target.playSound(soundIn, volume, pitch);
    }

    public boolean isSilent() {
        return target.isSilent();
    }

    public void setSilent(boolean isSilent) {
        target.setSilent(isSilent);
    }

    public boolean hasNoGravity() {
        return target.hasNoGravity();
    }

    public void setNoGravity(boolean noGravity) {
        target.setNoGravity(noGravity);
    }

    protected boolean canTriggerWalking() {
        return ((EntityAccessor) target).invokecanTriggerWalking();
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        ((EntityAccessor) target).invokeupdateFallState(y, onGroundIn, state, pos);
    }

    public boolean isImmuneToFire() {
        return target.isImmuneToFire();
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return target.onLivingFall(distance, damageMultiplier);
    }

    public boolean isInWater() {
        return target.isInWater();
    }

    public boolean isWet() {
        return target.isWet();
    }

    public boolean isInWaterRainOrBubbleColumn() {
        return target.isInWaterRainOrBubbleColumn();
    }

    public boolean isInWaterOrBubbleColumn() {
        return target.isInWaterOrBubbleColumn();
    }

    public boolean canSwim() {
        return target.canSwim();
    }

    public void updateSwimming() {
        target.updateSwimming();
    }

    protected boolean func_233566_aG_() {
        return ((EntityAccessor) target).invokefunc_233566_aG_();
    }

    void updateWaterState() {
        ((EntityAccessor) target).invokeupdateWaterState();
    }

    protected void doWaterSplashEffect() {
        ((EntityAccessor) target).invokedoWaterSplashEffect();
    }

    public boolean shouldSpawnRunningEffects() {
        return target.shouldSpawnRunningEffects();
    }

    protected void handleRunningEffect() {
        ((EntityAccessor) target).invokehandleRunningEffect();
    }

    public boolean areEyesInFluid(ITag<Fluid> tagIn) {
        return target.areEyesInFluid(tagIn);
    }

    public boolean isInLava() {
        return target.isInLava();
    }

    public void moveRelative(float p_213309_1_, Vector3d relative) {
        target.moveRelative(p_213309_1_, relative);
    }

    public float getBrightness() {
        return target.getBrightness();
    }

    public void setWorld(World worldIn) {
        target.setWorld(worldIn);
    }

    public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
        target.setPositionAndRotation(x, y, z, yaw, pitch);
    }

    public void func_242281_f(double p_242281_1_, double p_242281_3_, double p_242281_5_) {
        target.func_242281_f(p_242281_1_, p_242281_3_, p_242281_5_);
    }

    public void moveForced(Vector3d vec) {
        target.moveForced(vec);
    }

    public void moveForced(double x, double y, double z) {
        target.moveForced(x, y, z);
    }

    public void moveToBlockPosAndAngles(BlockPos pos, float rotationYawIn, float rotationPitchIn) {
        target.moveToBlockPosAndAngles(pos, rotationYawIn, rotationPitchIn);
    }

    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
        target.setLocationAndAngles(x, y, z, yaw, pitch);
    }

    public void forceSetPosition(double x, double y, double z) {
        target.forceSetPosition(x, y, z);
    }

    public float getDistance(Entity entityIn) {
        return target.getDistance(entityIn.target);
    }

    public double getDistanceSq(double x, double y, double z) {
        return target.getDistanceSq(x, y, z);
    }

    public double getDistanceSq(Entity entityIn) {
        return target.getDistanceSq(entityIn.target);
    }

    public double getDistanceSq(Vector3d vec) {
        return target.getDistanceSq(vec);
    }

    public void onCollideWithPlayer(PlayerEntity entityIn) {
        target.onCollideWithPlayer(entityIn);
    }

    public void applyEntityCollision(Entity entityIn) {
        target.applyEntityCollision(entityIn.target);
    }

    public void addVelocity(double x, double y, double z) {
        target.addVelocity(x, y, z);
    }

    protected void markVelocityChanged() {
        ((EntityAccessor) target).invokemarkVelocityChanged();
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return target.attackEntityFrom(source, amount);
    }

    public float getPitch(float partialTicks) {
        return target.getPitch(partialTicks);
    }

    public float getYaw(float partialTicks) {
        return target.getYaw(partialTicks);
    }

    public boolean canBeCollidedWith() {
        return target.canBeCollidedWith();
    }

    public boolean canBePushed() {
        return target.canBePushed();
    }

    public void awardKillScore(Entity killed, int scoreValue, DamageSource damageSource) {
        target.awardKillScore(killed.target, scoreValue, damageSource);
    }

    public boolean isInRangeToRender3d(double x, double y, double z) {
        return target.isInRangeToRender3d(x, y, z);
    }

    public boolean isInRangeToRenderDist(double distance) {
        return target.isInRangeToRenderDist(distance);
    }

    public boolean writeUnlessRemoved(CompoundNBT compound) {
        return target.writeUnlessRemoved(compound);
    }

    public boolean writeUnlessPassenger(CompoundNBT compound) {
        return target.writeUnlessPassenger(compound);
    }

    public void read(CompoundNBT compound) {
        target.read(compound);
    }

    protected boolean shouldSetPosAfterLoading() {
        return ((EntityAccessor) target).invokeshouldSetPosAfterLoading();
    }

    protected void readAdditional(CompoundNBT compound) {
        ((EntityAccessor) target).invokereadAdditional(compound);
    }

    protected void writeAdditional(CompoundNBT compound) {
        ((EntityAccessor) target).invokewriteAdditional(compound);
    }

    public boolean isAlive() {
        return target.isAlive();
    }

    public boolean isEntityInsideOpaqueBlock() {
        return target.isEntityInsideOpaqueBlock();
    }

    public boolean canCollide(Entity entity) {
        return target.canCollide(entity.target);
    }

    public boolean func_241845_aY() {
        return target.func_241845_aY();
    }

    public void updateRidden() {
        target.updateRidden();
    }

    public void updatePassenger(Entity passenger) {
        target.updatePassenger(passenger.target);
    }

    public void applyOrientationToEntity(Entity entityToUpdate) {
        target.applyOrientationToEntity(entityToUpdate.target);
    }

    public double getYOffset() {
        return target.getYOffset();
    }

    public double getMountedYOffset() {
        return target.getMountedYOffset();
    }

    public boolean startRiding(Entity entityIn) {
        return target.startRiding(entityIn.target);
    }

    public boolean isLiving() {
        return target.isLiving();
    }

    public boolean startRiding(Entity entityIn, boolean force) {
        return target.startRiding(entityIn.target, force);
    }

    protected boolean canBeRidden(Entity entityIn) {
        return ((EntityAccessor) target).invokecanBeRidden(entityIn.target);
    }

    protected boolean isPoseClear(Pose pose) {
        return ((EntityAccessor) target).invokeisPoseClear(pose);
    }

    public void removePassengers() {
        target.removePassengers();
    }

    public void dismount() {
        target.dismount();
    }

    public void stopRiding() {
        target.stopRiding();
    }

    protected void addPassenger(Entity passenger) {
        ((EntityAccessor) target).invokeaddPassenger(passenger.target);
    }

    protected void removePassenger(Entity passenger) {
        ((EntityAccessor) target).invokeremovePassenger(passenger.target);
    }

    protected boolean canFitPassenger(Entity passenger) {
        return ((EntityAccessor) target).invokecanFitPassenger(passenger.target);
    }

    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        target.setPositionAndRotationDirect(x, y, z, yaw, pitch, posRotationIncrements, teleport);
    }

    public void setHeadRotation(float yaw, int pitch) {
        target.setHeadRotation(yaw, pitch);
    }

    public float getCollisionBorderSize() {
        return target.getCollisionBorderSize();
    }

    public void setPortal(BlockPos pos) {
        target.setPortal(pos);
    }

    protected void updatePortal() {
        ((EntityAccessor) target).invokeupdatePortal();
    }

    public int getPortalCooldown() {
        return target.getPortalCooldown();
    }

    public void setVelocity(double x, double y, double z) {
        target.setVelocity(x, y, z);
    }

    public void handleStatusUpdate(byte id) {
        target.handleStatusUpdate(id);
    }

    public void performHurtAnimation() {
        target.performHurtAnimation();
    }

    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
        target.setItemStackToSlot(slotIn, stack);
    }

    public boolean isBurning() {
        return target.isBurning();
    }

    public boolean isPassenger() {
        return target.isPassenger();
    }

    public boolean isBeingRidden() {
        return target.isBeingRidden();
    }

    @SuppressWarnings("deprecation")
    public boolean canBeRiddenInWater() {
        return target.canBeRiddenInWater();
    }

    public void setSneaking(boolean keyDownIn) {
        target.setSneaking(keyDownIn);
    }

    public boolean isSneaking() {
        return target.isSneaking();
    }

    public boolean isSteppingCarefully() {
        return target.isSteppingCarefully();
    }

    public boolean isSuppressingBounce() {
        return target.isSuppressingBounce();
    }

    public boolean isDiscrete() {
        return target.isDiscrete();
    }

    public boolean isDescending() {
        return target.isDescending();
    }

    public boolean isCrouching() {
        return target.isCrouching();
    }

    public boolean isSprinting() {
        return target.isSprinting();
    }

    public void setSprinting(boolean sprinting) {
        target.setSprinting(sprinting);
    }

    public boolean isSwimming() {
        return target.isSwimming();
    }

    public boolean isActualySwimming() {
        return target.isActualySwimming();
    }

    public boolean isVisuallySwimming() {
        return target.isVisuallySwimming();
    }

    public void setSwimming(boolean swimming) {
        target.setSwimming(swimming);
    }

    public boolean isGlowing() {
        return target.isGlowing();
    }

    public void setGlowing(boolean glowingIn) {
        target.setGlowing(glowingIn);
    }

    public boolean isInvisible() {
        return target.isInvisible();
    }

    public boolean isInvisibleToPlayer(PlayerEntity player) {
        return target.isInvisibleToPlayer(player);
    }

    public boolean isOnSameTeam(Entity entityIn) {
        return target.isOnSameTeam(entityIn.target);
    }

    public boolean isOnScoreboardTeam(Team teamIn) {
        return target.isOnScoreboardTeam(teamIn);
    }

    public void setInvisible(boolean invisible) {
        target.setInvisible(invisible);
    }

    protected boolean getFlag(int flag) {
        return ((EntityAccessor) target).invokegetFlag(flag);
    }

    protected void setFlag(int flag, boolean set) {
        ((EntityAccessor) target).invokesetFlag(flag, set);
    }

    public int getMaxAir() {
        return target.getMaxAir();
    }

    public int getAir() {
        return target.getAir();
    }

    public void setAir(int air) {
        target.setAir(air);
    }

    public void func_241841_a(ServerWorld p_241841_1_, LightningBoltEntity p_241841_2_) {
        target.func_241841_a(p_241841_1_, p_241841_2_);
    }

    public void onEnterBubbleColumnWithAirAbove(boolean downwards) {
        target.onEnterBubbleColumnWithAirAbove(downwards);
    }

    public void onEnterBubbleColumn(boolean downwards) {
        target.onEnterBubbleColumn(downwards);
    }

    public void func_241847_a(ServerWorld p_241847_1_, LivingEntity p_241847_2_) {
        target.func_241847_a(p_241847_1_, p_241847_2_);
    }

    protected void pushOutOfBlocks(double x, double y, double z) {
        ((EntityAccessor) target).invokepushOutOfBlocks(x, y, z);
    }

    public void setMotionMultiplier(BlockState state, Vector3d motionMultiplierIn) {
        target.setMotionMultiplier(state, motionMultiplierIn);
    }

    public boolean isEntityEqual(Entity entityIn) {
        return target.isEntityEqual(entityIn.target);
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

    public boolean canBeAttackedWithItem() {
        return target.canBeAttackedWithItem();
    }

    public boolean hitByEntity(Entity entityIn) {
        return target.hitByEntity(entityIn.target);
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return target.isInvulnerableTo(source);
    }

    public boolean isInvulnerable() {
        return target.isInvulnerable();
    }

    public void setInvulnerable(boolean isInvulnerable) {
        target.setInvulnerable(isInvulnerable);
    }

    public void copyLocationAndAnglesFrom(Entity entityIn) {
        target.copyLocationAndAnglesFrom(entityIn.target);
    }

    public void copyDataFromOld(Entity entityIn) {
        target.copyDataFromOld(entityIn.target);
    }

    protected void setDead() {
        ((EntityAccessor) target).invokesetDead();
    }

    public boolean isNonBoss() {
        return target.isNonBoss();
    }

    public float getExplosionResistance(Explosion explosionIn, IBlockReader worldIn, BlockPos pos, BlockState blockStateIn, FluidState fluidState, float explosionPower) {
        return target.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn, fluidState, explosionPower);
    }

    public boolean canExplosionDestroyBlock(Explosion explosionIn, IBlockReader worldIn, BlockPos pos, BlockState blockStateIn, float explosionPower) {
        return target.canExplosionDestroyBlock(explosionIn, worldIn, pos, blockStateIn, explosionPower);
    }

    public int getMaxFallHeight() {
        return target.getMaxFallHeight();
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return target.doesEntityNotTriggerPressurePlate();
    }

    public void fillCrashReport(CrashReportCategory category) {
        target.fillCrashReport(category);
    }

    public boolean canRenderOnFire() {
        return target.canRenderOnFire();
    }

    public void setUniqueId(UUID uniqueIdIn) {
        target.setUniqueId(uniqueIdIn);
    }

    public boolean isPushedByWater() {
        return target.isPushedByWater();
    }

    public void setCustomName(@Nullable ITextComponent name) {
        target.setCustomName(name);
    }

    public boolean hasCustomName() {
        return target.hasCustomName();
    }

    public void setCustomNameVisible(boolean alwaysRenderNameTag) {
        target.setCustomNameVisible(alwaysRenderNameTag);
    }

    public boolean isCustomNameVisible() {
        return target.isCustomNameVisible();
    }

    public final void teleportKeepLoaded(double x, double y, double z) {
        target.teleportKeepLoaded(x, y, z);
    }

    public void setPositionAndUpdate(double x, double y, double z) {
        target.setPositionAndUpdate(x, y, z);
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return target.getAlwaysRenderNameTagForRender();
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        target.notifyDataManagerChange(key);
    }

    public void recalculateSize() {
        target.recalculateSize();
    }

    public boolean isSpectatedByPlayer(ServerPlayerEntity player) {
        return target.isSpectatedByPlayer(player);
    }

    public void setBoundingBox(AxisAlignedBB bb) {
        target.setBoundingBox(bb);
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return ((EntityAccessor) target).invokegetEyeHeight(poseIn, sizeIn);
    }

    public float getEyeHeight(Pose pose) {
        return target.getEyeHeight(pose);
    }

    public final float getEyeHeight() {
        return target.getEyeHeight();
    }

    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
        return target.replaceItemInInventory(inventorySlot, itemStackIn);
    }

    public void sendMessage(ITextComponent component, UUID senderUUID) {
        target.sendMessage(component, senderUUID);
    }

    public boolean isImmuneToExplosions() {
        return target.isImmuneToExplosions();
    }

    public void applyEnchantments(LivingEntity entityLivingBaseIn, Entity entityIn) {
        target.applyEnchantments(entityLivingBaseIn, entityIn.target);
    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        target.addTrackingPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        target.removeTrackingPlayer(player);
    }

    public float getRotatedYaw(Rotation transformRotation) {
        return target.getRotatedYaw(transformRotation);
    }

    public float getMirroredYaw(Mirror transformMirror) {
        return target.getMirroredYaw(transformMirror);
    }

    public boolean ignoreItemEntityData() {
        return target.ignoreItemEntityData();
    }

    public boolean func_233577_ch_() {
        return target.func_233577_ch_();
    }

    public boolean func_233578_ci_() {
        return target.func_233578_ci_();
    }

    public boolean isPassenger(Entity entityIn) {
        return target.isPassenger(entityIn.target);
    }

    public boolean isOnePlayerRiding() {
        return target.isOnePlayerRiding();
    }

    public boolean isRidingSameEntity(Entity entityIn) {
        return target.isRidingSameEntity(entityIn.target);
    }

    public boolean isRidingOrBeingRiddenBy(Entity entityIn) {
        return target.isRidingOrBeingRiddenBy(entityIn.target);
    }

    public boolean canPassengerSteer() {
        return target.canPassengerSteer();
    }

    protected int getFireImmuneTicks() {
        return ((EntityAccessor) target).invokegetFireImmuneTicks();
    }

    protected int getPermissionLevel() {
        return ((EntityAccessor) target).invokegetPermissionLevel();
    }

    public boolean hasPermissionLevel(int level) {
        return target.hasPermissionLevel(level);
    }

    public boolean shouldReceiveFeedback() {
        return target.shouldReceiveFeedback();
    }

    public boolean shouldReceiveErrors() {
        return target.shouldReceiveErrors();
    }

    public boolean allowLogging() {
        return target.allowLogging();
    }

    public void lookAt(EntityAnchorArgument.Type anchor, Vector3d target) {
        this.target.lookAt(anchor, target);
    }

    public boolean handleFluidAcceleration(ITag<Fluid> fluidTag, double p_210500_2_) {
        return target.handleFluidAcceleration(fluidTag, p_210500_2_);
    }

    public double func_233571_b_(ITag<Fluid> p_233571_1_) {
        return target.func_233571_b_(p_233571_1_);
    }

    public double func_233579_cu_() {
        return target.func_233579_cu_();
    }

    public final float getWidth() {
        return target.getWidth();
    }

    public final float getHeight() {
        return target.getHeight();
    }

    public void setMotion(Vector3d motionIn) {
        target.setMotion(motionIn);
    }

    public void setMotion(double x, double y, double z) {
        target.setMotion(x, y, z);
    }

    public final double getPosX() {
        return target.getPosX();
    }

    public double getPosXWidth(double p_226275_1_) {
        return target.getPosXWidth(p_226275_1_);
    }

    public double getPosXRandom(double p_226282_1_) {
        return target.getPosXRandom(p_226282_1_);
    }

    public final double getPosY() {
        return target.getPosY();
    }

    public double getPosYHeight(double p_226283_1_) {
        return target.getPosYHeight(p_226283_1_);
    }

    public double getPosYRandom() {
        return target.getPosYRandom();
    }

    public double getPosYEye() {
        return target.getPosYEye();
    }

    public final double getPosZ() {
        return target.getPosZ();
    }

    public double getPosZWidth(double p_226285_1_) {
        return target.getPosZWidth(p_226285_1_);
    }

    public double getPosZRandom(double p_226287_1_) {
        return target.getPosZRandom(p_226287_1_);
    }

    public void setRawPosition(double x, double y, double z) {
        target.setRawPosition(x, y, z);
    }

    public void checkDespawn() {
        target.checkDespawn();
    }

    public void canUpdate(boolean value) {
        target.canUpdate(value);
    }

    public boolean canUpdate() {
        return target.canUpdate();
    }

    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) {
        return target.canTrample(state, pos, fallDistance);
    }

    public final boolean isAddedToWorld() {
        return target.isAddedToWorld();
    }

    public void onAddedToWorld() {
        target.onAddedToWorld();
    }

    public void onRemovedFromWorld() {
        target.onRemovedFromWorld();
    }

    public void revive() {
        target.revive();
    }
}