package com.hacker.programblock;

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
        return bytecode.toByteArray();
    }
}
