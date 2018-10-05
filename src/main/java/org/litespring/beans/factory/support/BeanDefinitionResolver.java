package org.litespring.beans.factory.support;


import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.config.RunTimeBeanReference;
import org.litespring.beans.factory.config.TypeStringValue;

public class BeanDefinitionResolver {
	private BeanFactory factory;

	public BeanDefinitionResolver(BeanFactory factory) {
		this.factory = factory;
	}
	/**
	 * 这个是一个隐含的递归方法
	 * @param obj
	 * @return
	 */
	public Object resolveValueIfNecessary(Object obj) {
		if(obj instanceof RunTimeBeanReference) {
			RunTimeBeanReference ref = (RunTimeBeanReference) obj;
			return factory.getBean(ref.getBeanName());
		}else if(obj instanceof TypeStringValue){
			TypeStringValue value = (TypeStringValue)obj;
			return value.getValue();
		}else {
			throw new RuntimeException(String.format("the value %s has not impelmented", obj));
		}
		
	}

}
