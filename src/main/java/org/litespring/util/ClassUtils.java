package org.litespring.util;

public class ClassUtils {
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		cl = Thread.currentThread().getContextClassLoader();
		if(cl == null) {
			cl = ClassUtils.class.getClassLoader();
			if(cl == null) {
				cl = ClassLoader.getSystemClassLoader();
			}
		}
		return cl;
	}
}
