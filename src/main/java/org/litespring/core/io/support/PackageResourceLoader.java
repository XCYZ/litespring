package org.litespring.core.io.support;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.litespring.core.io.FileSystermResource;
import org.litespring.core.io.Resource;
import org.litespring.util.Assert;
import org.litespring.util.ClassUtils;

public class PackageResourceLoader {
	private final ClassLoader classLoader;

	public PackageResourceLoader() {
		classLoader = ClassUtils.getDefaultClassLoader();
	}

	public PackageResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public Resource[] getResources(String basePackage) {
		Assert.notNull(basePackage, "basePackage can not be null");
		String location = ClassUtils.convertClassNameToResourcePath(basePackage);
		URL url = classLoader.getResource(location);
		File baseDir = new File(url.getFile());
		ClassFileColloctor coll = new ClassFileColloctor(baseDir);
		List<File> classFiles = coll.getAllClassFiles();
		int size = classFiles.size();
		Resource[] resources = new Resource[size];
		for(int i=0;i<size;i++) {
			resources[i] = new FileSystermResource(classFiles.get(i));
		}
		return resources;
	}
	
	
	
	public static boolean checkFileIsComponentClass(File classFile) {
		return true;
	}
	
	
	
static class ClassFileColloctor{
		private File rootDir;
		private List<File> classFiles = new ArrayList<>();

		public ClassFileColloctor(File rootDir) {
			this.rootDir = rootDir;
		}
		
		public ClassFileColloctor(String rootDir) {
			this.rootDir = new File(rootDir);
		}
		
		private void  setclassFiles(File dir){
			File[] files = dir.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					String fullPath = file.getAbsolutePath();
					if(fullPath.endsWith(".class")) {
						classFiles.add(file);
					}
				}else {
					setclassFiles(file);
				}
			}
		}
		
		public List<File> getAllClassFiles(){
			setclassFiles(rootDir);
			return classFiles;
		}
		
	}
	
}
