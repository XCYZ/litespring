package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory implements ConfigurableBeanFactory,BeanDefinitionRegistry {
	private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	private ClassLoader classLoader = null;
	
	public DefaultBeanFactory() {
	}

	@Override
	public BeanDefinition getBeanDefinition(String id) {
		return beanDefinitionMap.get(id);
	}

	@Override
	public Object getBean(String id) {
		Object obj = null;
		BeanDefinition db = getBeanDefinition(id);
		if(db == null) {
			throw new BeanCreationException("beanDefinition does not exist");
		}
		try {
			obj =  getClassLoader().loadClass(db.getClassName()).newInstance();
		} catch (Exception e) {
			throw new BeanCreationException(e.getMessage(), e);
		}
		return obj;
	}

	@Override
	public void registryBeanDefinition(String id, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(id, beanDefinition);
	}

	@Override
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader != null?classLoader:ClassUtils.getDefaultClassLoader();
	}
	

}
