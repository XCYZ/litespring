package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	protected final Log logger = LogFactory.getLog(getClass());

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
				String scope = element.attributeValue(SCOPE_ATTR);
				if(scope != null) {
					bd.setScope(scope);
				}
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
	
	@SuppressWarnings("unchecked")
	public void parsePropertyElement(Element element,BeanDefinition bd) {
		Iterator<Element> iterator = element.elementIterator(PROPERTY_REF);
		while(iterator.hasNext()) {
			Element e = iterator.next();
			String pname = e.attributeValue(NAME_ATTR);
			if(StringUtils.isBlank(pname)) {
				logger.fatal("Tag 'property' must have a valid name");
				return;
			}
			
		}
	}
	
//	public Object parsePropertyValue(Element e,BeanDefinition bd,String propertyName) {
//		boolean hasRefAttr = (e.attribute(REF_ATTR)==null);
//		boolean hasValueAttr = (e.attribute(VALUE_ATTR)==null);
//	}
}
