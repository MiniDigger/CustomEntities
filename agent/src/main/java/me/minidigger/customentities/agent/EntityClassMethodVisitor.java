package me.minidigger.customentities.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Yamakaja on 18.05.17.
 */
public class EntityClassMethodVisitor extends MethodVisitor {

    private String version;

    public EntityClassMethodVisitor(int api, MethodVisitor mv, String version) {
        super(api, mv);
        this.version = version;
    }

    @Override
    public void visitCode() {
        super.visitCode();

        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "net/minecraft/server/" + version + "/Entity", "getName", "()Ljava/lang/String;", false);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "org/bukkit/Bukkit", "broadcastMessage", "(Ljava/lang/String;)I", false);

    }
}