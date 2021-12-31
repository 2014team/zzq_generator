package com.generator.generator.db;


import java.sql.Connection;

import com.generator.generator.MyTable;

public abstract class DbConnection {
	

	/**
	 * 获取表
	 * @return
	 */
	public abstract MyTable getTable(String tableName);
	
	public Connection getConnection(){
		return DataBaseUtil.getConnection();
	}
	
}
