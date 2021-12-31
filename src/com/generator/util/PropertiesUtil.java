package com.generator.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties props = new Properties();

	public PropertiesUtil() {
	}

	public static void init(String propertiesName) {
		try {
			props.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(propertiesName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}
