package com.hacker.programblock;

import org.apache.logging.log4j.LogManager;

import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL33.*;

class Rendering {
    private static final int program;
    private static final String vsc = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 color;\n" +
            "out vec4 ourColor;\n" +
            "const float w = 960;\n" +
            "const float h = 540;\n" +
            "void main()\n" +
            "{\n" +
            "   vec4 pos = vec4(aPos, 1.0);\n" +
            "   float x = pos.x;\n" +
            "   float y = pos.y;\n" +
            "   pos.x = (x - w) / (w + 1);\n" +
            "   pos.y = (h - y) / (h - 1);\n" +
            "   gl_Position = pos;\n" +
            "   ourColor = color;\n" +
            "}";

    private static final String psc = " #version 330 core\n" +
            "in vec4 ourColor;\n" +
            "out vec4 FragColor;\n" +
            "void main()\n" +
            "{\n" +
            "   FragColor = ourColor;\n" +
            "}";
    private static final int[] rect = {0, 1, 2, 1, 0, 3};

    static {
        program = glCreateProgram();
        int vs = glCreateShader(GL_VERTEX_SHADER);
        int ps = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vs, vsc);
        glShaderSource(ps, psc);
        glCompileShader(vs);
        glCompileShader(ps);
        int success;
        success = glGetShaderi(vs, GL_COMPILE_STATUS);
        if (success == 0) {
            String str = glGetShaderInfoLog(vs);
            LogManager.getLogger().error("error: vertex shader compilation failed\n" + str);
        }
        glGetShaderi(ps, GL_COMPILE_STATUS);
        if (success == 0) {
            String str = glGetShaderInfoLog(ps);
            LogManager.getLogger().error("error: fragment shader compilation failed\n" + str);
        }
        glAttachShader(program, vs);
        glAttachShader(program, ps);
        glLinkProgram(program);
        success = glGetProgrami(program, GL_LINK_STATUS);
        if (success == 0) {
            String str = glGetProgramInfoLog(program);
            LogManager.getLogger().error("error: shader program linking failed\n" + str);
        }
        glDeleteShader(vs);
        glDeleteShader(ps);
    }

    public static void begin() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glPushMatrix();
        glEnable(GL_MULTISAMPLE);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glUseProgram(program);
    }

    public static void end() {
        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
        glPopMatrix();
        glPopAttrib();
    }

    public static void draw(float[] vertices, int[] indexs) {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        int ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexs, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 28, 0);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 28, 12);
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, indexs.length, GL_UNSIGNED_INT, 0);
    }

    public static void draw(int mode, float[] vertices) {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 28, 0);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 28, 12);
        glBindVertexArray(vao);
        glDrawArrays(mode, 0, vertices.length);
    }

    public static void drawText(String text, float x, float y, int size, float r, float g, float b) {
        RenderingC.drawText(new String(text.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), x, y, size, r, g, b);
        glUseProgram(program);
    }

    public static void drawRect(float x, float y, float width, float height, float[] colors) {
        if (colors == null)
            colors = new float[]{
                    0, 0, 0, 1,
                    0, 0, 0, 1,
                    0, 0, 0, 1,
                    0, 0, 0, 1
            };
        float[] vertices = {
                x + width, y, 0.0f, colors[0], colors[1], colors[2], colors[3],
                x, y + height, 0.0f, colors[4], colors[5], colors[6], colors[7],
                x + width, y + height, 0.0f, colors[8], colors[9], colors[10], colors[11],
                x, y, 0f, colors[12], colors[13], colors[14], colors[15],
        };
        draw(vertices, rect);
    }
}
