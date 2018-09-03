package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	public static final String ID_ATTR = "id";
	public static final String CLASS_ATTR = "class";
	public static final String SCOPE_ATTR = "scope";
	BeanDefinition getBeanDefinition(String id);
	void registryBeanDefinition(String id,BeanDefinition beanDefinition);
}
