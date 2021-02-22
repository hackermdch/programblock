package com.hacker.programblock;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final MemoryClassLoader classLoader;
    private final Map<URI, MemoryJavaFileObject> javaFileObjectMap = new HashMap<>();

    public MemoryFileManager(JavaFileManager fileManager, MemoryClassLoader classLoader) {
        super(fileManager);
        this.classLoader = classLoader;
    }

    private static URI fromLocation(Location location, String packageName, String relativeName) throws URISyntaxException {
        return new URI(location.getName() + "/" + packageName + "/" + relativeName);
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        try {
            MemoryJavaFileObject object = javaFileObjectMap.get(fromLocation(location, packageName, relativeName));
            if (object != null)
                return object;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return super.getFileForInput(location, packageName, relativeName);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        try {
            MemoryJavaFileObject object = new MemoryJavaFileObject(className, kind);
            classLoader.addFileObject(className, object);
            return object;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return classLoader;
    }

    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        if (file instanceof MemoryJavaFileObject) {
            return file.getName();
        }
        return super.inferBinaryName(location, file);
    }

    @Override
    public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
        Iterable<JavaFileObject> sr = super.list(location, packageName, kinds, recurse);
        List<JavaFileObject> res = new ArrayList<>();
        if (location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
            for (JavaFileObject object : javaFileObjectMap.values()) {
                if (object.getKind() == JavaFileObject.Kind.CLASS && object.getName().startsWith(packageName)) {
                    res.add(object);
                }
            }
            res.addAll(classLoader.listObjects());
        } else if (location == StandardLocation.SOURCE_PATH && kinds.contains(JavaFileObject.Kind.SOURCE)) {
            for (JavaFileObject object : javaFileObjectMap.values()) {
                if (object.getKind() == JavaFileObject.Kind.SOURCE && object.getName().startsWith(packageName)) {
                    res.add(object);
                }
            }
        }
        for (JavaFileObject object : sr) {
            res.add(object);
        }
        return res;
    }

    public void addJavaFileObject(Location location, String packageName, String relativeName, MemoryJavaFileObject object) throws URISyntaxException {
        URI uri = fromLocation(location, packageName, relativeName);
        javaFileObjectMap.remove(uri);
        javaFileObjectMap.put(uri, object);
    }

    public void removeFileObject(Location location, String packageName, String relativeName) throws URISyntaxException {
        javaFileObjectMap.remove(fromLocation(location, packageName, relativeName));
    }
}
