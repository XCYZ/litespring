package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
	private String className;
	private String id;
	@Override
	public String getClassName() {
		return className;
	}
	
	public String getId() {
		return id;
	}

	public GenericBeanDefinition(String className, String id) {
		this.className = className;
		this.id = id;
	}
	
}
