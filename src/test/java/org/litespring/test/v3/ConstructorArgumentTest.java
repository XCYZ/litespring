package org.litespring.test.v3;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.ConstructorArgument.ValueHolder;
import org.litespring.beans.factory.config.RunTimeBeanReference;
import org.litespring.beans.factory.config.TypeStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

public class ConstructorArgumentTest {
	@Test
	public void testConstructorArgument() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertEquals("org.litespring.service.v3.PetStoreService", bd.getClassName());
		ConstructorArgument argument = bd.getConstructorArgument();
		List<ValueHolder> vhs = argument.getArgumentValues();
		assertTrue(vhs.size() == 3);
		RunTimeBeanReference ref1 = (RunTimeBeanReference)vhs.get(0).getValue();
		assertTrue(ref1.getBeanName().equals("accountDao"));
		RunTimeBeanReference ref2 = (RunTimeBeanReference)vhs.get(1).getValue();
		assertTrue(ref2.getBeanName().equals("itemDao"));
		TypeStringValue value = (TypeStringValue)vhs.get(2).getValue();
		assertTrue(value.getValue().equals("1"));
	}
}
