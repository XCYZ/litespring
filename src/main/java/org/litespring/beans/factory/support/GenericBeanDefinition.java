package org.litespring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition {
	private String className;
	private String id;
	private String scope=SCOPE_DEFAULT;
	private boolean isSingleton=true;
	private boolean isPrototype=false;
	private List<PropertyValue> properties = new ArrayList<>();
	private ConstructorArgument argument = new ConstructorArgument();
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

	@Override
	public List<PropertyValue> getPropertyValues() {
		return properties;
	}

	@Override
	public ConstructorArgument getConstructorArgument() {
		return argument;
	}

	@Override
	public boolean hasConstructorArgument() {
		return argument.getArgumentCount() != 0;
	}
	
}
