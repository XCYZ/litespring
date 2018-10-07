package org.litespring.core.io.type.classreading;

import java.util.LinkedHashSet;
import java.util.Set;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

public class AnnotationMetaReadingVisitor extends ClassMetaReadingVisitor{
	private final Set<String> annotationSet = new LinkedHashSet<>();
	public AnnotationMetaReadingVisitor(ClassVisitor cw) {
		super(cw);
	}

	@Override
	public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
		String className = Type.getType(desc).getClassName();
		annotationSet.add(className);
		return new AnnotationAttributeReadingVisitor();
	}
	
	

}
