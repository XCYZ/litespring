package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	public static final String ID_ATTR = "id";
	public static final String CLASS_ATTR = "class";
	BeanDefinition getBeanDefinition(String id);
	void registryBeanDefinition(String id,BeanDefinition beanDefinition);
}
