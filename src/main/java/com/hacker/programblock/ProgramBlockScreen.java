package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.glfw.GLFW;

public class ProgramBlockScreen extends Screen {
    private final ProgramBlockTileEntity programBlock;
    protected TextAreaWidget codes;

    public ProgramBlockScreen(ProgramBlockTileEntity programBlock) {
        super(NarratorChatListener.EMPTY);
        this.programBlock = programBlock;
    }

    @Override
    protected void init() {
        codes = new TextAreaWidget(this.font, this.width / 2 - 150, 50, 300, 100, new TranslationTextComponent("program.code"));
        codes.setMaxStringLength(Integer.MAX_VALUE);
        children.add(codes);
        setFocusedDefault(codes);
        codes.setFocused2(true);
    }

    @Override
    public void tick() {
        codes.tick();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        codes.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            assert minecraft != null;
            minecraft.displayGuiScreen(null);
            return true;
        }
//        if (keyCode == GLFW.GLFW_KEY_ENTER) {
//            codes.setText(codes.getText() + "\n");
//            return true;
//        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
