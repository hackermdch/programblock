package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("NullableProblems")
@OnlyIn(Dist.CLIENT)
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

    public void update() {
        Networking.INSTANCE.sendToServer(new CUpdateProgramBlock(programBlock.getPos(), codes.getText()));
    }

    @Override
    public void onClose() {
        assert minecraft != null;
        minecraft.keyboardListener.enableRepeatEvents(false);
    }

    @Override
    public void closeScreen() {
        update();
        super.closeScreen();
    }

    @Override
    public void tick() {
        codes.tick();
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        String s = codes.getText();
        int row = codes.getRow();
        int col = codes.getCol();
        this.init(minecraft, width, height);
        codes.setText(s);
        codes.setCursorRow(row);
        codes.setCursorCol(col);
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
            closeScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
