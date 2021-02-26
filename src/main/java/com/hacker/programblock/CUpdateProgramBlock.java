package com.hacker.programblock;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.StringWriter;
import java.util.function.Supplier;

public class CUpdateProgramBlock {
    public BlockPos pos;
    public String code;
    public boolean redcon;

    public CUpdateProgramBlock(BlockPos pos, String code, boolean redcon) {
        this.pos = pos;
        this.code = code;
        this.redcon = redcon;
    }

    public CUpdateProgramBlock(PacketBuffer buf) {
        pos = buf.readBlockPos();
        redcon = buf.readBoolean();
        code = buf.readString(Integer.MAX_VALUE / 4);
    }

    public void writePacketData(PacketBuffer buf) {
        buf.writeBlockPos(pos);
        buf.writeBoolean(redcon);
        buf.writeString(code);
    }

    public void processPacket(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            assert player != null;
            TileEntity tileentity = player.world.getTileEntity(pos);
            if (tileentity instanceof ProgramBlockTileEntity) {
                ProgramBlockTileEntity prob = (ProgramBlockTileEntity) tileentity;
                prob.code = code;
                prob.redstone_control = redcon;
                prob.markDirty();
                StringWriter s = new StringWriter();
                try {
                    prob.update();
                    Compiler.compile("com.hacker.dy", prob.getClassName(), prob.genSource(), s);
                } catch (Exception e) {
                    ProgramBlockTileEntity.compileError(s, e, player);
                    return;
                }
                player.getCommandSource().sendFeedback(new TranslationTextComponent("program.compile_success"), false);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
