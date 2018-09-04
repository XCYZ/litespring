package org.litespring.beans.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CustomNumberEditor extends PropertyEditorSupport{
	private final boolean allowEmpty;
	

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(allowEmpty && (text == null || StringUtils.isBlank(text))) {
			setValue(null);
		}else {
			if(text == null || StringUtils.isBlank(text)) {
				throw new IllegalArgumentException("param can not be null or blank");
			}else {
				setValue(NumberUtils.createNumber(text));
			}
		}
	}
	
	

	public CustomNumberEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

}
