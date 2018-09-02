package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;

import static org.litespring.beans.factory.support.BeanDefinitionRegistry.*;

public class XmlBeanDefinitionReader {
	private BeanDefinitionRegistry registry = null;

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}
	
	@SuppressWarnings("unchecked")
	public void loadBeanDefinitions(Resource resource) {
		InputStream inputStream = null;
		try {
			inputStream = resource.getInputStream();
			SAXReader sr = new SAXReader();
			Document doc = sr.read(inputStream);
			Element root = doc.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element element = iterator.next();
				String id = element.attributeValue(ID_ATTR);
				String className = element.attributeValue(CLASS_ATTR);
				BeanDefinition bd = new GenericBeanDefinition(className, id);
				registry.registryBeanDefinition(id, bd);
			}
		} catch (Exception e) {
			throw new BeanStoreException(e.getMessage(), e);
		}finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					inputStream = null;
				}
			}
		}
	}
}
