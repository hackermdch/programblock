package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.AbstractBlockAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({"unused", "deprecation"})
public class AbstractBlock implements IProxy<net.minecraft.block.AbstractBlock> {
    private final net.minecraft.block.AbstractBlock target;

    public AbstractBlock(@Nonnull net.minecraft.block.AbstractBlock target) {
        this.target = Objects.requireNonNull(target);
    }

    public net.minecraft.block.AbstractBlock getTarget() {
        return target;
    }

    public void updateDiagonalNeighbors(BlockState state, IWorld worldIn, BlockPos pos, int flags, int recursionLeft) {
        target.updateDiagonalNeighbors(state, worldIn, pos, flags, recursionLeft);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return target.allowsMovement(state, worldIn, pos, type);
    }

    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        return target.isSideInvisible(state, adjacentBlockState, side);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        target.neighborChanged(state, worldIn.getTarget(), pos, blockIn, fromPos, isMoving);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        target.onBlockAdded(state, worldIn.getTarget(), pos, oldState, isMoving);
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        target.onReplaced(state, worldIn.getTarget(), pos, newState, isMoving);
    }

    public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        return target.eventReceived(state, worldIn.getTarget(), pos, id, param);
    }

    public boolean isTransparent(BlockState state) {
        return target.isTransparent(state);
    }

    public boolean canProvidePower(BlockState state) {
        return target.canProvidePower(state);
    }

    public boolean hasComparatorInputOverride(BlockState state) {
        return target.hasComparatorInputOverride(state);
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return target.isReplaceable(state, useContext);
    }

    public boolean isReplaceable(BlockState state, Fluid fluid) {
        return target.isReplaceable(state, fluid);
    }

    public long getPositionRandom(BlockState state, BlockPos pos) {
        return target.getPositionRandom(state, pos);
    }

    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return target.getOpacity(state, worldIn, pos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return target.isValidPosition(state, worldIn, pos);
    }

    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return target.getAmbientOcclusionLightValue(state, worldIn, pos);
    }

    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return target.getComparatorInputOverride(blockState, worldIn.getTarget(), pos);
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        target.randomTick(state, worldIn, pos, random);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        target.tick(state, worldIn, pos, rand);
    }

    public float getPlayerRelativeBlockHardness(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        return target.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        target.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        target.onBlockClicked(state, worldIn.getTarget(), pos, player);
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getWeakPower(blockState, blockAccess, pos, side);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        target.onEntityCollision(state, worldIn.getTarget(), pos, entityIn.getTarget());
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getStrongPower(blockState, blockAccess, pos, side);
    }

    public final boolean isTileEntityProvider() {
        return target.isTileEntityProvider();
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        target.onProjectileCollision(worldIn.getTarget(), state, hit, projectile);
    }

    protected boolean isAir(BlockState state) {
        return ((AbstractBlockAccessor) target).invokeisAir(state);
    }

}