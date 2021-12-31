package com.generator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.ProjectConfigDao;
import com.generator.domain.entity.ProjectConfig;
import com.generator.service.ProjectConfigService;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.domain.vo.ProjectConfigVo;
import com.generator.domain.dto.ProjectConfigDto;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminServiceLog;

/**
 * @ClassName: ProjectConfigServiceImpl
 * @Description: 项目配置管理
 * @author zhuzq
 * @date 2021年12月28日 16:37:21
 */
@Service
public class ProjectConfigServiceImpl extends BaseServiceImpl<ProjectConfig,Integer>  implements ProjectConfigService {
	
	@Autowired
	private ProjectConfigDao projectConfigDao;


	/**
	 * @Title: saveProjectConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	@AdminServiceLog(description="项目配置管理保存")
	@Override
	public boolean saveProjectConfig(ProjectConfigVo projectConfigVo) {
		// ProjectConfigVo转ProjectConfig
		ProjectConfig projectConfig = convertProjectConfig(projectConfigVo);
		Integer result = projectConfigDao.save(projectConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteProjectConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigId
	 * @return
	 */
	@AdminServiceLog(description="项目配置管理 删除")
	@Override
	public boolean deleteProjectConfig(Integer id) {
		Integer result = projectConfigDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigIdArr
	 * @return
	 */
	@AdminServiceLog(description="项目配置管理 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return projectConfigDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateProjectConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	@AdminServiceLog(description="项目配置管理 批量修改")
	@Override
	public boolean updateProjectConfig(ProjectConfigVo projectConfigVo) {
		// ProjectConfigVo转ProjectConfig
		ProjectConfig projectConfig = convertProjectConfig(projectConfigVo);
		Integer result = projectConfigDao.update(projectConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getProjectConfig
	 * @Description: 根据projectConfigId获取项目配置管理
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigId
	 * @return
	 */
	 @AdminServiceLog(description="项目配置管理根据projectConfigId获取项目配置管理")
	@Override
	public ProjectConfigDto getProjectConfig(Integer projectConfigId) {
		ProjectConfigDto projectConfigDTO = null;
		ProjectConfig projectConfig = projectConfigDao.get(projectConfigId);
		if (null != projectConfig) {
			projectConfigDTO = convertProjectConfigDto(projectConfig);
		}
		return projectConfigDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="项目配置管理分页查找")
	@Override
	public AdminResultByPage findByPage(ProjectConfigVo projectConfigVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectConfigVo", projectConfigVo);
		paramMap.put("page", jsonResult);

		int count = projectConfigDao.findByPageCount(paramMap);

		if (count > 0) {
			List<ProjectConfigDto> dataList = null;
			List<ProjectConfig> projectConfigList = findByPage(paramMap);
			if (null != projectConfigList && projectConfigList.size() > 0) {
				dataList = new ArrayList<ProjectConfigDto>();
				for (ProjectConfig projectConfig : projectConfigList) {
					// ProjectConfig转ProjectConfigDTO
					ProjectConfigDto projectConfigDTO = convertProjectConfigDto(projectConfig);
					dataList.add(projectConfigDTO);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	@Override
	public String checkParam(ProjectConfigVo projectConfigVo) {
	    String projectName = projectConfigVo.getprojectName();
		if (StringUtils.isBlank(projectName)) {
			return "项目名称不能为空";
		}
		Integer orderId = projectConfigVo.getOrderId();
		if (null == orderId) {
			return "排序号不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	@Override
	public String checkUnique(ProjectConfigVo ProjectConfigVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectName", ProjectConfigVo.getprojectName());
		List<ProjectConfig> projectConfigList = projectConfigDao.select(paramMap);
		if (null == projectConfigList || projectConfigList.size() < 1) {
			return null;
		}

		Integer id = ProjectConfigVo.getId();
		if (null != id) {
			for (ProjectConfig entity : projectConfigList) {
				if (!entity.getId().equals(id) && entity.getprojectName().equals(ProjectConfigVo.getprojectName())) {
					return "项目名称已经存在";
				}
			}
		} else {
			return "项目名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertProjectConfig
	 * @Description: ProjectConfigVo转ProjectConfig
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	private ProjectConfig convertProjectConfig(ProjectConfigVo projectConfigVo) {
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setId(projectConfigVo.getId());
		projectConfig.setprojectName(projectConfigVo.getprojectName());
		projectConfig.setOrderId(projectConfigVo.getOrderId());
		projectConfig.setCreateDate(projectConfigVo.getCreateDate());
		projectConfig.setUpdateDate(projectConfigVo.getUpdateDate());
		return projectConfig;
	}

	/**
	 * @Title: convertProjectConfigDto
	 * @Description: ProjectConfig转ProjectConfigDto
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfig
	 * @return
	 */
	private ProjectConfigDto convertProjectConfigDto(ProjectConfig projectConfig) {
		ProjectConfigDto dto = new ProjectConfigDto();
		dto.setId(projectConfig.getId());
		dto.setprojectName(projectConfig.getprojectName());
		dto.setOrderId(projectConfig.getOrderId());
		dto.setCreateDate(projectConfig.getCreateDate());
		dto.setUpdateDate(projectConfig.getUpdateDate());
		return dto;
	}
	
}
