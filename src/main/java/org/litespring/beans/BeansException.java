package org.litespring.beans;

public class BeansException extends RuntimeException {

	private static final long serialVersionUID = 4792615718473005294L;

	public BeansException(String message) {
		super(message);
	}

	public BeansException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
