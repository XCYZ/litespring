package org.litespring.test.v2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.litespring.beans.propertyeditor.CustomNumberEditor;

public class CustomNumberEditorTest {
	@Test
	public void testCustomString() {
		CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);
		editor.setAsText("3");
		Object obj1 = editor.getValue();
		assertTrue(obj1 instanceof Integer);
		editor.setAsText("");
		Object obj2 = editor.getValue();
		assertTrue(obj2 == null);
		try {
			editor.setAsText("3.1");
		}catch (Exception e) {
			System.out.println("exception");
			return ;
		}
		fail();
	}
}
