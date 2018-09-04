package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.RunTimeBeanReference;
import org.litespring.beans.factory.support.BeanDefinitionResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v2.AccountDao;

public class BeanDefinitionResolverTest {
	@Test
	public void testResolveRuntimeBeanReferrence() {
		 DefaultBeanFactory factory = new DefaultBeanFactory();
		   XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		   Resource resource =  new ClassPathResource("petstore-v2.xml");
		   reader.loadBeanDefinitions(resource);
		   BeanDefinitionResolver resolver = new BeanDefinitionResolver(factory);
		   RunTimeBeanReference ref = new RunTimeBeanReference("accountDao");
		   Object obj = resolver.resolveValueIfNecessary(ref);
		   Assert.assertNotNull(obj);
		   Assert.assertTrue(obj instanceof AccountDao);
		   
	}
	
}
