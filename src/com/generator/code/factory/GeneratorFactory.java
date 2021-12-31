package com.generator.code.factory;

import com.generator.generator.db.DataBaseUtil;
import com.generator.generator.go.GeneratorCode;
import com.generator.generator.go.impl.MysqlGeneratorImpl;
import com.generator.generator.go.impl.SqlserverGeneratorImpl;

public class GeneratorFactory {
	
	public static GeneratorCode getGenerator(){
		
		String dbType = DataBaseUtil.getDbType();
		if(null != dbType){
			if(dbType.equals("mysql")){
				return new MysqlGeneratorImpl();
				
			}else if(dbType.equals("sqlserver")){
				return new SqlserverGeneratorImpl();
				
			}
		}
		return null;
	}

}
