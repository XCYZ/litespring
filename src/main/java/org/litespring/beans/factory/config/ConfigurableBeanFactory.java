package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {
	public void setClassLoader(ClassLoader classLoader);
	public ClassLoader getClassLoader();
}
