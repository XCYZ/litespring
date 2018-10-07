package org.litespring.test.v4;

import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.type.classreading.AnnotationMetaReadingVisitor;
import org.litespring.core.io.type.classreading.ClassMetaReadingVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class ClassReaderTest {
	@Test
	public void testGetClassMetaData() throws IOException {
		ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		ClassWriter  classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS); 
		ClassMetaReadingVisitor visitor = new ClassMetaReadingVisitor(classWriter);
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		assertEquals("org.litespring.service.v4.PetStoreService", visitor.getClassName());
		assertEquals("java.lang.Object", visitor.getSuperClassName());
		assertEquals(0, visitor.getInterfaces().length);
	}
	
	@Test
	public void testGetAnnotation() throws IOException {
		ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		ClassWriter  classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS); 
		AnnotationMetaReadingVisitor visitor = new AnnotationMetaReadingVisitor(classWriter);
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
//		assertEquals("org.litespring.service.v4.PetStoreService", visitor.getClassName());
//		assertEquals("java.lang.Object", visitor.getSuperClassName());
//		assertEquals(0, visitor.getInterfaces().length);
	}
}
