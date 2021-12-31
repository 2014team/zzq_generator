package com.generator.domain.dto;
import com.generator.domain.entity.ParameterConfig;
 
/**
 * @ClassName: ParameterConfigDto
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:36
 */ 
public class ParameterConfigDto extends ParameterConfig{

	private static final long serialVersionUID = 1L;
	
	private String projectConfigIdName;

	public String getProjectConfigIdName() {
		return projectConfigIdName;
	}

	public void setProjectConfigIdName(String projectConfigIdName) {
		this.projectConfigIdName = projectConfigIdName;
	}

	
	
	
	
}