package org.litespring.beans.factory.config;

public interface SingletonBeanRegistry {
	 void registrySingleton(String id,Object obj);
	 Object getSingleton(String id); 
}
