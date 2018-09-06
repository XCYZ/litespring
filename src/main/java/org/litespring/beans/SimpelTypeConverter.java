package org.litespring.beans;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import org.litespring.beans.propertyeditor.CustomBooleanEditor;
import org.litespring.beans.propertyeditor.CustomNumberEditor;
import org.litespring.util.ClassUtils;

public class SimpelTypeConverter implements TypeConverter {
	private Map<Class<?>,PropertyEditor> defaultEditors = null;
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convertIfNessary(Object value, Class<T> clazz) {
		if(ClassUtils.isAssignableValue(clazz, value)) {
			return (T)value;
		}else {
			if(value instanceof String) {
				PropertyEditor editor = findDefaultEditor(clazz);
				try {
					editor.setAsText((String)value);
					return (T)editor.getValue();
				}catch (IllegalArgumentException e) {
					throw new TypeMisMatchException(value,clazz);
				}
			
			}else {
				throw new RuntimeException("can not convert");
			}
		}
	}

	private PropertyEditor findDefaultEditor(Class<?> clazz) {
		PropertyEditor editor = getDefaultEditor(clazz);
		if(editor == null) {
			throw new RuntimeException("editor for"+clazz+"has not impelemented");
		}
		return editor;
	}

	private PropertyEditor getDefaultEditor(Class<?> clazz) {
		if(defaultEditors == null) {
			createDefaultEdtiors();
		}
		return defaultEditors.get(clazz);
	}

	private void createDefaultEdtiors() {
		defaultEditors = new HashMap<>(64);
		defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
		defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));
		defaultEditors.put(int.class, new CustomNumberEditor(int.class, false));
		defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

}
