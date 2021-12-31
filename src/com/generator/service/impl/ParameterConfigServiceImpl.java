package com.generator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.annotation.AdminServiceLog;
import com.generator.common.entity.AdminResultByPage;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.dao.ParameterConfigDao;
import com.generator.dao.ProjectConfigDao;
import com.generator.domain.dto.ParameterConfigDto;
import com.generator.domain.dto.TemplateConfigDto;
import com.generator.domain.entity.ParameterConfig;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.entity.TemplateConfig;
import com.generator.domain.vo.ParameterConfigVo;
import com.generator.service.ParameterConfigService;

/**
 * @ClassName: ParameterConfigServiceImpl
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:37
 */
@Service
public class ParameterConfigServiceImpl extends BaseServiceImpl<ParameterConfig,Integer>  implements ParameterConfigService {
	
	@Autowired
	private ParameterConfigDao parameterConfigDao;
	@Autowired
	private ProjectConfigDao projectConfigDao;


	/**
	 * @Title: saveParameterConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @return
	 */
	@AdminServiceLog(description="参数配置保存")
	@Override
	public boolean saveParameterConfig(ParameterConfigVo parameterConfigVo) {
		// ParameterConfigVo转ParameterConfig
		ParameterConfig parameterConfig = convertParameterConfig(parameterConfigVo);
		Integer result = parameterConfigDao.save(parameterConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteParameterConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigId
	 * @return
	 */
	@AdminServiceLog(description="参数配置 删除")
	@Override
	public boolean deleteParameterConfig(Integer id) {
		Integer result = parameterConfigDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigIdArr
	 * @return
	 */
	@AdminServiceLog(description="参数配置 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return parameterConfigDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateParameterConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @return
	 */
	@AdminServiceLog(description="参数配置 批量修改")
	@Override
	public boolean updateParameterConfig(ParameterConfigVo parameterConfigVo) {
		// ParameterConfigVo转ParameterConfig
		ParameterConfig parameterConfig = convertParameterConfig(parameterConfigVo);
		Integer result = parameterConfigDao.update(parameterConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getParameterConfig
	 * @Description: 根据parameterConfigId获取参数配置
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigId
	 * @return
	 */
	 @AdminServiceLog(description="参数配置根据parameterConfigId获取参数配置")
	@Override
	public ParameterConfigDto getParameterConfig(Integer parameterConfigId) {
		ParameterConfigDto parameterConfigDTO = null;
		ParameterConfig parameterConfig = parameterConfigDao.get(parameterConfigId);
		if (null != parameterConfig) {
			parameterConfigDTO = convertParameterConfigDto(parameterConfig);
		}
		return parameterConfigDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="参数配置分页查找")
	@Override
	public AdminResultByPage findByPage(ParameterConfigVo parameterConfigVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parameterConfigVo", parameterConfigVo);
		paramMap.put("page", jsonResult);

		int count = parameterConfigDao.findByPageCount(paramMap);

		if (count > 0) {
			List<ParameterConfigDto> dataList = null;
			List<ParameterConfig> parameterConfigList = findByPage(paramMap);
			if (null != parameterConfigList && parameterConfigList.size() > 0) {
				dataList = new ArrayList<ParameterConfigDto>();
				for (ParameterConfig parameterConfig : parameterConfigList) {
					// ParameterConfig转ParameterConfigDTO
					ParameterConfigDto parameterConfigDTO = convertParameterConfigDto(parameterConfig);
					dataList.add(parameterConfigDTO);
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
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @return
	 */
	@Override
	public String checkParam(ParameterConfigVo parameterConfigVo) {
	    String paramKey = parameterConfigVo.getParamKey();
		if (StringUtils.isBlank(paramKey)) {
			return "参数key不能为空";
		}
	    String paramValue = parameterConfigVo.getParamValue();
		if (StringUtils.isBlank(paramValue)) {
			return "参数value不能为空";
		}
	    String remarks = parameterConfigVo.getRemarks();
		if (StringUtils.isBlank(remarks)) {
			return "备注不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @return
	 */
	@Override
	public String checkUnique(ParameterConfigVo ParameterConfigVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paramKey", ParameterConfigVo.getParamKey());
		List<ParameterConfig> parameterConfigList = parameterConfigDao.select(paramMap);
		if (null == parameterConfigList || parameterConfigList.size() < 1) {
			return null;
		}

		Integer id = ParameterConfigVo.getId();
		if (null != id) {
			for (ParameterConfig entity : parameterConfigList) {
				if (!entity.getId().equals(id) && entity.getParamKey().equals(ParameterConfigVo.getParamKey())) {
					return "参数key已经存在";
				}
			}
		} else {
			return "参数key已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertParameterConfig
	 * @Description: ParameterConfigVo转ParameterConfig
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfigVo
	 * @return
	 */
	private ParameterConfig convertParameterConfig(ParameterConfigVo parameterConfigVo) {
		ParameterConfig parameterConfig = new ParameterConfig();
		parameterConfig.setId(parameterConfigVo.getId());
		parameterConfig.setParamKey(parameterConfigVo.getParamKey());
		parameterConfig.setParamValue(parameterConfigVo.getParamValue());
		parameterConfig.setRemarks(parameterConfigVo.getRemarks());
		parameterConfig.setCreateDate(parameterConfigVo.getCreateDate());
		parameterConfig.setUpdateDate(parameterConfigVo.getUpdateDate());
		parameterConfig.setOrderId(parameterConfigVo.getOrderId());
		parameterConfig.setProjectConfigId(parameterConfigVo.getProjectConfigId());
		parameterConfig.setStatus(parameterConfigVo.getStatus());
		return parameterConfig;
	}

	/**
	 * @Title: convertParameterConfigDto
	 * @Description: ParameterConfig转ParameterConfigDto
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:37
	 * @param parameterConfig
	 * @return
	 */
	private ParameterConfigDto convertParameterConfigDto(ParameterConfig parameterConfig) {
		ParameterConfigDto dto = new ParameterConfigDto();
		dto.setId(parameterConfig.getId());
		dto.setParamKey(parameterConfig.getParamKey());
		dto.setParamValue(parameterConfig.getParamValue());
		dto.setRemarks(parameterConfig.getRemarks());
		dto.setCreateDate(parameterConfig.getCreateDate());
		dto.setUpdateDate(parameterConfig.getUpdateDate());
		dto.setOrderId(parameterConfig.getOrderId());
		dto.setProjectConfigId(parameterConfig.getProjectConfigId());
		dto.setStatus(parameterConfig.getStatus());
		if(null != parameterConfig.getProjectConfigId()){
			ProjectConfig projectConfig = projectConfigDao.get(parameterConfig.getProjectConfigId());
			if(null != projectConfig){
				String projectConfigIdName = projectConfig.getprojectName();
				dto.setProjectConfigIdName(projectConfigIdName);
			}
		}
		
		
		return dto;
	}

	
}
