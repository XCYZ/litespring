package org.litespring.beans;

public interface TypeConverter {

	<T> T convertIfNessary(Object obj, Class<T> clazz);

}
