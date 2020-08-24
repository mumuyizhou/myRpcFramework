package boot;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.javac.util.Assert;
import annotation.Expose;

/**
 * @author ZhouPan
 * @date 2020-08-24
 */
public class AnnotationScanner {
	private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);
	private Set<Class<?>> annotationSet;

	public AnnotationScanner(Set<Class<?>> annotationSet) {
		this.annotationSet = annotationSet;
	}

	public Set<Object> doScan() {
		Set<Object> result = new HashSet<>();
		URL rootUrl = Thread.currentThread().getContextClassLoader().getResource("");
		if (rootUrl == null) {
			throw new IllegalArgumentException("根目录位置为空");
		}
		try {
			Set<Class<?>> matchedClasses = scanClasses(new HashSet<>(), URLDecoder.decode(rootUrl.getFile(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static Set<Class<?>> scanClasses(Set<Class<?>> classes, String path) {
		File file = new File(path);
		if (!file.exists()) return classes;

		File[] fileList = file.listFiles(pathname ->
				pathname.isDirectory() || pathname.getName().endsWith(".class"));
		Assert.checkNonNull(fileList);
		// TODO: 2020/8/24 package扫描不正确 
		for (File f : fileList) {
			String s = f.getName();
			if (f.isDirectory()) {
				scanClasses(classes, f.getPath());
			} else {
				try {
					String[] packageName = path.split("\\\\");
					String s2 = packageName[packageName.length - 1] + "." +
							s.substring(0, s.length() - 6);
					System.out.println(s2);
					Class<?> clazz = Class.forName(s2);
					if (clazz.isAnnotationPresent(Expose.class)) {
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return classes;
	}

	public static String getStackTrace() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		return stack[stack.length - 1].getClassName();
	}

	public static void main(String[] args) {
		AnnotationScanner annotationScanner = new AnnotationScanner(null);
		annotationScanner.doScan();
	}
}
