package com.hacker.programblock;

import javax.tools.JavaCompiler;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.Collections;

public class Compiler {
    private static MemoryClassLoader classLoader;
    private static MemoryFileManager fileManager;
    private static JavaCompiler compiler;
    private static boolean inited = false;

    public static Class<?> compile(String packageName, String className, CharSequence sourceCode) throws Exception {
        if (!inited) {
            compiler = ToolProvider.getSystemJavaCompiler();
            classLoader = new MemoryClassLoader(Thread.currentThread().getContextClassLoader());
            fileManager = new MemoryFileManager(compiler.getStandardFileManager(null, null, null), classLoader);
            inited = true;
        }
        MemoryJavaFileObject file = new MemoryJavaFileObject(className, sourceCode);
        fileManager.addJavaFileObject(StandardLocation.SOURCE_PATH, packageName, className + ".java", file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, Arrays.asList("-source", "1.8", "-target", "1.8"), null, Collections.singletonList(file));
        task.call();
        return classLoader.loadClass(packageName + "." + className);
    }
}
