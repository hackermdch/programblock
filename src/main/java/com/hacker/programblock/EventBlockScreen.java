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
//        glDisable(GL_TEXTURE_2D);
//        glBegin(GL_TRIANGLES);
//        glColor3f(1, 0, 0);
//        glVertex3d(0, 0, 0);
//        glColor3f(0, 1, 0);
//        glVertex3d(0, 100, 0);
//        glColor3f(0, 0, 1);
//        glVertex3d(100, 100, 0);
//        glEnd();
//        glEnable(GL_TEXTURE_2D);
        Rending.draw();
    }
}
