package com.generator.domain.dto;
import com.generator.domain.entity.DatabaseConfig;
 
/**
 * @ClassName: DatabaseConfigDto
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:30
 */ 
public class DatabaseConfigDto extends DatabaseConfig{

	private static final long serialVersionUID = 1L;
	private  String projectConfigIdName;
	public String getProjectConfigIdName() {
		return projectConfigIdName;
	}
	public void setProjectConfigIdName(String projectConfigIdName) {
		this.projectConfigIdName = projectConfigIdName;
	}
	
	
	
	
}