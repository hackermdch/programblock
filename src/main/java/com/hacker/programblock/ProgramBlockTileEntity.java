package com.hacker.programblock;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

@SuppressWarnings("NullableProblems")
public class ProgramBlockTileEntity extends TileEntity {
    public String code;

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
        compound.putString("Code", code);
        return compound;
    }

    public String getClassName() {
        return ("program$" + getPos().getX() + getPos().getY() + getPos().getZ()).replaceAll("-", "a");
    }

    public String genSource() {
        return "package com.hacker.dy;\n" +
                "import com.hacker.programblock.proxy.*;\n" +
                "class " + getClassName() + " implements Runnable {\n" +
                "\t public " + getClassName() + "(){}\n" +
                '\t' +
                "public void run() {\n" +
                "\t\t" + code.replaceAll("\n", "\n\t\t") +
                "\n\t}\n" +
                "}";
    }
}
