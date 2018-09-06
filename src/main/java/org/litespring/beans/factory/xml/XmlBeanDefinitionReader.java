package org.litespring.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanStoreException;
import org.litespring.beans.factory.config.RunTimeBeanReference;
import org.litespring.beans.factory.config.TypeStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.StringUtils;


public class XmlBeanDefinitionReader {
	public static final String ID_ATTR = "id";
	public static final String CLASS_ATTR = "class";
	public static final String SCOPE_ATTR = "scope";
	public static final String PROPERTY_REF="property";
	public static final String REF_ATTR="ref";
	public static final String VALUE_ATTR = "value";
	public static final String NAME_ATTR="name";
	public static final String CONSTRUCTOR_ARG_ELEMENT ="constructor-arg";
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
				parsePropertyElement(element, bd);
				parseConstructorArguments(element, bd);
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
	private void parsePropertyElement(Element element,BeanDefinition bd) {
		Iterator<Element> iterator = element.elementIterator(PROPERTY_REF);
		while(iterator.hasNext()) {
			Element e = iterator.next();
			String pname = e.attributeValue(NAME_ATTR);
			if(!StringUtils.hasText(pname)) {
				logger.fatal("Tag 'property' must have a valid name");
				return;
			}
			Object obj=parsePropertyValue(e,pname);
			PropertyValue propertyValue = new PropertyValue(pname, obj);
			bd.getPropertyValues().add(propertyValue);
		}
	}
	
	private Object parsePropertyValue(Element e,String propertyName) {
		boolean hasRefAttr = (e.attribute(REF_ATTR)!=null);
		boolean hasValueAttr = (e.attribute(VALUE_ATTR)!=null);
		if(hasRefAttr) {
			String refName = e.attributeValue(REF_ATTR);
			if(!StringUtils.hasText(refName)) {
				logger.error("ref is empty!");
			}
			RunTimeBeanReference ref = new RunTimeBeanReference(refName);
			return ref;
		}else if(hasValueAttr) {
			return  new TypeStringValue(e.attributeValue(VALUE_ATTR));
		}else {
			throw new RuntimeException("must specific a ref or a value");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void parseConstructorArguments(Element element,BeanDefinition bd) {
		Iterator<Element> iterator = element.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
		while(iterator.hasNext()) {
			Element e = iterator.next();
			parseConstructorArgument(e,bd);
		}
	}

	private void parseConstructorArgument(Element e, BeanDefinition bd) {
		Object value = parsePropertyValue(e, null);
		bd.getConstructorArgument().addArgumentValue(value);
	}
}
