package com.generator.domain.dto;
import com.generator.domain.entity.GenerateTemplates;
 
/**
 * @ClassName: GenerateTemplatesDto
 * @Description: 模本生成管理
 * @author zhuzq
 * @date 2021年12月27日 09:37:26
 */ 
public class GenerateTemplatesDto extends GenerateTemplates{

	private static final long serialVersionUID = 1L;
	
	private String uniteConfigIdName;

	public String getUniteConfigIdName() {
		return uniteConfigIdName;
	}

	public void setUniteConfigIdName(String uniteConfigIdName) {
		this.uniteConfigIdName = uniteConfigIdName;
	}
	
	
	
}