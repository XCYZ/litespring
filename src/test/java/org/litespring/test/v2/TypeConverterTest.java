package org.litespring.test.v2;
import static org.junit.Assert.*;

import org.junit.Test;
import org.litespring.beans.SimpelTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMisMatchException;

public class TypeConverterTest {
	@Test
	public void convertToInt() {
		TypeConverter converter = new SimpelTypeConverter();
		try {
			converter.convertIfNessary("3",Integer.class);
			converter.convertIfNessary("3.1",Integer.class);
		}catch (TypeMisMatchException e) {
			return;
		}
		fail();
	}
	
	@Test
	public void convertToBoolean() {
		
	}
}
