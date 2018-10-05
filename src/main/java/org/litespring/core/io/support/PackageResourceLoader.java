package org.litespring.core.io.support;

import java.io.File;
import java.net.URL;

import org.litespring.core.io.Resource;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

public class PackageResourceLoader {
	private final ClassLoader classLoader;

	public PackageResourceLoader() {
		classLoader = ClassUtils.getDefaultClassLoader();
	}

	public PackageResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public Resource[] getResources(String basePackage) {
		Assert.notNull(basePackage, "basePackage can not be null");
		String location = ClassUtils.convertClassNameToResourcePath(basePackage);
		URL url = classLoader.getResource(location);
		File baseDir = new File(url.getFile());
		return null;
	}
	
	
}
