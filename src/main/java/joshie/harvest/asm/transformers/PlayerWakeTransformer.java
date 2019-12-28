package joshie.harvest.asm.transformers;

import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/** Removes the call to wakePlayer, in onUpdate when
 *  It is daytime. This is to allow the player to sleep at any time
 *  of day that they wish, in order to pass the time quicker.
 */
public class PlayerWakeTransformer extends AbstractASM {
    @Override
    public boolean isClass(String name) {
        return name.equals("net.minecraft.entity.player.EntityPlayer") || name.equals("aax");
    }

    @Override
    public boolean isVisitor() {
        return false;
    }

    private boolean isOnUpdate(String name, String descriptor) {
        return descriptor.equals("()V") && (name.equals("onUpdate") || name.equals("func_70071_h_") || name.equals("B_"));
    }

    private boolean isWakeupPlayer(String name, String descriptor) {
        return descriptor.equals("(ZZZ)V") && (name.equals("wakeUpPlayer") || name.equals("func_70999_a") || name.equals("a"));
    }

    private boolean isDaytime(String name, String descriptor) {
        return name.equals("fireSleepingTimeCheck") || descriptor.equals("()Z") && (name.equals("isDaytime") || name.equals("func_72935_r") || name.equals("D"));
    }

    @Override
    public byte[] transform(byte[] data) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(data);
        classReader.accept(classNode, 0);

        topLabel:
        for (MethodNode method : classNode.methods) {
            //System.out.println(method.name + " " + method.desc);
            if (isOnUpdate(method.name, method.desc)) {
                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof MethodInsnNode) {
                        MethodInsnNode mNode = ((MethodInsnNode) node);
                        //System.out.println(mNode.name + " " + mNode.desc);
                        if ((isWakeupPlayer(mNode.name, mNode.desc)) && method.instructions.get(i - 8) instanceof MethodInsnNode) {
                            MethodInsnNode previous = ((MethodInsnNode) method.instructions.get(i - 8));
                            //System.out.println(previous.name + " " + previous.desc);
                            if (isDaytime(previous.name, previous.desc)) {
                                Set<AbstractInsnNode> remove = new HashSet<>();
                                for (int j = 0; j <= 5; j++) {
                                    remove.add(method.instructions.get(i - j));
                                }

                                for (AbstractInsnNode instruction : remove) {
                                    method.instructions.remove(instruction);
                                }

                                break topLabel;
                            }
                        }
                    }
                }
            }
        }

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
