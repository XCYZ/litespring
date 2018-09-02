package org.litespring.core.io;

import java.io.InputStream;

import org.litespring.util.ClassUtils;

public class ClassPathResource implements Resource {
	private String classPath;
	private ClassLoader classLoader;

	public ClassPathResource(String classPath) {
		this(classPath, null);
	}
	
	

	public ClassPathResource(String classPath, ClassLoader classLoader) {
		this.classPath = classPath;
		this.classLoader = classLoader != null?classLoader:ClassUtils.getDefaultClassLoader();
	}



	@Override
	public InputStream getInputStream() {
		return classLoader.getResourceAsStream(classPath);
	}

	@Override
	public String getDescription() {
		return "classPath resource";
	}

}
