package com.hacker.programblock;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

import javax.tools.StandardLocation;
import java.net.URISyntaxException;

@SuppressWarnings("NullableProblems")
public class ProgramBlockTileEntity extends TileEntity {
    public String code;
    private int classnum = -1;

    public ProgramBlockTileEntity() {
        super(Hacker.programblock_tile.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        code = nbt.getString("Code");
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
