package org.litespring.beans.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import org.litespring.util.NumberUtils;
import org.litespring.util.StringUtils;


public class CustomNumberEditor extends PropertyEditorSupport{


	private final boolean allowEmpty;
	private final Class<? extends Number> numbetClass;
	private final NumberFormat format;
	
	public CustomNumberEditor(Class<? extends Number> numbetClass, boolean allowEmpty,NumberFormat format) {
		this.allowEmpty = allowEmpty;
		this.numbetClass = numbetClass;
		this.format = format;
	}
	
	public CustomNumberEditor(Class<? extends Number> numbetClass, boolean allowEmpty) {
		this(numbetClass, allowEmpty, null);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		}else if(format != null){
			setValue(NumberUtils.parseNumber(text, numbetClass, format));
		}else {
			setValue(NumberUtils.parseNumber(text, numbetClass));
		}
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Number) {
			super.setValue(NumberUtils.convertNumberToTargetClass((Number) value, numbetClass));
		}else {
			super.setValue(value);
		}
	}
	
	
	

}
