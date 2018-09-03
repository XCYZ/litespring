package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry 
implements ConfigurableBeanFactory,BeanDefinitionRegistry{
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
		if(db.isSingleton()) {
				Object singleObj = getSingleton(id);
				if(singleObj == null) {
					obj =  createBean(db);
					registrySingleton(id, obj);
					return obj;
				}
				return singleObj;
		}
		obj =  createBean(db);
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
	
	/**
	 * 反射创建对象
	 * @param db
	 * @return
	 */
	private Object createBean(BeanDefinition db) {
		try {
			return getClassLoader().loadClass(db.getClassName()).newInstance();
		} catch (Exception e) {
			throw new BeanCreationException(e.getMessage(), e);
		}
	}

}
