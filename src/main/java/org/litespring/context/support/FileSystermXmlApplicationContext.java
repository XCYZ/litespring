package org.litespring.context.support;

import org.litespring.core.io.FileSystermResource;
import org.litespring.core.io.Resource;

public class FileSystermXmlApplicationContext extends AbstractApplicationContext {
	public FileSystermXmlApplicationContext(String config) {
		super(config);
	}

	@Override
	public Resource getResourceByPath(String path) {
		return new FileSystermResource(path);
	}

}
