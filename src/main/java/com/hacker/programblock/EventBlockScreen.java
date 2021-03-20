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
        Rendering.drawRect(0, 0, 1922, 1080, bg);
    }
}
