package com.hacker.programblock;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import javax.tools.StandardLocation;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("all")
public final class ProgramBlockTileEntity extends TileEntity {
    public String code;
    private int classnum = -1;
    private static final Map<BlockPos, Thread> threads = new HashMap<>();
    public boolean powered = false;
    protected boolean redstone_control = true;

    public ProgramBlockTileEntity() {
        super(Hacker.programblock_tile.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        code = nbt.getString("Code");
        redstone_control = nbt.getBoolean("RedstoneControl");
    }

    protected void execute() {
        call(false, null);
    }

    protected Object call(boolean sync, Map<String, Object> args) {
        return call(sync, args, null);
    }

    protected Object call(boolean sync, Map<String, Object> args, Consumer<Object> callback) {
        try {
            Class<?> z = null;
            try {
                z = Compiler.getClassLoader().loadClass("com.hacker.dy." + getClassName());
            } catch (ClassNotFoundException cnf) {
                StringWriter s = new StringWriter();
                try {
                    z = Compiler.compile("com.hacker.dy", getClassName(), genSource(), s);
                } catch (Exception ee) {
                    for (ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
                        if (player.hasPermissionLevel(2)) {
                            compileError(s, ee, player);
                        }
                    }
                    ee.printStackTrace();
                }
            }
            Object o = z.newInstance();
            ProgramFunction fun = (ProgramFunction) o;
            if (args != null)
                fun.setArgs(args);
            if (getThreadInPos(pos) != null)
                Objects.requireNonNull(getThreadInPos(pos)).stop();
            if (!sync) {
                Callback c = new Callback();
                c.delegate = fun;
                c.callback = callback;
                Thread t = new Thread(c);
                t.setDaemon(true);
                setThreadInPos(pos, t);
                t.start();
            } else {
                fun.execute();
                return fun.getReturnValue();
            }
        } catch (Exception e) {
            assert world != null;
            for (ServerPlayerEntity player : Objects.requireNonNull(world.getServer()).getPlayerList().getPlayers()) {
                if (player.hasPermissionLevel(2)) {
                    player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.runtime_error", e.toString(), pos));
                    for (StackTraceElement se : e.getStackTrace()) {
                        player.getCommandSource().sendErrorMessage(new StringTextComponent(se.toString()));
                    }
                }
            }
            e.printStackTrace();
        }
        return null;
    }

    static void compileError(StringWriter s, Exception e, ServerPlayerEntity player) {
        if (s.toString().isEmpty()) {
            player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.compile_failed", e.toString()));
            for (StackTraceElement se : e.getStackTrace()) {
                player.getCommandSource().sendErrorMessage(new StringTextComponent(se.toString()));
            }
        } else {
            player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.compile_failed", s.toString().replaceAll("\r", "").replaceAll("\t", "  ")));
        }
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
        compoundNBT.putBoolean("RedstoneControl", redstone_control);
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        code = tag.getString("Code");
        redstone_control = tag.getBoolean("RedstoneControl");
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
        compound.putBoolean("RedstoneControl", redstone_control);
        return compound;
    }

    public String getClassName() {
        return ("program$" + getPos().getX() + getPos().getY() + getPos().getZ() + "$" + classnum).replaceAll("-", "a");
    }

    public void update() {
        try {
            Compiler.getFileManager().removeFileObject(StandardLocation.SOURCE_PATH, "com/hacker/dy", getClassName() + ".java");
            Compiler.getFileManager().removeFileObject(StandardLocation.CLASS_PATH, "com/hacker/dy", getClassName() + ".class");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Compiler.getClassLoader().removeFileObject("com.hacker.dy." + getClassName());
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
                "import com.hacker.programblock.ProgramFunction;\n" +
                "import static com.hacker.programblock.ProgramUtils.*;\n" +
                "class " + getClassName() + " implements ProgramFunction {\n" +
                "\tprivate Map<String,Object> args=new HashMap<>();\n" +
                "\tprivate Object retval;\n" +
                "\tpublic " + getClassName() + "(){}\n" +
                "\tpublic void execute() throws Exception {\n" + "\t\ttry {\n" + "\t\t" + code.replaceAll("\n", "\n\t\t") + "\t\t}catch(Exception e){throw e;}" + "\n\t}\n" +
                "\tprivate int getX(){return " + getPos().getX() + ";}\n" +
                "\tprivate int getY(){return " + getPos().getY() + ";}\n" +
                "\tprivate int getZ(){return " + getPos().getZ() + ";}\n" +
                "\tpublic void putArg(String key,Object val) {\n" +
                "\t\targs.put(key,val);\n" +
                "\t}\n" +
                "\tpublic Object getArg(String key) {\n" +
                "\t\treturn args.get(key);\n" +
                "\t}\n" +
                "\tpublic Map<String, Object> getArgs() {\n" +
                "\t\treturn args;\n" +
                "\t}\n" +
                "\tpublic void setArgs(Map<String, Object> args) {\n" +
                "\t\tthis.args=args;\n" +
                "\t}\n" +
                "\tpublic Object getReturnValue() {\n" +
                "\t\treturn retval;\n" +
                "\t}\n" +
                "\tprivate void setReturnValue(Object val) {\n" +
                "\t\tretval=val;\n" +
                "\t}\n" +
                "}";
    }
}
