package org.litespring.test.v1;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystermResource;
import org.litespring.core.io.Resource;

public class ResourceTest {
	@Test
	public void testClassPathResource() {
		Resource resource = new ClassPathResource("petstore-v1.xml");
		InputStream is = null;
		try {
			is = resource.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(is);
	}
	
	@Test
	public void testFileSystermResource() {
		String homePath = System.getProperty("user.home");
		Resource resource = new FileSystermResource(homePath+"/workspace/litespring/src/test/resources/petstore-v1.xml");
		InputStream is = null;
		try {
			is = resource.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(is);
	}

}
