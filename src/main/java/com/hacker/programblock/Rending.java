package com.hacker.programblock;

import org.apache.logging.log4j.LogManager;

import static org.lwjgl.opengl.GL20.*;

class Rending {
    private static int program;
    private static final String vsc = "#version 110" + '\n' +
            "attribute vec3 pos;" + '\n' +
            "attribute vec3 color;" + '\n' +
            "varying vec3 Color;" + '\n' +
            "void main()" + '\n' +
            "{" + '\n' +
            "   gl_Position = vec4(pos,1.0);" + '\n' +
            "   Color = color;" + '\n' +
            "}";

    private static final String psc = "#version 110" + '\n' +
            "varying vec3 Color;\n" +
            "void main()\n" +
            "{\n" +
            "    gl_FragColor = vec4(Color,1.0);\n" +
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

    public static void draw() {
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glPushMatrix();
        float[] vertices = {
                0f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f,  // 右上
                0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,   // 右下
                -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,   // 左下
                -0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.0f,    // 左上
        };
        int VBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        int[] indices = {
                0, 1, 3,
                1, 2, 3
        };
        int EBO = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(program);
        glDrawElements(GL_TRIANGLES, 3, GL_UNSIGNED_INT, 0);
        glPopMatrix();
        glPopAttrib();
    }
}
