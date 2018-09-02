package org.litespring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.litespring.util.Assert.*;

public class FileSystermResource implements Resource {
	private String filePath;
	private File file;

	public FileSystermResource(String config) {
		notNull(config, "path can not be null");
		filePath = config;
		file = new File(filePath);
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(file);
	}

	@Override
	public String getDescription() {
		return "filesysterm resource";
	}

}
