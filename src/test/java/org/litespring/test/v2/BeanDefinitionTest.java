package org.litespring.test.v2;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RunTimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class BeanDefinitionTest {
   @Test
   public void testGetBeanDefinition() {
	   DefaultBeanFactory factory = new DefaultBeanFactory();
	   XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	   Resource resource =  new ClassPathResource("petstore-v2.xml");
	   reader.loadBeanDefinitions(resource);
	   BeanDefinition bd = factory.getBeanDefinition("petStore");
	   List<PropertyValue> propertyValues = bd.getPropertyValues();
	   Assert.assertTrue(propertyValues.size()==2);
	   PropertyValue propertyValue = getPropertyValue("accountDao",propertyValues);
	   Assert.assertNotNull(propertyValue);
	   Assert.assertTrue(propertyValue.getValue() instanceof RunTimeBeanReference);
   }

   private PropertyValue getPropertyValue(String string, List<PropertyValue> propertyValues) {
	return null;
   }
}
