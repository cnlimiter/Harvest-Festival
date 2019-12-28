package joshie.harvest.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import joshie.harvest.asm.transformers.AbstractASM;
import net.minecraft.launchwrapper.IClassTransformer;

public class RealTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] data) {
        byte[] modified = data;
        for (AbstractASM a : HFTransformer.asm) {
            if (a.isClass(transformedName)) {
                if (a.isVisitor()) {
                    ClassReader cr = new ClassReader(modified);
                    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
                    ClassVisitor cv = a.newInstance(cw);
                    cr.accept(cv, ClassReader.EXPAND_FRAMES);
                    modified = cw.toByteArray();
                }

                modified = a.transform(modified);
            }
        }

        return modified;
    }

}
