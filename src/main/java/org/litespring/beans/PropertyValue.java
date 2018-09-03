package org.litespring.beans;

public class PropertyValue {
	private final String name;
	private final Object value;
	private boolean isConverted = false;
	private Object convertedValue;
	
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public boolean isConverted() {
		return isConverted;
	}

	public Object getConvertedValue() {
		return convertedValue;
	}
	
	
}
