package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {
	DefaultBeanFactory beanFactory = null;
	XmlBeanDefinitionReader reader = null;
	
	@Before
	public void setUp() {
		beanFactory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(beanFactory);
	}
	@Test
	public void testGetBean() {
		Resource resource = new ClassPathResource("petstore-v1.xml");
		reader.loadBeanDefinitions(resource);
		BeanDefinition bd = beanFactory.getBeanDefinition("petStore");
		assertEquals("org.litespring.service.v1.PetStoreService", bd.getClassName());
		PetStoreService service = (PetStoreService)beanFactory.getBean("petStore");
		assertNotNull(service);
	}
	
	@Test
	public void testInvalidBean() {
		Resource resource = new ClassPathResource("petstore-v1.xml");
		reader.loadBeanDefinitions(resource);
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
			Resource resource = new ClassPathResource("xxx.xml");
			reader.loadBeanDefinitions(resource);
			beanFactory.getBean("invalidBean");
		}catch (BeanStoreException e) {
			return;
		}
		Assert.fail("beanCreationException");
	}
}
