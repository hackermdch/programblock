package com.hacker.programblock;

import net.minecraft.client.Minecraft;

public class GUIProxy {
    public static void openProgramBlock(ProgramBlockTileEntity programBlock) {
        Minecraft.getInstance().displayGuiScreen(new ProgramBlockScreen(programBlock));
    }
}
