package com.hacker.programblock;

import org.apache.logging.log4j.LogManager;

import static org.lwjgl.opengl.GL20.*;

class Rending {
    private static int program;
    //    private static final String vsc = "#version 330\n" +
//            "layout (location = 0) in vec3 aPos;\n" +
//            "out vec4 ourColor;\n" +
//            "void main()\n" +
//            "{\n" +
//            "   gl_Position = vec4(aPos, 1.0);\n" +
//            "   ourColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n" +
//            "}";
//
//    private static final String psc = " #version 330\n" +
//            "in vec4 ourColor;\n" +
//            "out vec4 FragColor;\n" +
//            "void main()\n" +
//            "{\n" +
//            "   FragColor = ourColor;\n" +
//            "}";
    private static final String vsc = "#version 110" + '\n' +
            "attribute vec3 aPos;" + '\n' +
            "void main()" + '\n' +
            "{" + '\n' +
            "    gl_Position = vec4(aPos, 1.0);" + '\n' +
            "}";

    private static final String psc = "#version 110" + '\n' +
            "void main()" + '\n' +
            "{" + '\n' +
            "   gl_FragColor = vec4(1.0, 0.5, 0.2, 1.0);" + '\n' +
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
//        float[] vets = new float[]{
//                0, 0, 1,
//                0, 100, 1,
//                100, 100, 1,
//        };
        float[] vets = new float[]{
                0, 0.5f, 0,
                0.5f, -0.5f, 0,
                -0.5f, -0.5f, 0,
        };
        int vbo = glGenBuffers();
        glPushAttrib(GL_ALL_ATTRIB_BITS);
        glPushMatrix();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vets, GL_STATIC_DRAW);
        glUseProgram(program);
        int pos = glGetAttribLocation(program, "aPos");
        glEnableVertexAttribArray(pos);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(pos, 3, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glDisableVertexAttribArray(pos);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
        glPopMatrix();
        glPopAttrib();
    }
}
