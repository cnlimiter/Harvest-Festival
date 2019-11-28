package joshie.harvest.asm.transformers;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import joshie.harvest.calendar.provider.HFWorldProvider;

public class DimensionTransformer extends AbstractASM {
    private int aload;

    @Override
    public boolean isClass(String name) {
        return name.equals("net.minecraft.world.DimensionType") || name.equals("ayn");
    }

    @Override
    public ClassVisitor newInstance(ClassWriter writer) {
        return new DimensionTransformer.Visitor(writer);
    }

    public static class Visitor extends ClassVisitor {
        public Visitor(ClassWriter writer) {
            super(Opcodes.ASM4, writer);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
            if (("<clinit>".equals(name))) {
                return new MethodVisitor(Opcodes.ASM4, visitor) {
                    @Override
                    public void visitLdcInsn(Object cst) {
                        if (cst instanceof Type) {
                            String name = ((Type) cst).getClassName();
                            if ("ayp".equals(name) || "net.minecraft.world.WorldProviderSurface".equals(name)) {
                                Type type = Type.getType(HFWorldProvider.class);
                                super.visitLdcInsn(type);
                                return;
                            }
                        }
                        super.visitLdcInsn(cst);
                    }
                };
            }
            return visitor;
        }
    }
}
