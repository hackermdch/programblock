package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;

@SuppressWarnings("all")
public class EventBlockScreen extends Screen {
    private final EventBlockTileEntity eventBlock;

    public EventBlockScreen(EventBlockTileEntity eventBlock) {
        super(NarratorChatListener.EMPTY);
        this.eventBlock = eventBlock;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Rending.draw();
    }
}
