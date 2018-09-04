package org.litespring.test.v2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.litespring.beans.propertyeditor.CustomBooleanEditor;

public class CustomBooleanEditorTest {
	@Test
	public void customBooleanEditorTest() {
		CustomBooleanEditor editor = new CustomBooleanEditor(true);
		editor.setAsText("true");
		Boolean obj1 = (Boolean)editor.getValue();
		assertTrue(obj1);
		editor.setAsText("");
		Object obj2 = editor.getValue();
		assertTrue(obj2 == null);
		editor.setAsText("false");
		assertFalse((Boolean)editor.getValue());
	}
}
