package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.BeanStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	@Test
	public void testGetBean() {
		BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
		BeanDefinition bd = beanFactory.getBeanDefinition("petStore");
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getClassName());
		PetStoreService service = (PetStoreService)beanFactory.getBean("petStore");
		assertNotNull(service);
	}
	
	@Test
	public void testInvalidBean() {
		BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			beanFactory.getBean("invalidBean");
		}catch (BeanCreationException e) {
			return;
		}
		Assert.fail("beanCreationException");
	}
	@Test
	public void testInvalidXML() {
		try {
			BeanFactory beanFactory = new DefaultBeanFactory("xxx.xml");
			beanFactory.getBean("invalidBean");
		}catch (BeanStoreException e) {
			return;
		}
		Assert.fail("beanCreationException");
	}
}
