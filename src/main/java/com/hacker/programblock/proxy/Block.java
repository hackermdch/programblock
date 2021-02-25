package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.BlockAccessor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.ITag;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"unused", "deprecation"})
public class Block extends AbstractBlock {
    private final net.minecraft.block.Block target;

    public Block(@Nonnull net.minecraft.block.Block target) {
        super(target);
        this.target = target;
    }

    @Nonnull
    @Override
    public net.minecraft.block.Block getTarget() {
        return target;
    }

    public BlockState getDefaultState() {
        return new BlockState(target.getDefaultState());
    }

    public boolean isIn(ITag<net.minecraft.block.Block> tagIn) {
        return target.isIn(tagIn);
    }

    public boolean matchesBlock(Block block) {
        return target.matchesBlock(block.target);
    }

    public boolean ticksRandomly(BlockState state) {
        return target.ticksRandomly(state.getTarget());
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return target.propagatesSkylightDown(state.getTarget(), reader, pos.getTarget());
    }

    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        target.animateTick(stateIn.getTarget(), worldIn.getTarget(), pos.getTarget(), rand);
    }

    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        target.onPlayerDestroy(worldIn, pos.getTarget(), state.getTarget());
    }

    public void dropXpOnBlockBreak(ServerWorld worldIn, BlockPos pos, int amount) {
        target.dropXpOnBlockBreak(worldIn, pos.getTarget(), amount);
    }

    public float getExplosionResistance() {
        return target.getExplosionResistance();
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        target.onExplosionDestroy(worldIn.getTarget(), pos.getTarget(), explosionIn);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        target.onEntityWalk(worldIn.getTarget(), pos.getTarget(), entityIn.getTarget());
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        target.harvestBlock(worldIn.getTarget(), player, pos.getTarget(), state.getTarget(), te, stack);
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        target.onBlockPlacedBy(worldIn.getTarget(), pos.getTarget(), state.getTarget(), placer, stack);
    }

    public boolean canSpawnInBlock() {
        return target.canSpawnInBlock();
    }

    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        target.onFallenUpon(worldIn.getTarget(), pos.getTarget(), entityIn.getTarget(), fallDistance);
    }

    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        target.onLanded(worldIn, entityIn.getTarget());
    }

    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        target.fillItemGroup(group, items);
    }

    public float getSlipperiness() {
        return target.getSlipperiness();
    }

    public float getSpeedFactor() {
        return target.getSpeedFactor();
    }

    public float getJumpFactor() {
        return target.getJumpFactor();
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        target.onBlockHarvested(worldIn.getTarget(), pos.getTarget(), state.getTarget(), player);
    }

    public void fillWithRain(World worldIn, BlockPos pos) {
        target.fillWithRain(worldIn.getTarget(), pos.getTarget());
    }

    public boolean canDropFromExplosion(Explosion explosionIn) {
        return target.canDropFromExplosion(explosionIn);
    }

    protected void fillStateContainer(StateContainer.Builder<net.minecraft.block.Block, net.minecraft.block.BlockState> builder) {
        ((BlockAccessor) target).invokefillStateContainer(builder);
    }

    protected final void setDefaultState(BlockState state) {
        ((BlockAccessor) target).invokesetDefaultState(state.getTarget());
    }

    public boolean isVariableOpacity() {
        return target.isVariableOpacity();
    }

    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        target.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        assert entity != null;
        return target.getSlipperiness(state.getTarget(), world, pos.getTarget(), entity.getTarget());
    }

    public int getHarvestLevel(BlockState state) {
        return target.getHarvestLevel(state.getTarget());
    }

    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        return target.canSustainPlant(state.getTarget(), world, pos.getTarget(), facing, plantable);
    }
}
