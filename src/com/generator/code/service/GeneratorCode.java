package com.generator.code.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.generator.code.factory.DbConnectionFactory;
import com.generator.generator.MyTable;

/**
 * 代码生成器
 *
 */
public abstract class GeneratorCode {
	
	
	/**
	 * 获取模版路径
	 * @return
	 */
	public abstract String getTemplatePath();
	/**
	 * 获取要生成的表对象
	 * @return
	 */
	public static MyTable getTable(String tableName){
		DbConnection connection = DbConnectionFactory.getDbConnection();
		if(null != connection){
			return connection.getTable(tableName);
		}
		return null;
	}
	

	public static String getCurrentDateFormat(String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new Date());
	}
}
