package com.hacker.programblock;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import javax.tools.StandardLocation;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"NullableProblems", "deprecation"})
public final class ProgramBlockTileEntity extends TileEntity {
    public String code;
    private int classnum = -1;
    private static final Map<BlockPos, Thread> threads = new HashMap<>();

    public ProgramBlockTileEntity() {
        super(Hacker.programblock_tile.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        code = nbt.getString("Code");
    }

    protected static Thread getThreadInPos(BlockPos pos) {
        for (BlockPos a : threads.keySet()) {
            if (a.equals(pos)) {
                return threads.get(a);
            }
        }
        return null;
    }

    protected static Thread getThreadInPos(int x, int y, int z) {
        return getThreadInPos(new BlockPos(x, y, z));
    }

    protected static void setThreadInPos(BlockPos pos, Thread thread) {
        for (BlockPos a : threads.keySet()) {
            if (a.equals(pos)) {
                threads.get(a).stop();
                threads.remove(a);
                threads.put(pos, thread);
                return;
            }
        }
        threads.put(pos, thread);
    }

    protected static void setThreadInPos(int x, int y, int z, Thread thread) {
        setThreadInPos(new BlockPos(x, y, z), thread);
    }

    protected static void removeThreadIn(BlockPos pos) {
        for (BlockPos a : threads.keySet()) {
            if (a.equals(pos)) {
                threads.get(a).stop();
                threads.remove(a);
                return;
            }
        }
    }

    protected static void removeAllThread() {
        threads.values().forEach(Thread::stop);
        threads.clear();
    }

    protected static void removeThreadIn(int x, int y, int z) {
        removeThreadIn(new BlockPos(x, y, z));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.putString("Code", code == null ? "" : code);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        code = tag.getString("Code");
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(world.getBlockState(pkt.getPos()), pkt.getNbtCompound());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putString("Code", code == null ? "" : code);
        return compound;
    }

    public String getClassName() {
        return ("program$" + getPos().getX() + getPos().getY() + getPos().getZ() + "$" + classnum).replaceAll("-", "a");
    }

    public void update() {
        if (Compiler.isInited()) {
            try {
                Compiler.getFileManager().removeFileObject(StandardLocation.SOURCE_PATH, "com/hacker/dy", getClassName() + ".java");
                Compiler.getFileManager().removeFileObject(StandardLocation.CLASS_PATH, "com/hacker/dy", getClassName() + ".class");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Compiler.getClassLoader().removeFileObject("com.hacker.dy." + getClassName());
        }
        classnum++;
    }

    public String genSource() {
        String code = this.code.replaceAll("java\\.io", "");
        code = code.replaceAll("java\\.lang\\.Runtime", "Runtime");
        code = code.replaceAll("java\\.lang\\.reflect", "");
        code = code.replaceAll("net\\.minecraft\\.(?:client|server)", "");
        return "package com.hacker.dy;\n" +
                "import com.hacker.programblock.proxy.*;\n" +
                "import java.util.*;\n" +
                "import static com.hacker.programblock.ProgramUtils.*;\n" +
                "class " + getClassName() + " implements Runnable {\n" +
                "\tpublic " + getClassName() + "(){}\n" +
                "\tpublic void run() {\n" + "\t\t" + code.replaceAll("\n", "\n\t\t") + "\n\t}\n" +
                "\tprivate int getX(){return " + getPos().getX() + ";}\n" +
                "\tprivate int getY(){return " + getPos().getY() + ";}\n" +
                "\tprivate int getZ(){return " + getPos().getZ() + ";}\n" +
                "}";
    }
}
