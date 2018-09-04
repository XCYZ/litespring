package org.litespring.beans.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

public class CustomBooleanEditor extends PropertyEditorSupport{
	private final boolean allowEmpty;
	public CustomBooleanEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(allowEmpty && (text == null || StringUtils.isBlank(text))) {
			setValue(null);
		}else {
			if(text == null || StringUtils.isBlank(text)) {
				throw new IllegalArgumentException("param can not be null or blank");
			}else {
				setValue(BooleanUtils.toBoolean(text));
			}
		}
	}
	
}
