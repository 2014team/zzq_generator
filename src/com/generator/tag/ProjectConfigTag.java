
package com.generator.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generator.domain.entity.ProjectConfig;
import com.generator.service.ProjectConfigService;
import com.generator.util.SpringConfigUtil;

public class ProjectConfigTag {
	

	public static ProjectConfigService  projectConfigService = (ProjectConfigService) SpringConfigUtil
			.getBean("projectConfigServiceImpl");

	public static List<ProjectConfig> getList(){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		List<ProjectConfig> list = projectConfigService.select(paramMap);
		return list;
	}
	
	
	
}
