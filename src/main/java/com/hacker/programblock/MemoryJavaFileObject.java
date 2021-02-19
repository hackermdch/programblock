package com.hacker.programblock;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class MemoryJavaFileObject extends SimpleJavaFileObject {
    private final CharSequence sourcecode;
    private ByteArrayOutputStream bytecode;

    public MemoryJavaFileObject(String classname, CharSequence sourcecode) throws URISyntaxException {
        super(new URI(classname), Kind.SOURCE);
        this.sourcecode = sourcecode;
    }

    public MemoryJavaFileObject(String classname, Kind kind) throws URISyntaxException {
        super(new URI(classname), kind);
        sourcecode = null;
    }

    public MemoryJavaFileObject(URI uri, Kind kind) {
        super(uri, kind);
        sourcecode = null;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return sourcecode;
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return bytecode = new ByteArrayOutputStream();
    }

    @Override
    public InputStream openInputStream() throws IOException {
        return new ByteArrayInputStream(getBytecode());
    }

    public byte[] getBytecode() {
        ClassReader r = new ClassReader(bytecode.toByteArray());
        ClassNode node = new ClassNode();
        r.accept(node, 0);
        node.access += Opcodes.ACC_PUBLIC;
        ClassWriter w = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        node.accept(w);
        return w.toByteArray();
    }
}
