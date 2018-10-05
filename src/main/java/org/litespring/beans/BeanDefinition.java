package org.litespring.beans;

import java.util.List;

public interface BeanDefinition {
	public final static String SCOPE_DEFAULT = "";
	public final static String SCOPE_SINGLETON = "singleton";
	public final static String SCOPE_PROTOTYPE = "prototype";
	String getClassName();
	String getId();

	boolean isSingleton();

	boolean isPrototype();

	String getScope();
	
	void setScope(String scope);

	List<PropertyValue> getPropertyValues();
	ConstructorArgument getConstructorArgument();
	boolean hasConstructorArgument();


}
