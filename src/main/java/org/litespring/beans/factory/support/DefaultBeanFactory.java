package org.litespring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
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
		Object instance = initBean(db);
		pupulateBean(instance, db);
		return instance;
	}
	
	/**
	 * 初始化bean
	 * @param db
	 * @return
	 */
	private Object initBean(BeanDefinition db) {
		try {
			return getClassLoader().loadClass(db.getClassName()).newInstance();
		} catch (Exception e) {
			throw new BeanCreationException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 反射注入属性
	 * @param instance
	 * @param bd
	 */
	private void pupulateBean(Object instance,BeanDefinition bd) {
		List<PropertyValue> propertyValues = bd.getPropertyValues();
		if(propertyValues.size()==0) {
			return;
		}
		BeanDefinitionResolver resolver = new BeanDefinitionResolver(this);
			try {
				for (PropertyValue propertyValue : propertyValues) {
					Object obj = propertyValue.getValue();
					String name = propertyValue.getName();
					Object resolveValue = resolver.resolveValueIfNecessary(obj);
					BeanInfo beanInfo = Introspector.getBeanInfo(instance.getClass());
					PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
					for (PropertyDescriptor propertyDescriptor : pds) {
						if(propertyDescriptor.getName().equals(name)) {
							propertyDescriptor.getWriteMethod().invoke(instance, resolveValue);
							break;
						}
					}
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}
	

}
