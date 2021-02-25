package com.hacker.programblock.proxy;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tags.ITagCollectionSupplier;
import net.minecraft.util.CachedBlockInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Consumer;

@SuppressWarnings("all")
public class ItemStack implements IProxy<net.minecraft.item.ItemStack> {
    private final net.minecraft.item.ItemStack target;

    public ItemStack(Item item) {
        target = new net.minecraft.item.ItemStack(item.getTarget());
    }

    public ItemStack(net.minecraft.item.ItemStack target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.item.ItemStack getTarget() {
        return target;
    }

    public boolean isEmpty() {
        return target.isEmpty();
    }

    public float getDestroySpeed(BlockState blockIn) {
        return target.getDestroySpeed(blockIn.getTarget());
    }

    public int getMaxStackSize() {
        return target.getMaxStackSize();
    }

    public boolean isStackable() {
        return target.isStackable();
    }

    public boolean isDamageable() {
        return target.isDamageable();
    }

    public boolean isDamaged() {
        return target.isDamaged();
    }

    public int getDamage() {
        return target.getDamage();
    }

    public void setDamage(int damage) {
        target.setDamage(damage);
    }

    public int getMaxDamage() {
        return target.getMaxDamage();
    }

    public boolean attemptDamageItem(int amount, Random rand, @Nullable ServerPlayerEntity damager) {
        return target.attemptDamageItem(amount, rand, damager);
    }

    public <T extends LivingEntity> void damageItem(int amount, T entityIn, Consumer<T> onBroken) {
        target.damageItem(amount, entityIn.getTarget(), (e) -> onBroken.accept((T) new LivingEntity(e)));
    }

    public void hitEntity(LivingEntity entityIn, Player playerIn) {
        target.hitEntity(entityIn.getTarget(), playerIn.getTarget());
    }

    public void onBlockDestroyed(World worldIn, BlockState blockIn, BlockPos pos, Player playerIn) {
        target.onBlockDestroyed(worldIn.getTarget(), blockIn.getTarget(), pos.getTarget(), playerIn.getTarget());
    }

    public boolean canHarvestBlock(BlockState blockIn) {
        return target.canHarvestBlock(blockIn.getTarget());
    }

    public boolean isItemEqual(ItemStack other) {
        return target.isItemEqual(other.target);
    }

    public boolean isItemEqualIgnoreDurability(ItemStack stack) {
        return target.isItemEqualIgnoreDurability(stack.target);
    }

    public void inventoryTick(World worldIn, Entity entityIn, int inventorySlot, boolean isCurrentItem) {
        target.inventoryTick(worldIn.getTarget(), entityIn.getTarget(), inventorySlot, isCurrentItem);
    }

    public void onCrafting(World worldIn, Player playerIn, int amount) {
        target.onCrafting(worldIn.getTarget(), playerIn.getTarget(), amount);
    }

    public int getUseDuration() {
        return target.getUseDuration();
    }

    public void onPlayerStoppedUsing(World worldIn, LivingEntity entityLiving, int timeLeft) {
        target.onPlayerStoppedUsing(worldIn.getTarget(), entityLiving.getTarget(), timeLeft);
    }

    public boolean isCrossbowStack() {
        return target.isCrossbowStack();
    }

    public boolean hasTag() {
        return target.hasTag();
    }

    public void removeChildTag(String p_196083_1_) {
        target.removeChildTag(p_196083_1_);
    }

    public void setTag(@Nullable CompoundNBT nbt) {
        target.setTag(nbt);
    }

    public void clearCustomName() {
        target.clearCustomName();
    }

    public boolean hasDisplayName() {
        return target.hasDisplayName();
    }

    public void setHideFlags(net.minecraft.item.ItemStack.TooltipDisplayFlags p_242395_1_) {
        target.func_242395_a(p_242395_1_);
    }

    public boolean hasEffect() {
        return target.hasEffect();
    }

    public boolean isEnchantable() {
        return target.isEnchantable();
    }

    public void addEnchantment(Enchantment ench, int level) {
        target.addEnchantment(ench, level);
    }

    public boolean isEnchanted() {
        return target.isEnchanted();
    }

    public void setTagInfo(String key, INBT value) {
        target.setTagInfo(key, value);
    }

    public boolean isOnItemFrame() {
        return target.isOnItemFrame();
    }

    public void setAttachedEntity(@Nullable Entity entity) {
        target.setAttachedEntity(entity.getTarget());
    }

    public int getRepairCost() {
        return target.getRepairCost();
    }

    public void setRepairCost(int cost) {
        target.setRepairCost(cost);
    }

    public void addAttributeModifier(Attribute attributeName, AttributeModifier modifier, @Nullable EquipmentSlotType equipmentSlot) {
        target.addAttributeModifier(attributeName, modifier, equipmentSlot);
    }

    public boolean canDestroy(ITagCollectionSupplier p_206848_1_, CachedBlockInfo p_206848_2_) {
        return target.canDestroy(p_206848_1_, p_206848_2_);
    }

    public boolean canPlaceOn(ITagCollectionSupplier p_206847_1_, CachedBlockInfo p_206847_2_) {
        return target.canPlaceOn(p_206847_1_, p_206847_2_);
    }

    public int getAnimationsToGo() {
        return target.getAnimationsToGo();
    }

    public void setAnimationsToGo(int animations) {
        target.setAnimationsToGo(animations);
    }

    public int getCount() {
        return target.getCount();
    }

    public void setCount(int count) {
        target.setCount(count);
    }

    public void grow(int count) {
        target.grow(count);
    }

    public void shrink(int count) {
        target.shrink(count);
    }

    public void onItemUsed(World worldIn, LivingEntity livingEntityIn, int countIn) {
        target.onItemUsed(worldIn.getTarget(), livingEntityIn.getTarget(), countIn);
    }

    public boolean isFood() {
        return target.isFood();
    }

    public void deserializeNBT(CompoundNBT nbt) {
        target.deserializeNBT(nbt);
    }
}
