package org.litespring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.ConstructorArgument.ValueHolder;
import org.litespring.beans.SimpelTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.config.TypeStringValue;

public class ConstructorResolver {
	private final ConfigurableBeanFactory factory;
	//private final Log logger = LogFactory.getLog(getClass());

	public ConstructorResolver(ConfigurableBeanFactory factory) {
		this.factory = factory;
	}

	public Object autowireConstructor(final BeanDefinition bd) {
		Class<?> beanClass = null;
		try {
			beanClass = factory.getClassLoader().loadClass(bd.getClassName());
		} catch (ClassNotFoundException e) {
			throw new BeanCreationException(String.format("%s is not found", bd.getClassName()), e);
		}
		Constructor<?>[] constructors = beanClass.getConstructors();
		BeanDefinitionResolver resolver = new BeanDefinitionResolver(factory);
		ConstructorArgument argument = bd.getConstructorArgument();
		List<ValueHolder> holders = argument.getArgumentValues();
		int size = holders.size();
		SimpelTypeConverter converter = new SimpelTypeConverter();
		for (Constructor<?> constructor : constructors) {
			if(constructor.getParameterCount() == size) {
				Object[] argsToUse = new Object[size];
				Class<?>[] types = constructor.getParameterTypes();
				for(int i=0;i<types.length;i++) {
					Object value = holders.get(i).getValue();
					Object realValue = resolver.resolveValueIfNecessary(value);
					Class<?> type = types[i];
					if(value instanceof TypeStringValue) {
						if(type.isPrimitive()) {
							if(type.getSimpleName().equals("int")) {
								type = Integer.class;
							}
						}
						realValue = converter.convertIfNessary(realValue, type);
					}
					argsToUse[i] = realValue;
				}
				if(argsToUse[size-1] !=null) {
					try {
						return constructor.newInstance(argsToUse);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

}
