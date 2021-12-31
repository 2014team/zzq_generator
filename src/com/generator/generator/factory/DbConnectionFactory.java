package com.generator.generator.factory;

import com.generator.generator.db.DataBaseUtil;
import com.generator.generator.db.DbConnection;
import com.generator.generator.db.impl.MySqlConnectionImpl;
import com.generator.generator.db.impl.SqlserverConnectionImpl;

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
