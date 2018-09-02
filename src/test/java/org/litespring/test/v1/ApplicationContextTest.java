package org.litespring.test.v1;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystermXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {
	@Test
	public void testGetBeanByClassPathXmlApplicationContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService service = (PetStoreService)context.getBean("petStore");
		assertNotNull(service);
	}
	
	@Test
	public void testGetBeanByFileSystermXmlApplicationContext() {
		String homepath = System.getProperty("user.home");
		ApplicationContext context = new FileSystermXmlApplicationContext(homepath+"/workspace/litespring/src/test/resources/petstore-v1.xml");
		PetStoreService service = (PetStoreService)context.getBean("petStore");
		assertNotNull(service);
	}
}
