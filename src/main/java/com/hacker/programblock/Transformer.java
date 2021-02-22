package com.hacker.programblock;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class Transformer {
    public byte[] transformClass(String name, byte[] bytecode) {
        ClassReader r = new ClassReader(bytecode);
        ClassNode node = new ClassNode();
        r.accept(node, 0);
        node.access += Opcodes.ACC_PUBLIC;
        ClassWriter w = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        node.accept(w);
        return w.toByteArray();
    }
}
