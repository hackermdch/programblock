package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.PlayerAccessor;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffers;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stat;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@SuppressWarnings("all")
public class Player extends LivingEntity {
    private final PlayerEntity target;

    public Player(@Nonnull PlayerEntity target) {
        super(target);
        this.target = target;
    }

    @Nonnull
    @Override
    public PlayerEntity getTarget() {
        return target;
    }

    public boolean blockActionRestricted(World worldIn, BlockPos pos, GameType gameMode) {
        return target.blockActionRestricted(worldIn.getTarget(), pos.getTarget(), gameMode);
    }

    protected void registerData() {
        ((PlayerAccessor) target).invokeregisterData();
    }

    public void tick() {
        target.tick();
    }

    public boolean isSecondaryUseActive() {
        return target.isSecondaryUseActive();
    }

    protected boolean wantsToStopRiding() {
        return ((PlayerAccessor) target).invokewantsToStopRiding();
    }

    protected boolean isStayingOnGroundSurface() {
        return ((PlayerAccessor) target).invokeisStayingOnGroundSurface();
    }

    protected boolean updateEyesInWaterPlayer() {
        return ((PlayerAccessor) target).invokeupdateEyesInWaterPlayer();
    }

    protected void updatePose() {
        ((PlayerAccessor) target).invokeupdatePose();
    }

    public int getMaxInPortalTime() {
        return target.getMaxInPortalTime();
    }

    public int getPortalCooldown() {
        return target.getPortalCooldown();
    }

    public void playSound(SoundEvent soundIn, float volume, float pitch) {
        target.playSound(soundIn, volume, pitch);
    }

    public void playSound(SoundEvent p_213823_1_, SoundCategory p_213823_2_, float p_213823_3_, float p_213823_4_) {
        target.playSound(p_213823_1_, p_213823_2_, p_213823_3_, p_213823_4_);
    }

    protected int getFireImmuneTicks() {
        return ((PlayerAccessor) target).invokegetFireImmuneTicks();
    }

    public void handleStatusUpdate(byte id) {
        target.handleStatusUpdate(id);
    }

    public void closeScreen() {
        target.closeScreen();
    }

    public void updateRidden() {
        target.updateRidden();
    }

    public void preparePlayerToSpawn() {
        target.preparePlayerToSpawn();
    }

    protected void updateEntityActionState() {
        ((PlayerAccessor) target).invokeupdateEntityActionState();
    }

    public void livingTick() {
        target.livingTick();
    }

    public int getScore() {
        return target.getScore();
    }

    public void setScore(int scoreIn) {
        target.setScore(scoreIn);
    }

    public void addScore(int scoreIn) {
        target.addScore(scoreIn);
    }

    public void onDeath(DamageSource cause) {
        target.onDeath(cause);
    }

    protected void dropInventory() {
        ((PlayerAccessor) target).invokedropInventory();
    }

    protected void destroyVanishingCursedItems() {
        ((PlayerAccessor) target).invokedestroyVanishingCursedItems();
    }

    public boolean drop(boolean p_225609_1_) {
        return target.drop(p_225609_1_);
    }

    public float getDigSpeed(BlockState state) {
        return target.getDigSpeed(state.getTarget());
    }

    public float getDigSpeed(BlockState state, @Nullable BlockPos pos) {
        return target.getDigSpeed(state.getTarget(), pos.getTarget());
    }

    public boolean func_234569_d_(BlockState p_234569_1_) {
        return target.func_234569_d_(p_234569_1_.getTarget());
    }

    public void readAdditional(CompoundNBT compound) {
        target.readAdditional(compound);
    }

    public void writeAdditional(CompoundNBT compound) {
        target.writeAdditional(compound);
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return target.isInvulnerableTo(source);
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        return target.attackEntityFrom(source, amount);
    }

    protected void blockUsingShield(LivingEntity entityIn) {
        ((PlayerAccessor) target).invokeblockUsingShield(entityIn.getTarget());
    }

    public boolean canAttackPlayer(Player other) {
        return target.canAttackPlayer(other.getTarget());
    }

    protected void damageArmor(DamageSource damageSource, float damage) {
        ((PlayerAccessor) target).invokedamageArmor(damageSource, damage);
    }

