package com.hacker.programblock;

import javax.tools.JavaCompiler;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

public class Compiler {
    private static MemoryClassLoader classLoader;
    private static MemoryFileManager fileManager;
    private static JavaCompiler compiler;
    private static String classpath;
    private static boolean inited = false;

    public static boolean isInited() {
        return inited;
    }

    public static Class<?> compile(String packageName, String className, CharSequence sourceCode, Writer error) throws Exception {
        if (!inited) {
            compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                String s = "com.sun.tools.javac.api.JavacTool";
                File file = new File(System.getProperty("java.io.tmpdir") + "/tools.jar");
                InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("libs/tools.jar");
                FileOutputStream o = new FileOutputStream(file);
                int len;
                byte[] b = new byte[1024];
                while ((len = in.read(b)) > 0) {
                    o.write(b, 0, len);
                }
                o.close();
                in.close();
                URL[] urls = {file.toURI().toURL()};
                URLClassLoader cl = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
                Class<?> cz = cl.loadClass(s);
                Class<?> az = cz.asSubclass(JavaCompiler.class).asSubclass(JavaCompiler.class);
                compiler = (JavaCompiler) az.newInstance();
            }
            classLoader = new MemoryClassLoader(Thread.currentThread().getContextClassLoader());
            fileManager = new MemoryFileManager(compiler.getStandardFileManager(null, null, null), classLoader);
            classpath = "";
            File f = new File(System.getProperty("user.dir") + "/mods");
            if (f.exists() && f.isDirectory()) {
                StringBuilder sb = new StringBuilder();
                for (File ff : f.listFiles()) {
                    if (ff.getName().endsWith(".jar")) {
                        sb.append(ff.getAbsolutePath());
                        sb.append(";");
                    }
                }
                classpath = sb.toString();
            }
            inited = true;
        }
        MemoryJavaFileObject file = new MemoryJavaFileObject(className, sourceCode);
        fileManager.addJavaFileObject(StandardLocation.SOURCE_PATH, packageName.replaceAll("\\.", "/"), className + ".java", file);
        JavaCompiler.CompilationTask task = compiler.getTask(error, fileManager, null, Arrays.asList("-source", "1.8", "-target", "1.8", "-classpath", System.getProperty("java.class.path") + ";" + classpath), null, Collections.singletonList(file));
        task.call();
        return classLoader.loadClass(packageName + "." + className);
    }

    public static MemoryClassLoader getClassLoader() {
        return classLoader;
    }

    public static MemoryFileManager getFileManager() {
        return fileManager;
    }
}
