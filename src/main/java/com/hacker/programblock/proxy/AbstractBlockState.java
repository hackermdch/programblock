package com.hacker.programblock.proxy;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tags.ITag;
import net.minecraft.util.BlockVoxelShape;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings({"unused", "deprecation"})
public class AbstractBlockState implements IProxy<AbstractBlock.AbstractBlockState> {

    private final AbstractBlock.AbstractBlockState target;

    public AbstractBlockState(@Nonnull AbstractBlock.AbstractBlockState target) {
        this.target = Objects.requireNonNull(target);
    }

    @Nonnull
    @Override
    public AbstractBlock.AbstractBlockState getTarget() {
        return target;
    }

    public void cacheState() {
        target.cacheState();
    }

    public boolean canEntitySpawn(IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return target.canEntitySpawn(worldIn, pos.getTarget(), type);
    }

    public boolean propagatesSkylightDown(IBlockReader worldIn, BlockPos pos) {
        return target.propagatesSkylightDown(worldIn, pos.getTarget());
    }

    public int getOpacity(IBlockReader worldIn, BlockPos pos) {
        return target.getOpacity(worldIn, pos.getTarget());
    }

    public boolean isCollisionShapeLargerThanFullBlock() {
        return target.isCollisionShapeLargerThanFullBlock();
    }

    public boolean isTransparent() {
        return target.isTransparent();
    }

    public int getLightValue() {
        return target.getLightValue();
    }

    public boolean isAir() {
        return target.isAir();
    }

    public boolean isEmissiveRendering(IBlockReader reader, BlockPos pos) {
        return target.isEmissiveRendering(reader, pos.getTarget());
    }

    public float getAmbientOcclusionLightValue(IBlockReader reader, BlockPos pos) {
        return target.getAmbientOcclusionLightValue(reader, pos.getTarget());
    }

    public boolean isNormalCube(IBlockReader reader, BlockPos pos) {
        return target.isNormalCube(reader, pos.getTarget());
    }

    public boolean canProvidePower() {
        return target.canProvidePower();
    }

    public int getWeakPower(IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getWeakPower(blockAccess, pos.getTarget(), side);
    }

    public boolean hasComparatorInputOverride() {
        return target.hasComparatorInputOverride();
    }

    public int getComparatorInputOverride(World worldIn, BlockPos pos) {
        return target.getComparatorInputOverride(worldIn.getTarget(), pos.getTarget());
    }

    public float getBlockHardness(IBlockReader worldIn, BlockPos pos) {
        return target.getBlockHardness(worldIn, pos.getTarget());
    }

    public float getPlayerRelativeBlockHardness(PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        return target.getPlayerRelativeBlockHardness(player, worldIn, pos.getTarget());
    }

    public int getStrongPower(IBlockReader blockAccess, BlockPos pos, Direction side) {
        return target.getStrongPower(blockAccess, pos.getTarget(), side);
    }

    public boolean isOpaqueCube(IBlockReader worldIn, BlockPos pos) {
        return target.isOpaqueCube(worldIn, pos.getTarget());
    }

    public boolean isSolid() {
        return target.isSolid();
    }

    public boolean isSideInvisible(BlockState state, Direction face) {
        return target.isSideInvisible(state, face);
    }

    public final boolean canSpawnMobs(IBlockReader reader, BlockPos pos, Entity entity) {
        return target.canSpawnMobs(reader, pos.getTarget(), entity.getTarget());
    }

    public final boolean isTopSolid(IBlockReader reader, BlockPos pos, Entity entityIn, Direction direction) {
        return target.isTopSolid(reader, pos.getTarget(), entityIn.getTarget(), direction);
    }

    public boolean receiveBlockEvent(World world, BlockPos pos, int id, int param) {
        return target.receiveBlockEvent(world.getTarget(), pos.getTarget(), id, param);
    }

    public void neighborChanged(World worldIn, BlockPos posIn, Block blockIn, BlockPos fromPosIn, boolean isMoving) {
        target.neighborChanged(worldIn.getTarget(), posIn.getTarget(), blockIn.getTarget(), fromPosIn.getTarget(), isMoving);
    }

    public final void updateNeighbours(IWorld world, BlockPos pos, int flag) {
        target.updateNeighbours(world, pos.getTarget(), flag);
    }

