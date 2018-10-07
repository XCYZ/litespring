package org.litespring.test.v4;


import static org.junit.Assert.*;
import org.junit.Test;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;

public class PackageResourceLoaderTest {
	@Test
	public void getResourcesTest() {
		PackageResourceLoader loader = new PackageResourceLoader();
		Resource[] resources =  loader.getResources("org.litespring.dao.v2");
		assertEquals(2, resources.length);
	}
}
