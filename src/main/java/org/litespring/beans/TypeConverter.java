package org.litespring.beans;

public interface TypeConverter {

	<T> T convertIfNessary(String string, Class<T> clazz);

}
