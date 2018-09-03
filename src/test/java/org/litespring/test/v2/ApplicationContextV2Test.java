package org.litespring.test.v2;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v2.PetStoreService;

public class ApplicationContextV2Test {
	@Test
	public void testGetBeanProperty() {
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
		PetStoreService ps = (PetStoreService)context.getBean("petStore");
		assertNotNull(ps.getAccountDao());
		assertNotNull(ps.getItemDao());
	}
}
