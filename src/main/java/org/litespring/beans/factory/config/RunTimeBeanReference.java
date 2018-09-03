package org.litespring.beans.factory.config;

public class RunTimeBeanReference {
	private final String beanName;

	public RunTimeBeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
