package org.litespring.beans;

public class TypeMisMatchException extends RuntimeException {
	private Object value;
	private Class<?> clazz;
	private static final long serialVersionUID = 2452339152843230248L;
	
	public TypeMisMatchException(Object value, Class<?> clazz) {
		this.value = value;
		this.clazz =clazz;
	}


}
