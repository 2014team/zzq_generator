package com.generator.domain.dto;
import com.generator.domain.entity.TemplateConfig;
 
/**
 * @ClassName: TemplateConfigDto
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:04
 */ 
public class TemplateConfigDto extends TemplateConfig{

	private static final long serialVersionUID = 1L;
	
	private String projectConfigIdName;

	public String getProjectConfigIdName() {
		return projectConfigIdName;
	}

	public void setProjectConfigIdName(String projectConfigIdName) {
		this.projectConfigIdName = projectConfigIdName;
	}
	
	
	
}