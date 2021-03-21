package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.AbstractBlockAccessor;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
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

    @Nonnull
    public net.minecraft.block.AbstractBlock getTarget() {
        return target;
    }

    public void updateDiagonalNeighbors(BlockState state, IWorld worldIn, BlockPos pos, int flags, int recursionLeft) {
        target.updateDiagonalNeighbors(state.getTarget(), worldIn, pos.getTarget(), flags, recursionLeft);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return target.allowsMovement(state.getTarget(), worldIn, pos.getTarget(), type);
    }

    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        return target.isSideInvisible(state.getTarget(), adjacentBlockState.getTarget(), side);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        target.neighborChanged(state.getTarget(), worldIn.getTarget(), pos.getTarget(), blockIn.getTarget(), fromPos.getTarget(), isMoving);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        target.onBlockAdded(state.getTarget(), worldIn.getTarget(), pos.getTarget(), oldState.getTarget(), isMoving);
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        target.onReplaced(state.getTarget(), worldIn.getTarget(), pos.getTarget(), newState.getTarget(), isMoving);
    }

    public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        return target.eventReceived(state.getTarget(), worldIn.getTarget(), pos.getTarget(), id, param);
    }

    public boolean isTransparent(BlockState state) {
        return target.isTransparent(state.getTarget());
    }

    public boolean canProvidePower(BlockState state) {
        return target.canProvidePower(state.getTarget());
    }

    public boolean hasComparatorInputOverride(BlockState state) {
        return target.hasComparatorInputOverride(state.getTarget());
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return target.isReplaceable(state.getTarget(), useContext);
    }

    public boolean isReplaceable(BlockState state, Fluid fluid) {
        return target.isReplaceable(state.getTarget(), fluid);
    }

    public long getPositionRandom(BlockState state, BlockPos pos) {
        return target.getPositionRandom(state.getTarget(), pos.getTarget());
    }

    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return target.getOpacity(state.getTarget(), worldIn, pos.getTarget());
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return target.isValidPosition(state.getTarget(), worldIn, pos.getTarget());
    }

    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return target.getAmbientOcclusionLightValue(state.getTarget(), worldIn, pos.getTarget());
    }

    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return target.getComparatorInputOverride(blockState.getTarget(), worldIn.getTarget(), pos.getTarget());
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        target.randomTick(state.getTarget(), worldIn, pos.getTarget(), random);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        target.tick(state.getTarget(), worldIn, pos.getTarget(), rand);
    }

    public float getPlayerRelativeBlockHardness(BlockState state, Player player, IBlockReader worldIn, BlockPos pos) {
        return target.getPlayerRelativeBlockHardness(state.getTarget(), player.getTarget(), worldIn, pos.getTarget());
    }

    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        target.spawnAdditionalDrops(state.getTarget(), worldIn, pos.getTarget(), stack);
    }

    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, Player player) {
        target.onBlockClicked(state.getTarget(), worldIn.getTarget(), pos.getTarget(), player.getTarget());
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getWeakPower(blockState.getTarget(), blockAccess, pos.getTarget(), side);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        target.onEntityCollision(state.getTarget(), worldIn.getTarget(), pos.getTarget(), entityIn.getTarget());
    }

    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getStrongPower(blockState.getTarget(), blockAccess, pos.getTarget(), side);
    }

    public final boolean isTileEntityProvider() {
        return target.isTileEntityProvider();
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        target.onProjectileCollision(worldIn.getTarget(), state.getTarget(), hit, projectile);
    }

    protected boolean isAir(BlockState state) {
        return ((AbstractBlockAccessor) target).invokeisAir(state.getTarget());
    }

}