package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	BeanDefinition getBeanDefinition(String id);
	void registryBeanDefinition(String id,BeanDefinition beanDefinition);
}
