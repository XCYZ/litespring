package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.util.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	private final Map<String, Object>  singletonMaps = new ConcurrentHashMap<String, Object>();
	
	@Override
	public void registrySingleton(String id, Object obj) {
		Assert.notNull(id, "id can not be null");
		Object oldObj = singletonMaps.get(id);
		if(oldObj != null) {
			throw new IllegalStateException(String.format("can not registry singleton object because under the id %s the object %s already exits", id,oldObj));
		}
		singletonMaps.put(id, obj);
	}

	@Override
	public Object getSingleton(String id) {
		return singletonMaps.get(id);
	}

}
