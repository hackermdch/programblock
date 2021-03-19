package com.hacker.programblock;

import org.apache.logging.log4j.LogManager;

import static org.lwjgl.opengl.GL30.*;

class Rendering {
    private static final int program;
    private static final String vsc = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 color;\n" +
            "out vec4 ourColor;\n" +
            "void main()\n" +
            "{\n" +
            "   gl_Position = vec4(aPos, 1.0);\n" +
            "   ourColor = color;\n" +
            "}";

    private static final String psc = " #version 330 core\n" +
            "in vec4 ourColor;\n" +
            "out vec4 FragColor;\n" +
            "void main()\n" +
            "{\n" +
            "   FragColor = ourColor;\n" +
            "}";

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

    private static void begin() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glPushMatrix();
        glEnable(GL_MULTISAMPLE);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glUseProgram(program);
    }

    private static void EnableAA() {
        glEnable(GL_POINT_SMOOTH);
        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_POLYGON_SMOOTH);
    }

    private static void end() {
        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
        glPopMatrix();
        glPopAttrib();
    }

    public static void draw() {
        float[] vertices = {
                0.5f, 0.5f, 0.0f, 1, 0, 0, 1f,
                0.5f, -0.5f, 0.0f, 0, 1, 0, 1f,
                -0.5f, -0.5f, 0.0f, 0, 0, 1, 1f,
                -0.5f, 0.5f, 0f, 0, 1, 1, 1,
        };
        begin();
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        int[] inds = {0, 2, 1, 3, 2, 0};
        int ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, inds, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 28, 0);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 28, 12);

        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        end();
    }
}
