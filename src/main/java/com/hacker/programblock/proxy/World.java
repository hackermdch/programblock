package com.hacker.programblock.proxy;

import com.hacker.programblock.mixin.accessor.WorldAccessor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.MapData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class World implements IProxy<net.minecraft.world.World> {
    private final net.minecraft.world.World target;

    public World(@Nonnull net.minecraft.world.World target) {
        this.target = Objects.requireNonNull(target);
    }

    @Nonnull
    public net.minecraft.world.World getTarget() {
        return target;
    }

    public List<Entity> getEntities(@Nullable Entity entityIn, Box boundingBox, @Nullable Predicate<? super Entity> predicate) {
        assert entityIn != null;
        List<Entity> p = new ArrayList<>();
        List<net.minecraft.entity.Entity> l = target.getEntitiesInAABBexcluding(entityIn.getTarget(), boundingBox.getTarget(), predicate != null ? (e) -> predicate.test(new Entity(e)) : null);
        l.forEach((e) -> p.add(new Entity(e)));
        return p;
    }

    public boolean addEntity(Entity entity) {
        return target.addEntity(entity.getTarget());
    }

    public boolean isRemote() {
        return target.isRemote();
    }

    public boolean setBlockState(BlockPos pos, BlockState newState, int flags) {
        return target.setBlockState(pos.getTarget(), newState.getTarget(), flags);
    }

    @Nullable
    public TileEntity getTileEntity(BlockPos pos) {
        if (net.minecraft.world.World.isOutsideBuildHeight(pos.getTarget())) {
            return null;
        } else {
            TileEntity tileentity = null;
            if (target.processingLoadedTiles) {
                tileentity = target.getPendingTileEntityAt(pos.getTarget());
            }
            if (tileentity == null) {
                tileentity = target.getChunkAt(pos.getTarget()).getTileEntity(pos.getTarget(), Chunk.CreateEntityType.IMMEDIATE);
            }
            if (tileentity == null) {
                tileentity = target.getPendingTileEntityAt(pos.getTarget());
            }
            return tileentity;
        }
    }

    public boolean setBlockState(BlockPos pos, BlockState state, int flags, int recursionLeft) {
        return target.setBlockState(pos.getTarget(), state.getTarget(), flags, recursionLeft);
    }

    public BlockState getBlockState(BlockPos pos) {
        return new BlockState(target.getBlockState(pos.getTarget()));
    }

    public void markAndNotifyBlock(BlockPos pos, @Nullable Chunk chunk, BlockState blockstate, BlockState state, int flags, int recursionLeft) {
        target.markAndNotifyBlock(pos.getTarget(), chunk, blockstate.getTarget(), state.getTarget(), flags, recursionLeft);
    }

    public void onBlockStateChange(BlockPos pos, BlockState blockStateIn, BlockState newState) {
        target.onBlockStateChange(pos.getTarget(), blockStateIn.getTarget(), newState.getTarget());
    }

    public boolean removeBlock(BlockPos pos, boolean isMoving) {
        return target.removeBlock(pos.getTarget(), isMoving);
    }

    public boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity, int recursionLeft) {
        assert entity != null;
        return target.destroyBlock(pos.getTarget(), dropBlock, entity.getTarget(), recursionLeft);
    }

    public boolean setBlockState(BlockPos pos, BlockState state) {
        return target.setBlockState(pos.getTarget(), state.getTarget());
    }

    public void notifyBlockUpdate(BlockPos pos, BlockState oldState, BlockState newState, int flags) {
        target.notifyBlockUpdate(pos.getTarget(), oldState.getTarget(), newState.getTarget(), flags);
    }

    public void markBlockRangeForRenderUpdate(BlockPos blockPosIn, BlockState oldState, BlockState newState) {
        target.markBlockRangeForRenderUpdate(blockPosIn.getTarget(), oldState.getTarget(), newState.getTarget());
    }

    public void notifyNeighborsOfStateChange(BlockPos pos, Block blockIn) {
        target.notifyNeighborsOfStateChange(pos.getTarget(), blockIn.getTarget());
    }

    public void notifyNeighborsOfStateExcept(BlockPos pos, Block blockType, Direction skipSide) {
        target.notifyNeighborsOfStateExcept(pos.getTarget(), blockType.getTarget(), skipSide);
    }

    public void neighborChanged(BlockPos pos, Block blockIn, BlockPos fromPos) {
        target.neighborChanged(pos.getTarget(), blockIn.getTarget(), fromPos.getTarget());
    }

    public int getHeight(Heightmap.Type heightmapType, int x, int z) {
        return target.getHeight(heightmapType, x, z);
    }

    public boolean isDaytime() {
        return target.isDaytime();
    }

    public boolean isNightTime() {
        return target.isNightTime();
    }

    public void playSound(@Nullable PlayerEntity player, BlockPos pos, SoundEvent soundIn, SoundCategory category, float volume, float pitch) {
        target.playSound(player, pos.getTarget(), soundIn, category, volume, pitch);
    }

    public void playSound(@Nullable PlayerEntity player, double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch) {
        target.playSound(player, x, y, z, soundIn, category, volume, pitch);
    }

    public void playMovingSound(@Nullable PlayerEntity playerIn, Entity entityIn, SoundEvent eventIn, SoundCategory categoryIn, float volume, float pitch) {
        target.playMovingSound(playerIn, entityIn.getTarget(), eventIn, categoryIn, volume, pitch);
    }

    public void playSound(double x, double y, double z, SoundEvent soundIn, SoundCategory category, float volume, float pitch, boolean distanceDelay) {
        target.playSound(x, y, z, soundIn, category, volume, pitch, distanceDelay);
    }

    public void addParticle(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        target.addParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void addParticle(IParticleData particleData, boolean forceAlwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        target.addParticle(particleData, forceAlwaysRender, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void addOptionalParticle(IParticleData particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        target.addOptionalParticle(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void addOptionalParticle(IParticleData particleData, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        target.addOptionalParticle(particleData, ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public float getCelestialAngleRadians(float partialTicks) {
        return target.getCelestialAngleRadians(partialTicks);
    }

    public boolean addTileEntity(TileEntity tile) {
        return target.addTileEntity(tile);
    }

    public void addTileEntities(Collection<TileEntity> tileEntityCollection) {
        target.addTileEntities(tileEntityCollection);
    }

    public void tickBlockEntities() {
        target.tickBlockEntities();
    }

    public void guardEntityTick(Consumer<Entity> consumerEntity, Entity entityIn) {
        target.guardEntityTick((e) -> consumerEntity.accept(new Entity(e)), entityIn.getTarget());
    }

    public void setTileEntity(BlockPos pos, @Nullable TileEntity tileEntityIn) {
        target.setTileEntity(pos.getTarget(), tileEntityIn);
    }

    public void removeTileEntity(BlockPos pos) {
        target.removeTileEntity(pos.getTarget());
    }

    public boolean isBlockPresent(BlockPos pos) {
        return target.isBlockPresent(pos.getTarget());
    }

    public boolean isDirectionSolid(BlockPos pos, Entity entity, Direction direction) {
        return target.isDirectionSolid(pos.getTarget(), entity.getTarget(), direction);
    }

    public boolean isTopSolid(BlockPos pos, Entity entityIn) {
        return target.isTopSolid(pos.getTarget(), entityIn.getTarget());
    }

    public void calculateInitialSkylight() {
        target.calculateInitialSkylight();
    }

    public void setAllowedSpawnTypes(boolean hostile, boolean peaceful) {
        target.setAllowedSpawnTypes(hostile, peaceful);
    }

    protected void calculateInitialWeather() {
        ((WorldAccessor) target).invokecalculateInitialWeather();
    }

    public void close() throws IOException {
        target.close();
    }

    public void markChunkDirty(BlockPos pos, TileEntity unusedTileEntity) {
        target.markChunkDirty(pos.getTarget(), unusedTileEntity);
    }

    public int getSeaLevel() {
        return target.getSeaLevel();
    }

    public int getStrongPower(BlockPos pos) {
        return target.getStrongPower(pos.getTarget());
    }

    public boolean isSidePowered(BlockPos pos, Direction side) {
        return target.isSidePowered(pos.getTarget(), side);
    }

    public int getRedstonePower(BlockPos pos, Direction facing) {
        return target.getRedstonePower(pos.getTarget(), facing);
    }

    public boolean isBlockPowered(BlockPos pos) {
        return target.isBlockPowered(pos.getTarget());
    }

    public int getRedstonePowerFromNeighbors(BlockPos pos) {
        return target.getRedstonePowerFromNeighbors(pos.getTarget());
    }

    public void sendQuittingDisconnectingPacket() {
        target.sendQuittingDisconnectingPacket();
    }

    public long getGameTime() {
        return target.getGameTime();
    }

    public long getDayTime() {
        return target.getDayTime();
    }

    public boolean isBlockModifiable(PlayerEntity player, BlockPos pos) {
        return target.isBlockModifiable(player, pos.getTarget());
    }

    public void setEntityState(Entity entityIn, byte state) {
        target.setEntityState(entityIn.getTarget(), state);
    }

    public void addBlockEvent(BlockPos pos, Block blockIn, int eventID, int eventParam) {
        target.addBlockEvent(pos.getTarget(), blockIn.getTarget(), eventID, eventParam);
    }

    public float getThunderStrength(float delta) {
        return target.getThunderStrength(delta);
    }

    public void setThunderStrength(float strength) {
        target.setThunderStrength(strength);
    }

    public float getRainStrength(float delta) {
        return target.getRainStrength(delta);
    }

    public void setRainStrength(float strength) {
        target.setRainStrength(strength);
    }

    public boolean isThundering() {
        return target.isThundering();
    }

    public boolean isRaining() {
        return target.isRaining();
    }

    public boolean isRainingAt(BlockPos position) {
        return target.isRainingAt(position.getTarget());
    }

    public boolean isBlockinHighHumidity(BlockPos pos) {
        return target.isBlockinHighHumidity(pos.getTarget());
    }

    public void registerMapData(MapData mapDataIn) {
        target.registerMapData(mapDataIn);
    }

    public int getNextMapId() {
        return target.getNextMapId();
    }

    public void playBroadcastSound(int id, BlockPos pos, int data) {
        target.playBroadcastSound(id, pos.getTarget(), data);
    }

    public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
        target.sendBlockBreakProgress(breakerId, pos.getTarget(), progress);
    }

    public void makeFireworks(double x, double y, double z, double motionX, double motionY, double motionZ, @Nullable CompoundNBT compound) {
        target.makeFireworks(x, y, z, motionX, motionY, motionZ, compound);
    }

    public void updateComparatorOutputLevel(BlockPos pos, Block blockIn) {
        target.updateComparatorOutputLevel(pos.getTarget(), blockIn.getTarget());
    }

    public int getSkylightSubtracted() {
        return target.getSkylightSubtracted();
    }

    public void setTimeLightningFlash(int timeFlashIn) {
        target.setTimeLightningFlash(timeFlashIn);
    }

    public void sendPacketToServer(IPacket<?> packetIn) {
        target.sendPacketToServer(packetIn);
    }

    public boolean hasBlockState(BlockPos pos, Predicate<BlockState> state) {
        return target.hasBlockState(pos.getTarget(), (s) -> state.test(new BlockState(s)));
    }

    public boolean isSaveDisabled() {
        return target.isSaveDisabled();
    }

    public double getMaxEntityRadius() {
        return target.getMaxEntityRadius();
    }

    public double increaseMaxEntityRadius(double value) {
        return target.increaseMaxEntityRadius(value);
    }

    public final boolean isDebug() {
        return target.isDebug();
    }
}
