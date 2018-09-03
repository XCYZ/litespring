package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		BeanDefinition db = beanFactory.getBeanDefinition("petStore");
		assertTrue(db.isSingleton());
		assertFalse(db.isPrototype());
		assertEquals(BeanDefinition.SCOPE_DEFAULT, db.getScope());
		assertEquals(beanFactory.getBean("petStore"), beanFactory.getBean("petStore"));
		assertEquals("org.litespring.service.v1.PetStoreService", db.getClassName());
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
