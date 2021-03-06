package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;

@SuppressWarnings("all")
public class EventBlockScreen extends Screen {
    private final EventBlockTileEntity eventBlock;
    private static final float[] bg = {
            0.5f, 0, 0, 0.5f,
            0, 0.5f, 0, 0.5f,
            0, 0, 0.5f, 0.5f,
            0, 0.5f, 0.5f, 0.5f
    };

    public EventBlockScreen(EventBlockTileEntity eventBlock) {
        super(NarratorChatListener.EMPTY);
        this.eventBlock = eventBlock;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Rendering.begin();
        Rendering.drawRect(0, 0, 1922, 1080, bg);
        Rendering.drawRect(0, 100, 200, 200, null);
        Rendering.drawText("hello world", 200, 0, 1, 1, 0, 0);
        Rendering.drawText("hello world你妈$了", 500, 0, -3, 1, 0, 0);
        Rendering.drawText("hello world", 200, 100, 2, 1, 0, 1);
        Rendering.drawText("hello world opengjlfikl", 0, 300, 3, 1, 0, 0);
        Rendering.drawText("hello world opengjlfikl", 0, 450, 4, 1, 0, 0);
        Rendering.drawText("hello world opengjlfikl", 0, 600, 8, 1, 0, 0);
        Rendering.end();
    }
}
