package com.hacker.programblock;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("NullableProblems")
@OnlyIn(Dist.CLIENT)
public class ProgramBlockScreen extends Screen {
    private final ProgramBlockTileEntity programBlock;
    protected TextAreaWidget codes;
    protected Button editor;
    protected Button save;
    protected Button control;
    private boolean openEdit = false;

    public ProgramBlockScreen(ProgramBlockTileEntity programBlock) {
        super(NarratorChatListener.EMPTY);
        this.programBlock = programBlock;
    }

    @Override
    protected void init() {
        codes = new TextAreaWidget(this.font, this.width / 2 - 150, 50, 300, 100, new TranslationTextComponent("program.code"));
        codes.setMaxStringLength(Integer.MAX_VALUE);
        codes.setText(programBlock.code);
        codes.setCursorPositionEnd();
        save = addButton(new Button(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, new TranslationTextComponent("program.save"), (e) -> {
            if (!openEdit)
                closeScreen();
        }));
        control = addButton(new Button(this.width / 2 - 75 + 4, this.height / 4 + 90 + 12, 150, 20, new TranslationTextComponent("program.redstone_control.enable"), (e) -> {
            programBlock.redstone_control = !programBlock.redstone_control;
            control.setMessage(programBlock.redstone_control ? new TranslationTextComponent("program.redstone_control.enable") : new TranslationTextComponent("program.redstone_control.disble"));
        }));
        editor = addButton(new Button(this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, new TranslationTextComponent("program.open_editor"), (e) -> {
            if (!openEdit) {
                Thread t = new Thread(() -> {
                    InputStream s = getClass().getClassLoader().getResourceAsStream("assets/programblock/Editor.exe");
                    if (s != null) {
                        try {
                            File f = new File(System.getProperty("java.io.tmpdir") + "/Editor.exe");
                            FileOutputStream o = new FileOutputStream(f);
                            byte[] b = new byte[1024];
                            int len;
                            while ((len = s.read(b)) > 0) {
                                o.write(b, 0, len);
                            }
                            o.close();
                            s.close();
                            ProcessBuilder pb = new ProcessBuilder(f.getAbsolutePath());
                            Process p = pb.start();
                            p.getOutputStream().write(codes.getText().getBytes(StandardCharsets.UTF_8));
                            p.getOutputStream().close();
                            openEdit = true;
                            getCodes().redirect = true;
                            p.waitFor();
                            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            StringBuilder sb = new StringBuilder();
                            while (br.ready()) {
                                sb.append(br.readLine());
                                if (br.ready())
                                    sb.append('\n');
                            }
                            br.close();
                            getCodes().setText(sb.toString());
                            openEdit = false;
                            getCodes().redirect = false;
                        } catch (IOException | InterruptedException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        }));
        children.add(codes);
        setFocusedDefault(codes);
        codes.setFocused2(true);
    }

    public void update() {
        programBlock.code = codes.getText();
        Networking.INSTANCE.sendToServer(new CUpdateProgramBlock(programBlock.getPos(), codes.getText()));
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

    public TextAreaWidget getCodes() {
        return codes;
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        String s = codes.getText();
        int row = codes.getRow();
        int col = codes.getCol();
        boolean rs = codes.redirect;
        this.init(minecraft, width, height);
        codes.setText(s);
        codes.setCursorRow(row);
        codes.setCursorCol(col);
        codes.redirect = rs;
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
            if (!openEdit)
                closeScreen();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
