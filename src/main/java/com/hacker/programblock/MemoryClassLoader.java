package com.hacker.programblock;

import cpw.mods.modlauncher.ClassTransformer;
import cpw.mods.modlauncher.TransformingClassLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MemoryClassLoader extends ClassLoader {
    private final Map<String, MemoryJavaFileObject> javaFileObjectMap = new HashMap<>();

    public MemoryClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        MemoryJavaFileObject fileObject = javaFileObjectMap.get(name);
        if (fileObject != null) {
            byte[] bytecode = fileObject.getBytecode();
            if (getParent() instanceof TransformingClassLoader) {
                try {
                    Field f = TransformingClassLoader.class.getDeclaredField("classTransformer");
                    Method m = ClassTransformer.class.getDeclaredMethod("transform", byte[].class, String.class, String.class);
                    f.setAccessible(true);
                    m.setAccessible(true);
                    bytecode = (byte[]) m.invoke(f.get(getParent()), bytecode, name, "asm");
                } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            bytecode = new Transformer().transformClass(name, bytecode);
            return defineClass(name, bytecode, 0, bytecode.length);
        }
        return super.findClass(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name.endsWith(".class")) {
            String classname = name.substring(0, name.length() - ".class".length()).replace('/', '.');
            MemoryJavaFileObject fileObject = javaFileObjectMap.get(classname);
            if (fileObject != null && fileObject.getBytecode() != null) {
                return new ByteArrayInputStream(fileObject.getBytecode());
            }
        }
        return super.getResourceAsStream(name);
    }

    public void addFileObject(String name, MemoryJavaFileObject object) {
        javaFileObjectMap.remove(name);
        javaFileObjectMap.put(name, object);
    }

    public void removeFileObject(String name) {
        javaFileObjectMap.remove(name);
    }

    public Collection<MemoryJavaFileObject> listObjects() {
        return Collections.unmodifiableCollection(javaFileObjectMap.values());
    }
}
