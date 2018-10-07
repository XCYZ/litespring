package org.litespring.core.io.type.classreading;

import org.litespring.util.ClassUtils;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassMetaReadingVisitor extends ClassAdapter{
	private String className;
	private String superClassName;
	private String[] interfaces;
	private boolean isInterface;
	private boolean isAbstract;
	private boolean isFinal;
	public ClassMetaReadingVisitor(ClassVisitor cw) {
		super(cw);
	}
	


	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		className = ClassUtils.convertResourcePathToClassName(name);
		isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
		isAbstract = (access & Opcodes.ACC_ABSTRACT) != 0;
		isFinal = (access & Opcodes.ACC_FINAL) != 0;
		if(superName != null) {
			superClassName = ClassUtils.convertResourcePathToClassName(superName);
		}
		int len = interfaces.length;
		this.interfaces = new String[len];
		for (int i=0;i<len;i++) {
			this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
		}
	}

	public String getClassName() {
		return className;
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public String[] getInterfaces() {
		return interfaces;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public boolean isFinal() {
		return isFinal;
	}
}
