package com.hacker.programblock;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.StringWriter;
import java.util.function.Supplier;

public class CUpdateProgramBlock {
    public BlockPos pos;
    public String code;

    public CUpdateProgramBlock(BlockPos pos, String code) {
        this.pos = pos;
        this.code = code;
    }

    public CUpdateProgramBlock(PacketBuffer buf) {
        pos = buf.readBlockPos();
        code = buf.readString(Integer.MAX_VALUE / 4);
    }

    public void writePacketData(PacketBuffer buf) {
        buf.writeBlockPos(pos);
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
                prob.markDirty();
                StringWriter s = new StringWriter();
                try {
                    Class<?> z = Compiler.compile("com.hacker.dy", prob.getClassName(), prob.genSource(), s);
                    try {
                        Runnable r = (Runnable) z.newInstance();
                        r.run();
                    } catch (Exception e2) {
                        player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.runtime_error", e2.toString()));
                        for (StackTraceElement se : e2.getStackTrace()) {
                            player.getCommandSource().sendErrorMessage(new StringTextComponent(se.toString()));
                        }
                        e2.printStackTrace();
                    }
                } catch (Exception e) {
                    if (s.toString().isEmpty()) {
                        player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.compile_failed", e.toString()));
                        for (StackTraceElement se : e.getStackTrace()) {
                            player.getCommandSource().sendErrorMessage(new StringTextComponent(se.toString()));
                        }
                    } else {
                        player.getCommandSource().sendErrorMessage(new TranslationTextComponent("program.compile_failed", s.toString()));
                    }
                    e.printStackTrace();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
