package com.generator.code.factory;

import com.generator.code.service.DbConnection;
import com.generator.code.service.impl.MySqlConnectionImpl;
import com.generator.code.service.impl.SqlserverConnectionImpl;
import com.generator.code.util.DataBaseUtil;

public class DbConnectionFactory {
	
	public static DbConnection getDbConnection(){
		String dbType = DataBaseUtil.getDbType();
		if(null != dbType){
			if(dbType.equals("mysql")){
				return new MySqlConnectionImpl();
				
			}else if(dbType.equals("sqlserver")){
				return new SqlserverConnectionImpl();
				
			}
		}
		return null;
	}
}
