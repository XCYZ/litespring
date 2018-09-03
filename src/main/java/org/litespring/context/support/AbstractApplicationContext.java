package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext{
	private DefaultBeanFactory factory;
	private ClassLoader classLoader;
	
	public AbstractApplicationContext(String config) {
		this(config,null);
	}
	
	public AbstractApplicationContext(String config,ClassLoader cl) {
		setClassLoader(cl);
		factory =  new DefaultBeanFactory();
		factory.setClassLoader(getClassLoader());
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = getResourceByPath(config);
		reader.loadBeanDefinitions(resource);
	}
	
	@Override
	public Object getBean(String id) {
		return factory.getBean(id);
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader != null?classLoader:ClassUtils.getDefaultClassLoader();
	}

	public abstract Resource getResourceByPath(String path);
}
