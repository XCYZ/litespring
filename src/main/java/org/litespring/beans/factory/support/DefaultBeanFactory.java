package org.litespring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.BeanStoreException;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory implements BeanFactory {
	public static final String ID_ATTR = "id";
	public static final String CLASS_ATTR = "class";
	private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	
	public DefaultBeanFactory(String config) {
		loadBeanDefinitions(config);
	}

	@Override
	public BeanDefinition getBeanDefinition(String id) {
		return beanDefinitionMap.get(id);
	}

	@Override
	public Object getBean(String id) {
		Object obj = null;
		BeanDefinition db = getBeanDefinition(id);
		if(db == null) {
			throw new BeanCreationException("beanDefinition does not exist");
		}
		try {
			obj =  ClassUtils.getDefaultClassLoader().loadClass(db.getClassName()).newInstance();
		} catch (Exception e) {
			throw new BeanCreationException(e.getMessage(), e);
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	private void loadBeanDefinitions(String config) {
		InputStream inputStream = null;
		try {
			inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(config);
			SAXReader sr = new SAXReader();
			Document doc = sr.read(inputStream);
			Element root = doc.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element element = iterator.next();
				String id = element.attributeValue(ID_ATTR);
				String className = element.attributeValue(CLASS_ATTR);
				BeanDefinition bd = new GenericBeanDefinition(className, id);
				beanDefinitionMap.put(id, bd);
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
