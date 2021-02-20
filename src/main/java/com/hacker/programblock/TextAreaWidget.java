package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TextAreaWidget extends TextFieldWidget {
    private int col = 0;
    private int row = 0;

    public TextAreaWidget(FontRenderer p_i232260_1_, int p_i232260_2_, int p_i232260_3_, int p_i232260_4_, int p_i232260_5_, ITextComponent p_i232260_6_) {
        super(p_i232260_1_, p_i232260_2_, p_i232260_3_, p_i232260_4_, p_i232260_5_, p_i232260_6_);
    }

    public TextAreaWidget(FontRenderer p_i232259_1_, int p_i232259_2_, int p_i232259_3_, int p_i232259_4_, int p_i232259_5_, @Nullable TextFieldWidget p_i232259_6_, ITextComponent p_i232259_7_) {
        super(p_i232259_1_, p_i232259_2_, p_i232259_3_, p_i232259_4_, p_i232259_5_, p_i232259_6_, p_i232259_7_);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (!this.canWrite()) {
            return false;
        } else {
            this.field_212956_h = Screen.hasShiftDown();
            if (Screen.isSelectAll(keyCode)) {
                this.setCursorPositionEnd();
                this.setSelectionPos(0);
                return true;
            } else if (Screen.isCopy(keyCode)) {
                Minecraft.getInstance().keyboardListener.setClipboardString(this.getSelectedText());
                return true;
            } else if (Screen.isPaste(keyCode)) {
                if (this.isEnabled) {
                    this.writeText(Minecraft.getInstance().keyboardListener.getClipboardString());
                }

                return true;
            } else if (Screen.isCut(keyCode)) {
                Minecraft.getInstance().keyboardListener.setClipboardString(this.getSelectedText());
                if (this.isEnabled) {
                    this.writeText("");
                }

                return true;
            } else {
                switch (keyCode) {
                    case 259:
                        if (this.isEnabled) {
                            this.field_212956_h = false;
                            this.delete(-1);
                            this.field_212956_h = Screen.hasShiftDown();
                        }

                        return true;
                    case 260:
                    case 264:
                    case 265:
                    case 266:
                    default:
                        return false;
                    case 267:
                        return true;
                    case 261:
                        if (this.isEnabled) {
                            this.field_212956_h = false;
                            this.delete(1);
                            this.field_212956_h = Screen.hasShiftDown();
                        }
                        return true;
                    case 262:
                        if (Screen.hasControlDown()) {
                            this.setCursorPosition(this.getNthWordFromCursor(1));
                        } else {
                            this.moveCursorBy(1);
                        }

                        return true;
                    case 263:
                        if (Screen.hasControlDown()) {
                            this.setCursorPosition(this.getNthWordFromCursor(-1));
                        } else {
                            this.moveCursorBy(-1);
                        }

                        return true;
                    case 268:
                        this.setCursorPositionZero();
                        return true;
                    case 269:
                        this.setCursorPositionEnd();
                        return true;
                }
            }
        }
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (!this.canWrite()) {
            return false;
        } else if (SharedConstants.isAllowedCharacter(codePoint)) {
            if (this.isEnabled) {
                this.writeText(Character.toString(codePoint));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

    }
}