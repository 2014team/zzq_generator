
package com.generator.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private static String sysRootPath = "";

	private static String classpath = "";
	static {
		classpath = Global.class.getResource("/").getPath();
		classpath = classpath.substring(1, classpath.length() - 1);
		if (!classpath.substring(0, 1).equals("/") && !classpath.substring(1, 2).equals(":")) {
			classpath = "/" + classpath;
		}
		/**
		 * 系统根路径
		 */
		sysRootPath = classpath;
		if (sysRootPath != null) {
			File f = new File(sysRootPath);
			sysRootPath = f.getParentFile().getParent();
			sysRootPath = sysRootPath.replaceAll("\\\\", "/");
		}
	}

	public static String getClassPath() {
		return classpath;
	}

	public static String getSysRootPath() {
		return sysRootPath;
	}
}