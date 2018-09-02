package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
	public ClassPathXmlApplicationContext(String config) {
		super(config);
	}

	@Override
	public Resource getResourceByPath(String path) {
		return new ClassPathResource(path,getClassLoader());
	}

}
