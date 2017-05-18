package me.minidigger.customentities.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Created by Yamakaja on 18.05.17.
 */
public class EntityClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        ClassWriter writer;
        try {
            ClassReader reader = new ClassReader(classfileBuffer);

            writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            ClassVisitor visitor = new EntityClassVisitor(Opcodes.ASM5, writer);

            reader.accept(visitor, 0);

        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return writer.toByteArray();
    }



}
