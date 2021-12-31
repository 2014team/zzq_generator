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
import com.generator.dao.TemplateConfigDao;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.entity.TemplateConfig;
import com.generator.service.TemplateConfigService;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.domain.vo.TemplateConfigVo;
import com.generator.domain.dto.TemplateConfigDto;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminServiceLog;

/**
 * @ClassName: TemplateConfigServiceImpl
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:05
 */
@Service
public class TemplateConfigServiceImpl extends BaseServiceImpl<TemplateConfig,Integer>  implements TemplateConfigService {
	
	@Autowired
	private TemplateConfigDao templateConfigDao;
	@Autowired
	private ProjectConfigDao projectConfigDao;


	/**
	 * @Title: saveTemplateConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	@AdminServiceLog(description="模板配置保存")
	@Override
	public boolean saveTemplateConfig(TemplateConfigVo templateConfigVo) {
		// TemplateConfigVo转TemplateConfig
		TemplateConfig templateConfig = convertTemplateConfig(templateConfigVo);
		Integer result = templateConfigDao.save(templateConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteTemplateConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigId
	 * @return
	 */
	@AdminServiceLog(description="模板配置 删除")
	@Override
	public boolean deleteTemplateConfig(Integer id) {
		Integer result = templateConfigDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigIdArr
	 * @return
	 */
	@AdminServiceLog(description="模板配置 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return templateConfigDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateTemplateConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	@AdminServiceLog(description="模板配置 批量修改")
	@Override
	public boolean updateTemplateConfig(TemplateConfigVo templateConfigVo) {
		// TemplateConfigVo转TemplateConfig
		TemplateConfig templateConfig = convertTemplateConfig(templateConfigVo);
		Integer result = templateConfigDao.update(templateConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getTemplateConfig
	 * @Description: 根据templateConfigId获取模板配置
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigId
	 * @return
	 */
	 @AdminServiceLog(description="模板配置根据templateConfigId获取模板配置")
	@Override
	public TemplateConfigDto getTemplateConfig(Integer templateConfigId) {
		TemplateConfigDto templateConfigDTO = null;
		TemplateConfig templateConfig = templateConfigDao.get(templateConfigId);
		if (null != templateConfig) {
			templateConfigDTO = convertTemplateConfigDto(templateConfig);
		}
		return templateConfigDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="模板配置分页查找")
	@Override
	public AdminResultByPage findByPage(TemplateConfigVo templateConfigVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateConfigVo", templateConfigVo);
		paramMap.put("page", jsonResult);

		int count = templateConfigDao.findByPageCount(paramMap);

		if (count > 0) {
			List<TemplateConfigDto> dataList = null;
			List<TemplateConfig> templateConfigList = findByPage(paramMap);
			if (null != templateConfigList && templateConfigList.size() > 0) {
				dataList = new ArrayList<TemplateConfigDto>();
				for (TemplateConfig templateConfig : templateConfigList) {
					// TemplateConfig转TemplateConfigDTO
					TemplateConfigDto templateConfigDTO = convertTemplateConfigDto(templateConfig);
					dataList.add(templateConfigDTO);
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
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	@Override
	public String checkParam(TemplateConfigVo templateConfigVo) {
	    String templateName = templateConfigVo.getTemplateName();
		if (StringUtils.isBlank(templateName)) {
			return "模板名称不能为空";
		}
	    String templateContent = templateConfigVo.getTemplateContent();
		if (StringUtils.isBlank(templateContent)) {
			return "模板内容不能为空";
		}
	    String buildPath = templateConfigVo.getBuildPath();
		if (StringUtils.isBlank(buildPath)) {
			return "生成路径不能为空";
		}
	    String remarks = templateConfigVo.getRemarks();
		if (StringUtils.isBlank(remarks)) {
			return "备注不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	@Override
	public String checkUnique(TemplateConfigVo TemplateConfigVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("templateName", TemplateConfigVo.getTemplateName());
		List<TemplateConfig> templateConfigList = templateConfigDao.select(paramMap);
		if (null == templateConfigList || templateConfigList.size() < 1) {
			return null;
		}

		Integer id = TemplateConfigVo.getId();
		if (null != id) {
			for (TemplateConfig entity : templateConfigList) {
				if (!entity.getId().equals(id) && entity.getTemplateName().equals(TemplateConfigVo.getTemplateName())) {
					return "模板名称已经存在";
				}
			}
		} else {
			return "模板名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertTemplateConfig
	 * @Description: TemplateConfigVo转TemplateConfig
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	private TemplateConfig convertTemplateConfig(TemplateConfigVo templateConfigVo) {
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setId(templateConfigVo.getId());
		templateConfig.setTemplateName(templateConfigVo.getTemplateName());
		templateConfig.setTemplateContent(templateConfigVo.getTemplateContent());
		templateConfig.setBuildPath(templateConfigVo.getBuildPath());
		templateConfig.setRemarks(templateConfigVo.getRemarks());
		templateConfig.setCreateDate(templateConfigVo.getCreateDate());
		templateConfig.setUpdateDate(templateConfigVo.getUpdateDate());
		templateConfig.setOrderId(templateConfigVo.getOrderId());
		templateConfig.setProjectConfigId(templateConfigVo.getProjectConfigId());
		templateConfig.setStatus(templateConfigVo.getStatus());
		templateConfig.setSuffix(templateConfigVo.getSuffix());
		return templateConfig;
	}

	/**
	 * @Title: convertTemplateConfigDto
	 * @Description: TemplateConfig转TemplateConfigDto
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfig
	 * @return
	 */
	private TemplateConfigDto convertTemplateConfigDto(TemplateConfig templateConfig) {
		TemplateConfigDto dto = new TemplateConfigDto();
		dto.setId(templateConfig.getId());
		dto.setTemplateName(templateConfig.getTemplateName());
		dto.setTemplateContent(templateConfig.getTemplateContent());
		dto.setBuildPath(templateConfig.getBuildPath());
		dto.setRemarks(templateConfig.getRemarks());
		dto.setCreateDate(templateConfig.getCreateDate());
		dto.setUpdateDate(templateConfig.getUpdateDate());
		dto.setOrderId(templateConfig.getOrderId());
		dto.setProjectConfigId(templateConfig.getProjectConfigId());
		dto.setStatus(templateConfig.getStatus());
		dto.setSuffix(templateConfig.getSuffix());
		if(null != templateConfig.getProjectConfigId()){
			ProjectConfig projectConfig = projectConfigDao.get(templateConfig.getProjectConfigId());
			if(null != projectConfig){
				String projectConfigIdName = projectConfig.getprojectName();
				dto.setProjectConfigIdName(projectConfigIdName);
			}
		}
		
		return dto;
	}

}
