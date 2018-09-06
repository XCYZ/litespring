package org.litespring.beans.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.litespring.util.StringUtils;


public class CustomBooleanEditor extends PropertyEditorSupport{
	private final boolean allowEmpty;
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	
	public CustomBooleanEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		}else {
			if(TRUE.equals(text)) {
				setValue(true);
			}
			if(FALSE.equals(text)) {
				setValue(false);
			}
		}
	}

	
}
