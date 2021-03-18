package com.hacker.programblock;

import net.minecraft.client.Minecraft;

public class GUIProxy {
    public static void openProgramBlock(ProgramBlockTileEntity programBlock) {
        Minecraft.getInstance().displayGuiScreen(new ProgramBlockScreen(programBlock));
    }

    public static void openEventBlock(EventBlockTileEntity eventBlock) {
        Minecraft.getInstance().displayGuiScreen(new EventBlockScreen(eventBlock));
    }
}
