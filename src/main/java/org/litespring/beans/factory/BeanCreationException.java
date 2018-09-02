package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

public class BeanCreationException extends BeansException {

	private static final long serialVersionUID = 8845870518920157747L;

	public BeanCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanCreationException(String message) {
		super(message);
	}
	
	

	

}
