package com.hacker.programblock;

import net.minecraftforge.fml.common.Mod;

@Mod("programblock")
class Hacker {
    static String SOURCE_CODE =
            "package com.hacker.dy;" +
                    "class Test{" +
                    "public static void print(){" +
                    "System.out.println(\"hello world\");" +
                    "}" +
                    "}" +
                    ";";

    public Hacker() {
        try {
            Class<?> z = Compiler.compile("com.hacker.dy", "Test", SOURCE_CODE);
            z.getDeclaredMethod("print").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
