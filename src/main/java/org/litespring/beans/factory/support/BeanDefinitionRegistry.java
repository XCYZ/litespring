package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
	public static final String ID_ATTR = "id";
	public static final String CLASS_ATTR = "class";
	public static final String SCOPE_ATTR = "scope";
	public static final String PROPERTY_REF="property";
	public static final String REF_ATTR="ref";
	public static final String VALUE_ATTR = "value";
	public static final String NAME_ATTR="name";
	BeanDefinition getBeanDefinition(String id);
	void registryBeanDefinition(String id,BeanDefinition beanDefinition);
}