    protected void damageShield(float damage) {
        ((PlayerAccessor) target).invokedamageShield(damage);
    }

    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        ((PlayerAccessor) target).invokedamageEntity(damageSrc, damageAmount);
    }

    protected boolean func_230296_cM_() {
        return ((PlayerAccessor) target).invokefunc_230296_cM_();
    }

    public void openSignEditor(SignTileEntity signTile) {
        target.openSignEditor(signTile);
    }

    public void openMinecartCommandBlock(CommandBlockLogic commandBlock) {
        target.openMinecartCommandBlock(commandBlock);
    }

    public void openCommandBlock(CommandBlockTileEntity commandBlock) {
        target.openCommandBlock(commandBlock);
    }

    public void openStructureBlock(StructureBlockTileEntity structure) {
        target.openStructureBlock(structure);
    }

    public void openJigsaw(JigsawTileEntity p_213826_1_) {
        target.openJigsaw(p_213826_1_);
    }

    public void openHorseInventory(AbstractHorseEntity horse, IInventory inventoryIn) {
        target.openHorseInventory(horse, inventoryIn);
    }

    public void openMerchantContainer(int containerId, MerchantOffers offers, int level, int xp, boolean p_213818_5_, boolean p_213818_6_) {
        target.openMerchantContainer(containerId, offers, level, xp, p_213818_5_, p_213818_6_);
    }

    public void openBook(ItemStack stack, Hand hand) {
        target.openBook(stack, hand);
    }

    public double getYOffset() {
        return target.getYOffset();
    }

    public void dismount() {
        target.dismount();
    }

    protected boolean isMovementBlocked() {
        return ((PlayerAccessor) target).invokeisMovementBlocked();
    }

    public boolean func_241208_cS_() {
        return target.func_241208_cS_();
    }

    public void attackTargetEntityWithCurrentItem(Entity targetEntity) {
        target.attackTargetEntityWithCurrentItem(targetEntity.getTarget());
    }

    protected void spinAttack(LivingEntity p_204804_1_) {
        ((PlayerAccessor) target).invokespinAttack(p_204804_1_.getTarget());
    }

    public void disableShield(boolean p_190777_1_) {
        target.disableShield(p_190777_1_);
    }

    public void onCriticalHit(Entity entityHit) {
        target.onCriticalHit(entityHit.getTarget());
    }

    public void onEnchantmentCritical(Entity entityHit) {
        target.onEnchantmentCritical(entityHit.getTarget());
    }

    public void spawnSweepParticles() {
        target.spawnSweepParticles();
    }

    public void respawnPlayer() {
        target.respawnPlayer();
    }

    public void remove(boolean keepData) {
        target.remove(keepData);
    }

    public boolean isUser() {
        return target.isUser();
    }

    public void stopSleepInBed(boolean p_225652_1_, boolean p_225652_2_) {
        target.stopSleepInBed(p_225652_1_, p_225652_2_);
    }

    public void wakeUp() {
        target.wakeUp();
    }

    public boolean isPlayerFullyAsleep() {
        return target.isPlayerFullyAsleep();
    }

    public int getSleepTimer() {
        return target.getSleepTimer();
    }

    public void sendStatusMessage(ITextComponent chatComponent, boolean actionBar) {
        target.sendStatusMessage(chatComponent, actionBar);
    }

    public void addStat(ResourceLocation stat) {
        target.addStat(stat);
    }

    public void addStat(ResourceLocation p_195067_1_, int p_195067_2_) {
        target.addStat(p_195067_1_, p_195067_2_);
    }

    public void addStat(Stat<?> stat) {
        target.addStat(stat);
    }

    public void addStat(Stat<?> stat, int amount) {
        target.addStat(stat, amount);
    }

    public void takeStat(Stat<?> stat) {
        target.takeStat(stat);
    }

    public int unlockRecipes(Collection<IRecipe<?>> p_195065_1_) {
        return target.unlockRecipes(p_195065_1_);
    }

    public void unlockRecipes(ResourceLocation[] p_193102_1_) {
        target.unlockRecipes(p_193102_1_);
    }

    public int resetRecipes(Collection<IRecipe<?>> p_195069_1_) {
        return target.resetRecipes(p_195069_1_);
    }

    public void jump() {
        target.jump();
    }

    public void travel(Vector3d travelVector) {
        target.travel(travelVector);
    }

    public void updateSwimming() {
        target.updateSwimming();
    }

    protected boolean isNormalCube(BlockPos pos) {
        return ((PlayerAccessor) target).invokeisNormalCube(pos.getTarget());
    }

    public float getAIMoveSpeed() {
        return target.getAIMoveSpeed();
    }

    public void addMovementStat(double p_71000_1_, double p_71000_3_, double p_71000_5_) {
        target.addMovementStat(p_71000_1_, p_71000_3_, p_71000_5_);
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return target.onLivingFall(distance, damageMultiplier);
    }

    public boolean tryToStartFallFlying() {
        return target.tryToStartFallFlying();
    }

    public void startFallFlying() {
        target.startFallFlying();
    }

    public void stopFallFlying() {
        target.stopFallFlying();
    }

    protected void doWaterSplashEffect() {
        ((PlayerAccessor) target).invokedoWaterSplashEffect();
    }

    public void func_241847_a(ServerWorld p_241847_1_, LivingEntity p_241847_2_) {
        target.func_241847_a(p_241847_1_, p_241847_2_.getTarget());
    }

    public void setMotionMultiplier(BlockState state, Vector3d motionMultiplierIn) {
        target.setMotionMultiplier(state.getTarget(), motionMultiplierIn);
    }

    public void giveExperiencePoints(int p_195068_1_) {
        target.giveExperiencePoints(p_195068_1_);
    }

    public int getXPSeed() {
        return target.getXPSeed();
    }

    public void onEnchant(ItemStack enchantedItem, int cost) {
        target.onEnchant(enchantedItem, cost);
    }

    public void addExperienceLevel(int levels) {
        target.addExperienceLevel(levels);
    }

    public int xpBarCap() {
        return target.xpBarCap();
    }

    public void addExhaustion(float exhaustion) {
        target.addExhaustion(exhaustion);
    }

    public boolean canEat(boolean ignoreHunger) {
        return target.canEat(ignoreHunger);
    }

    public boolean shouldHeal() {
        return target.shouldHeal();
    }

    public boolean isAllowEdit() {
        return target.isAllowEdit();
    }

    public boolean canPlayerEdit(BlockPos pos, Direction facing, ItemStack stack) {
        return target.canPlayerEdit(pos.getTarget(), facing, stack);
    }

    protected int getExperiencePoints(Player player) {
        return ((PlayerAccessor) target).invokegetExperiencePoints(player.getTarget());
    }

    protected boolean isPlayer() {
        return ((PlayerAccessor) target).invokeisPlayer();
    }

    public boolean getAlwaysRenderNameTagForRender() {
        return target.getAlwaysRenderNameTagForRender();
    }

    protected boolean canTriggerWalking() {
        return ((PlayerAccessor) target).invokecanTriggerWalking();
    }

    public void sendPlayerAbilities() {
        target.sendPlayerAbilities();
    }

    public void setGameType(GameType gameType) {
        target.setGameType(gameType);
    }

    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
        target.setItemStackToSlot(slotIn, stack);
    }

    public boolean addItemStackToInventory(ItemStack p_191521_1_) {
        return target.addItemStackToInventory(p_191521_1_);
    }

    public boolean addShoulderEntity(CompoundNBT p_192027_1_) {
        return target.addShoulderEntity(p_192027_1_);
    }

    protected void spawnShoulderEntities() {
        ((PlayerAccessor) target).invokespawnShoulderEntities();
    }

    public boolean isSpectator() {
        return target.isSpectator();
    }

    public boolean isSwimming() {
        return target.isSwimming();
    }

    public boolean isCreative() {
        return target.isCreative();
    }

    public boolean isPushedByWater() {
        return target.isPushedByWater();
    }

    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return target.getStandingEyeHeight(poseIn, sizeIn);
    }

    public void setAbsorptionAmount(float amount) {
        target.setAbsorptionAmount(amount);
    }

    public float getAbsorptionAmount() {
        return target.getAbsorptionAmount();
    }

    public boolean isWearing(PlayerModelPart part) {
        return target.isWearing(part);
    }

    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
        return target.replaceItemInInventory(inventorySlot, itemStackIn);
    }

    public boolean hasReducedDebug() {
        return target.hasReducedDebug();
    }

    public void setReducedDebug(boolean reducedDebug) {
        target.setReducedDebug(reducedDebug);
    }

    public void forceFireTicks(int ticks) {
        target.forceFireTicks(ticks);
    }

    public void setPrimaryHand(HandSide hand) {
        target.setPrimaryHand(hand);
    }

    protected void setLeftShoulderEntity(CompoundNBT tag) {
        ((PlayerAccessor) target).invokesetLeftShoulderEntity(tag);
    }

    protected void setRightShoulderEntity(CompoundNBT tag) {
        ((PlayerAccessor) target).invokesetRightShoulderEntity(tag);
    }

    public float getCooldownPeriod() {
        return target.getCooldownPeriod();
    }

    public float getCooledAttackStrength(float adjustTicks) {
        return target.getCooledAttackStrength(adjustTicks);
    }

    public void resetCooldown() {
        target.resetCooldown();
    }

    protected float getSpeedFactor() {
        return ((PlayerAccessor) target).invokegetSpeedFactor();
    }

    public float getLuck() {
        return target.getLuck();
    }

    public boolean canUseCommandBlock() {
        return target.canUseCommandBlock();
    }

    public boolean canPickUpItem(ItemStack itemstackIn) {
        return target.canPickUpItem(itemstackIn);
    }

    protected boolean func_230295_b_(BlockState p_230295_1_) {
        return ((PlayerAccessor) target).invokefunc_230295_b_(p_230295_1_.getTarget());
    }

    public void refreshDisplayName() {
        target.refreshDisplayName();
    }

    public void setForcedPose(@Nullable Pose pose) {
        target.setForcedPose(pose);
    }
}
