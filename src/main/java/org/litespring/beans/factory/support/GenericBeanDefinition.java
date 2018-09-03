package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
	private String className;
	private String id;
	private String scope=SCOPE_DEFAULT;
	private boolean isSingleton=true;
	private boolean isPrototype=false;
	@Override
	public String getClassName() {
		return className;
	}
	
	public String getId() {
		return id;
	}

	public GenericBeanDefinition(String className, String id) {
		this.className = className;
		this.id = id;
	}

	@Override
	public boolean isSingleton() {
		return isSingleton;
	}

	@Override
	public boolean isPrototype() {
		return isPrototype;
	}

	@Override
	public String getScope() {
		return scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.isSingleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.isPrototype = SCOPE_PROTOTYPE.equals(scope);
	}
	
}
