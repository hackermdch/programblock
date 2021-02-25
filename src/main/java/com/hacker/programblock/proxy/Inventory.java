package com.hacker.programblock.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.ListNBT;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Inventory implements IProxy<net.minecraft.inventory.Inventory> {
    private final net.minecraft.inventory.Inventory target;

    public Inventory(net.minecraft.inventory.Inventory target) {
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.inventory.Inventory getTarget() {
        return target;
    }

    public void addListener(IInventoryChangedListener listener) {
        target.addListener(listener);
    }

    public void removeListener(IInventoryChangedListener listener) {
        target.removeListener(listener);
    }

    public boolean contains(ItemStack stack) {
        return target.func_233541_b_(stack.getTarget());
    }

    public void setInventorySlotContents(int index, ItemStack stack) {
        target.setInventorySlotContents(index, stack.getTarget());
    }

    public List<ItemStack> dropAll() {
        List<net.minecraft.item.ItemStack> l = target.func_233543_f_();
        List<ItemStack> ls = new ArrayList<>();
        l.forEach((e) -> ls.add(new ItemStack(e)));
        return ls;
    }

    public int getSizeInventory() {
        return target.getSizeInventory();
    }

    public ItemStack addItem(ItemStack stack) {
        return new ItemStack(target.addItem(stack.getTarget()));
    }

    public ItemStack removeItem(Item item, int count) {
        return new ItemStack(target.func_223374_a(item, count));
    }

    public boolean isEmpty() {
        return target.isEmpty();
    }

    public void markDirty() {
        target.markDirty();
    }

    public boolean isUsableByPlayer(PlayerEntity player) {
        return target.isUsableByPlayer(player);
    }

    public void clear() {
        target.clear();
    }

    public void fillStackedContents(RecipeItemHelper helper) {
        target.fillStackedContents(helper);
    }

    public void read(ListNBT p_70486_1_) {
        target.read(p_70486_1_);
    }

    public ListNBT write() {
        return target.write();
    }
}
