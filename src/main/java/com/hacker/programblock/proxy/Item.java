package com.hacker.programblock.proxy;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("all")
public class Item implements IProxy<net.minecraft.item.Item> {
    private final net.minecraft.item.Item target;

    public Item(net.minecraft.item.Item target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.item.Item getTarget() {
        return target;
    }

    public void onUse(World worldIn, LivingEntity livingEntityIn, ItemStack stack, int count) {
        target.onUse(worldIn.getTarget(), livingEntityIn.getTarget(), stack, count);
    }

    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return target.updateItemStackNBT(nbt);
    }

    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return target.canPlayerBreakBlockWhileHolding(state.getTarget(), worldIn.getTarget(), pos.getTarget(), player);
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return target.getDestroySpeed(stack, state.getTarget());
    }

    public final int getMaxStackSize() {
        return target.getMaxStackSize();
    }

    public final int getMaxDamage() {
        return target.getMaxDamage();
    }

    public boolean isDamageable() {
        return target.isDamageable();
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return this.target.hitEntity(stack, target.getTarget(), attacker.getTarget());
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return target.onBlockDestroyed(stack, worldIn.getTarget(), state.getTarget(), pos.getTarget(), entityLiving.getTarget());
    }

    public boolean canHarvestBlock(BlockState blockIn) {
        return target.canHarvestBlock(blockIn.getTarget());
    }

    public boolean shouldSyncTag() {
        return target.shouldSyncTag();
    }

    public boolean hasContainerItem() {
        return target.hasContainerItem();
    }

    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        target.inventoryTick(stack, worldIn.getTarget(), entityIn.getTarget(), itemSlot, isSelected);
    }

    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        target.onCreated(stack, worldIn.getTarget(), playerIn);
    }

    public boolean isComplex() {
        return target.isComplex();
    }

    public int getUseDuration(ItemStack stack) {
        return target.getUseDuration(stack);
    }

    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        target.onPlayerStoppedUsing(stack, worldIn.getTarget(), entityLiving.getTarget(), timeLeft);
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        target.addInformation(stack, worldIn.getTarget(), tooltip, flagIn);
    }

    public boolean hasEffect(ItemStack stack) {
        return target.hasEffect(stack);
    }

    public boolean isEnchantable(ItemStack stack) {
        return target.isEnchantable(stack);
    }

    public int getItemEnchantability() {
        return target.getItemEnchantability();
    }

    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        target.fillItemGroup(group, items);
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return target.getIsRepairable(toRepair, repair);
    }

    public boolean isRepairable(ItemStack stack) {
        return target.isRepairable(stack);
    }

    public int getHarvestLevel(ItemStack stack, net.minecraftforge.common.ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
        return target.getHarvestLevel(stack, tool, player, blockState.getTarget());
    }

    public boolean isCrossbow(ItemStack stack) {
        return target.isCrossbow(stack);
    }

    public boolean isIn(ITag<net.minecraft.item.Item> tagIn) {
        return target.isIn(tagIn);
    }

    public boolean isFood() {
        return target.isFood();
    }

    public boolean isImmuneToFire() {
        return target.isImmuneToFire();
    }

    public boolean isDamageable(DamageSource damageSource) {
        return target.isDamageable(damageSource);
    }
}
