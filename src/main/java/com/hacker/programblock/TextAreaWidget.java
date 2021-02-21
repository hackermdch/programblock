package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

import static org.lwjgl.glfw.GLFW.*;

@SuppressWarnings({"NullableProblems", "unused"})
public class TextAreaWidget extends TextFieldWidget {
    private int col = 0;
    private int row = 1;
    private int scroll = 0;
    private int lineScrollOffset;

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
                    case GLFW_KEY_BACKSPACE:
                        if (this.isEnabled) {
                            this.field_212956_h = false;
                            this.delete(-1);
                            this.field_212956_h = Screen.hasShiftDown();
                        }
                        return true;
                    case GLFW_KEY_INSERT:
                    case GLFW_KEY_PAGE_UP:
                    case GLFW_KEY_PAGE_DOWN:
                    default:
                        return false;
                    case GLFW_KEY_ENTER:
                        addText("\n");
                        row++;
                        col = 0;
                        return true;
                    case GLFW_KEY_DELETE:
                        if (this.isEnabled) {
                            this.field_212956_h = false;
                            this.delete(1);
                            this.field_212956_h = Screen.hasShiftDown();
                        }
                        return true;
                    case GLFW_KEY_RIGHT:
                        String[] ss = getLines();
                        col++;
                        int tmpr = row;
                        if (col > ss[row - 1].length()) {
                            row++;
                        }
                        row = Math.min(row, ss.length);
                        if (tmpr != row)
                            col = 0;
                        col = MathHelper.clamp(col, 0, ss[row - 1].length());
                        return true;
                    case GLFW_KEY_LEFT:
                        ss = getLines();
                        col--;
                        tmpr = row;
                        if (col < 0) {
                            row--;
                        }
                        row = Math.max(row, 1);
                        if (tmpr != row)
                            col = ss[row - 1].length();
                        col = MathHelper.clamp(col, 0, ss[row - 1].length());
                        return true;
                    case GLFW_KEY_UP:
                        String[] st = getLines();
                        row--;
                        row = Math.max(row, 1);
                        if (col > st[row - 1].length()) {
                            col = st[row - 1].length();
                        }
                        return true;
                    case GLFW_KEY_DOWN:
                        String[] sa = getLines();
                        row++;
                        row = Math.min(row, sa.length);
                        if (col > sa[row - 1].length()) {
                            col = sa[row - 1].length();
                        }
                        return true;
                    case GLFW_KEY_HOME:
                        this.setCursorPositionZero();
                        return true;
                    case GLFW_KEY_END:
                        this.setCursorPositionEnd();
                        return true;
                }
            }
        }
    }

    @Override
    public void setCursorPositionZero() {
        row = 1;
        col = 0;
    }

    private String[] getLines() {
        String[] ss = text.split("\n");
        if (text.endsWith("\n")) {
            String[] sss = new String[ss.length + 1];
            System.arraycopy(ss, 0, sss, 0, ss.length);
            sss[ss.length] = "";
            ss = sss;
        }
        return ss;
    }

    @Override
    protected void delete(int num) {
        deleteFromCursor(num);
    }

    @Override
    public void deleteWords(int num) {
        deleteFromCursor(num);
    }

    @Override
    public void deleteFromCursor(int num) {
        if (!this.text.isEmpty()) {
            String[] ss = getLines();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                if (i == row - 1) {
                    if (num < 0) {
                        if (col == 0 && row != 1) {
                            row--;
                            col = ss[i - 1].length();
                            ss[i - 1] += ss[i];
                            ss[i] = "@$%^[{skip}]@$%^;";
                            break;
                        }
                        try {
                            sb.append(ss[i], 0, col + num);
                            sb.append(ss[i].substring(Math.min(col, ss[i].length())));
                        } catch (Exception ignored) {
                            break;
                        }
                        ss[i] = sb.toString();
                        col = MathHelper.clamp(col - 1, 0, ss[i].length());
                        break;
                    } else if (num > 0) {
                        try {
                            sb.append(ss[i], 0, col - num + 1);
                            sb.append(ss[i].substring(Math.min(col + 1, ss[i].length())));
                            if (col > ss[i].length() - 1) {
                                if (i + 1 < ss.length)
                                    if (row + 1 <= ss.length) {
                                        sb.append(ss[i + 1]);
                                        ss[i + 1] = "@$%^[{skip}]@$%^;";
                                    }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ss[i] = sb.toString();
                        col = MathHelper.clamp(col, 0, ss[i].length());
                        break;
                    }
                }
            }
            sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                if (ss[i].equals("@$%^[{skip}]@$%^;"))
                    continue;
                sb.append(ss[i]);
                if (i != ss.length - 1)
                    sb.append("\n");
            }
            this.text = sb.toString();
        }
    }

    @Override
    public void setText(String textIn) {
        if (this.validator.test(textIn)) {
            if (textIn.length() > this.maxStringLength) {
                this.text = textIn.substring(0, this.maxStringLength);
            } else {
                this.text = textIn;
            }

            this.setSelectionPos(this.cursorPosition);
            this.onTextChanged(textIn);
        }
    }

    @Override
    public void setCursorPositionEnd() {
        String[] ss = getLines();
        row = ss.length;
        col = ss[ss.length - 1].length();
    }

    public void setCursorRow(int row) {
        this.row = MathHelper.clamp(row, 1, getLines().length);
    }

    public void setCursorCol(int col) {
        this.col = col;
        if (!this.field_212956_h) {
            this.setCursorCol(this.col);
        }
        this.onTextChanged(this.text);
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
    public void writeText(String textToWrite) {
        if (this.validator.test(textToWrite)) {
            addText(textToWrite);
            col += textToWrite.length();
            onTextChanged(this.text);
        }
    }

    private void addText(String text) {
        String[] ss = getLines();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            if (i == row - 1) {
                sb.append(ss[i], 0, col);
                sb.append(text);
                sb.append(ss[i], col, ss[i].length());
                ss[i] = sb.toString();
                break;
            }
        }
        sb = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            sb.append(ss[i]);
            if (i != ss.length - 1)
                sb.append("\n");
        }
        this.text = sb.toString();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        scroll += (int) -delta;
        scroll = MathHelper.clamp(scroll, 0, getLines().length);
        return true;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            try {
                if (enableBackgroundDrawing) {
                    int i = this.isFocused() ? -1 : -6250336;
                    fill(matrixStack, this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, i);
                    fill(matrixStack, this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
                }
                int color = this.isEnabled ? this.enabledColor : this.disabledColor;
                String[] ss = getLines();
                boolean showCursor = this.isFocused() && cursorCounter / 6 % 2 == 0;
                boolean cursorInText;
                int curX;
                int curY;
                int scrooll = 0;
                {
                    int mainr = row - 1;
                    int x = this.enableBackgroundDrawing ? this.x + 4 : this.x;
                    int y = this.enableBackgroundDrawing ? this.y + (mainr - scroll) * 8 : this.y;
                    int x1 = x;
                    String s = this.fontRenderer.func_238412_a_(ss[mainr], this.getAdjustedWidth());
                    boolean flag = ss[mainr].length() > s.length() && (col > ss[mainr].indexOf(s) + s.length() - 1 || col < ss[mainr].indexOf(s));
                    int srind = col - ss[mainr].indexOf(s);
                    if (flag) {
                        scrooll = col - s.length();
                        s = this.fontRenderer.func_238412_a_(ss[mainr].substring(Math.max(0, scrooll)), this.getAdjustedWidth());
                        srind = col - ss[mainr].indexOf(s);
                    }
                    if (!s.isEmpty()) {
                        x1 = this.fontRenderer.func_238407_a_(matrixStack, this.textFormatter.apply(s.substring(0, MathHelper.clamp(srind, 0, s.length())), col), x, y, color);
                    }
                    curX = x1;
                    curY = y;
                    cursorInText = col < ss[mainr].length();
                    if (cursorInText) {
                        curX = x1 - 1;
                        --x1;
                    }
                    if (!s.isEmpty()) {
                        this.fontRenderer.func_238407_a_(matrixStack, this.textFormatter.apply(s.substring(Math.min(s.length(), srind)), col), x1, y, color);
                    }
                }
                for (int i = scroll; i < ss.length; i++) {
                    int x = this.enableBackgroundDrawing ? this.x + 4 : this.x;
                    int y = this.enableBackgroundDrawing ? this.y + (i - scroll) * 8 : this.y;
                    if (i != row - 1) {
                        String s2 = this.fontRenderer.func_238412_a_(ss[i].substring(MathHelper.clamp(scrooll, 0, ss[i].length())), getAdjustedWidth());
                        if (!s2.isEmpty()) {
                            this.fontRenderer.func_238407_a_(matrixStack, this.textFormatter.apply(s2, 0), x, y, color);
                        }
                    }
                }
                if (showCursor) {
                    if (cursorInText) {
                        AbstractGui.fill(matrixStack, curX, curY - 1, curX + 1, curY + 1 + 9, -3092272);
                    } else {
                        this.fontRenderer.drawStringWithShadow(matrixStack, "_", curX, curY, color);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}