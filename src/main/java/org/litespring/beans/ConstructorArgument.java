package org.litespring.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConstructorArgument {
	private  List<ValueHolder> argumentValues = new ArrayList<>();
	public List<ValueHolder> getArgumentValues() {
		return Collections.unmodifiableList(argumentValues);
	}
	public int getArgumentCount() {
		return argumentValues.size();
	}
	
	public boolean isEmpty() {
		return getArgumentCount() == 0;
	}
	
	public void addArgumentValue(Object value) {
		argumentValues.add(new ValueHolder(value));
	}
	
	public void clean() {
		argumentValues.clear();
	}
	
	public static class ValueHolder{
		private Object value;
		
		public ValueHolder( Object value) {
			this.value = value;
		}


		public Object getValue() {
			return value;
		}
	}
}
