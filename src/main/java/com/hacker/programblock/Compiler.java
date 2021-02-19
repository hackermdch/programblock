package com.hacker.programblock;

import javax.tools.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compiler {
    private static MemoryClassLoader classLoader;
    private static MemoryFileManager fileManager;
    private static JavaCompiler compiler;
    private static boolean inited = false;

    public static Class<?> compile(String packageName, String className, CharSequence sourceCode) throws Exception {
        List<String> ops = new ArrayList<>();
        ops.add("-source");
        ops.add("1.8");
        ops.add("-target");
        ops.add("1.8");
        if (!inited) {
            compiler = ToolProvider.getSystemJavaCompiler();
            classLoader = new MemoryClassLoader(Thread.currentThread().getContextClassLoader());
            fileManager = new MemoryFileManager(compiler.getStandardFileManager(null, null, null), classLoader);
            inited = true;
        }
        MemoryJavaFileObject file = new MemoryJavaFileObject(className, sourceCode);
        fileManager.addJavaFileObject(StandardLocation.SOURCE_PATH, packageName, className + ".java", file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, ops, null, Collections.singletonList(file));
        task.call();
        return classLoader.loadClass(packageName + "." + className);
    }
}