    public final void updateNeighbours(IWorld world, BlockPos pos, int flag, int recursionLeft) {
        target.updateNeighbours(world, pos.getTarget(), flag, recursionLeft);
    }

    public final void updateDiagonalNeighbors(IWorld worldIn, BlockPos pos, int flags) {
        target.updateDiagonalNeighbors(worldIn, pos.getTarget(), flags);
    }

    public void updateDiagonalNeighbors(IWorld world, BlockPos pos, int flags, int recursionLeft) {
        target.updateDiagonalNeighbors(world, pos.getTarget(), flags, recursionLeft);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        target.onBlockAdded(worldIn.getTarget(), pos.getTarget(), oldState, isMoving);
    }

    public void onReplaced(World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        target.onReplaced(worldIn.getTarget(), pos.getTarget(), newState, isMoving);
    }

    public void tick(ServerWorld worldIn, BlockPos posIn, Random randomIn) {
        target.tick(worldIn, posIn.getTarget(), randomIn);
    }

    public void randomTick(ServerWorld worldIn, BlockPos posIn, Random randomIn) {
        target.randomTick(worldIn, posIn.getTarget(), randomIn);
    }

    public void onEntityCollision(World worldIn, BlockPos pos, Entity entityIn) {
        target.onEntityCollision(worldIn.getTarget(), pos.getTarget(), entityIn.getTarget());
    }

    public void spawnAdditionalDrops(ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        target.spawnAdditionalDrops(worldIn, pos.getTarget(), stack);
    }

    public void onBlockClicked(World worldIn, BlockPos pos, PlayerEntity player) {
        target.onBlockClicked(worldIn.getTarget(), pos.getTarget(), player);
    }

    public boolean isSuffocating(IBlockReader blockReaderIn, BlockPos blockPosIn) {
        return target.isSuffocating(blockReaderIn, blockPosIn.getTarget());
    }

    public boolean causesSuffocation(IBlockReader worldIn, BlockPos pos) {
        return target.causesSuffocation(worldIn, pos.getTarget());
    }

    public boolean allowsMovement(IBlockReader worldIn, BlockPos pos, PathType type) {
        return target.allowsMovement(worldIn, pos.getTarget(), type);
    }

    public boolean isReplaceable(BlockItemUseContext useContext) {
        return target.isReplaceable(useContext);
    }

    public boolean isReplaceable(Fluid fluidIn) {
        return target.isReplaceable(fluidIn);
    }

    public boolean isValidPosition(IWorldReader worldIn, BlockPos pos) {
        return target.isValidPosition(worldIn, pos.getTarget());
    }

    public boolean blockNeedsPostProcessing(IBlockReader worldIn, BlockPos pos) {
        return target.blockNeedsPostProcessing(worldIn, pos.getTarget());
    }

    public boolean isIn(ITag<net.minecraft.block.Block> tag) {
        return target.isIn(tag);
    }

    public boolean isInAndMatches(ITag<net.minecraft.block.Block> tag, Predicate<AbstractBlock.AbstractBlockState> predicate) {
        return target.isInAndMatches(tag, predicate);
    }

    public boolean isIn(Block tagIn) {
        return target.isIn(tagIn.getTarget());
    }

    public boolean ticksRandomly() {
        return target.ticksRandomly();
    }

    public long getPositionRandom(BlockPos pos) {
        return target.getPositionRandom(pos.getTarget());
    }

    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        target.onProjectileCollision(worldIn.getTarget(), state, hit, projectile);
    }

    public boolean isSolidSide(IBlockReader blockReaderIn, BlockPos blockPosIn, Direction directionIn) {
        return target.isSolidSide(blockReaderIn, blockPosIn.getTarget(), directionIn);
    }

    public boolean func_242698_a(IBlockReader blockReader, BlockPos pos, Direction direction, BlockVoxelShape blockVoxelShape) {
        return target.func_242698_a(blockReader, pos.getTarget(), direction, blockVoxelShape);
    }

    public boolean hasOpaqueCollisionShape(IBlockReader reader, BlockPos pos) {
        return target.hasOpaqueCollisionShape(reader, pos.getTarget());
    }

    public boolean getRequiresTool() {
        return target.getRequiresTool();
    }
}
