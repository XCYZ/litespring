package org.litespring.test.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.PetStoreService;

public class ApplicationContextV4Test {
	@Test
	public void testGetBeanProperty() {
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v4.xml");
		PetStoreService ps = (PetStoreService)context.getBean("petStore");
		assertNotNull(ps.getAccountDao());
		assertNotNull(ps.getItemDao());
		assertEquals(1, ps.getVersion());
	}
}
