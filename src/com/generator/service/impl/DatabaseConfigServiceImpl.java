package com.generator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.DatabaseConfigDao;
import com.generator.dao.DatabaseTypeConfigDao;
import com.generator.dao.ProjectConfigDao;
import com.generator.domain.entity.DatabaseConfig;
import com.generator.domain.entity.DatabaseTypeConfig;
import com.generator.domain.entity.ProjectConfig;
import com.generator.service.DatabaseConfigService;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.domain.vo.DatabaseConfigVo;
import com.generator.domain.dto.DatabaseConfigDto;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminServiceLog;

/**
 * @ClassName: DatabaseConfigServiceImpl
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:30
 */
@Service
public class DatabaseConfigServiceImpl extends BaseServiceImpl<DatabaseConfig,Integer>  implements DatabaseConfigService {
	
	@Autowired
	private DatabaseConfigDao databaseConfigDao;
	
	@Autowired
	private DatabaseTypeConfigDao databaseTypeConfigDao;
	
	@Autowired
	private ProjectConfigDao projectConfigDao;


	/**
	 * @Title: saveDatabaseConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	@AdminServiceLog(description="数据库配置保存")
	@Override
	public boolean saveDatabaseConfig(DatabaseConfigVo databaseConfigVo) {
		// DatabaseConfigVo转DatabaseConfig
		DatabaseConfig databaseConfig = convertDatabaseConfig(databaseConfigVo);
		Integer result = databaseConfigDao.save(databaseConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteDatabaseConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigId
	 * @return
	 */
	@AdminServiceLog(description="数据库配置 删除")
	@Override
	public boolean deleteDatabaseConfig(Integer id) {
		Integer result = databaseConfigDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigIdArr
	 * @return
	 */
	@AdminServiceLog(description="数据库配置 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return databaseConfigDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateDatabaseConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	@AdminServiceLog(description="数据库配置 批量修改")
	@Override
	public boolean updateDatabaseConfig(DatabaseConfigVo databaseConfigVo) {
		// DatabaseConfigVo转DatabaseConfig
		DatabaseConfig databaseConfig = convertDatabaseConfig(databaseConfigVo);
		Integer result = databaseConfigDao.update(databaseConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getDatabaseConfig
	 * @Description: 根据databaseConfigId获取数据库配置
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigId
	 * @return
	 */
	 @AdminServiceLog(description="数据库配置根据databaseConfigId获取数据库配置")
	@Override
	public DatabaseConfigDto getDatabaseConfig(Integer databaseConfigId) {
		DatabaseConfigDto databaseConfigDTO = null;
		DatabaseConfig databaseConfig = databaseConfigDao.get(databaseConfigId);
		if (null != databaseConfig) {
			databaseConfigDTO = convertDatabaseConfigDto(databaseConfig);
		}
		return databaseConfigDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="数据库配置分页查找")
	@Override
	public AdminResultByPage findByPage(DatabaseConfigVo databaseConfigVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("databaseConfigVo", databaseConfigVo);
		paramMap.put("page", jsonResult);

		int count = databaseConfigDao.findByPageCount(paramMap);

		if (count > 0) {
			List<DatabaseConfigDto> dataList = null;
			List<DatabaseConfig> databaseConfigList = findByPage(paramMap);
			if (null != databaseConfigList && databaseConfigList.size() > 0) {
				dataList = new ArrayList<DatabaseConfigDto>();
				for (DatabaseConfig databaseConfig : databaseConfigList) {
					// DatabaseConfig转DatabaseConfigDTO
					DatabaseConfigDto databaseConfigDTO = convertDatabaseConfigDto(databaseConfig);
					dataList.add(databaseConfigDTO);
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
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	@Override
	public String checkParam(DatabaseConfigVo databaseConfigVo) {
	    String jdbcUrl = databaseConfigVo.getJdbcUrl();
		if (StringUtils.isBlank(jdbcUrl)) {
			return "数据库url不能为空";
		}
	    String jdbcUser = databaseConfigVo.getJdbcUser();
		if (StringUtils.isBlank(jdbcUser)) {
			return "数据库用户名不能为空";
		}
	    String jdbcPassword = databaseConfigVo.getJdbcPassword();
		if (StringUtils.isBlank(jdbcPassword)) {
			return "数据库密码不能为空";
		}
	    String jdbcDriver = databaseConfigVo.getJdbcDriver();
		if (StringUtils.isBlank(jdbcDriver)) {
			return "驱动不能为空";
		}
	    String remarks = databaseConfigVo.getRemarks();
		if (StringUtils.isBlank(remarks)) {
			return "备注不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	@Override
	public String checkUnique(DatabaseConfigVo DatabaseConfigVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("jdbcUrl", DatabaseConfigVo.getJdbcUrl());
		List<DatabaseConfig> databaseConfigList = databaseConfigDao.select(paramMap);
		if (null == databaseConfigList || databaseConfigList.size() < 1) {
			return null;
		}

		Integer id = DatabaseConfigVo.getId();
		if (null != id) {
			for (DatabaseConfig entity : databaseConfigList) {
				if (!entity.getId().equals(id) && entity.getJdbcUrl().equals(DatabaseConfigVo.getJdbcUrl())) {
					return "数据库url已经存在";
				}
			}
		} else {
			return "数据库url已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertDatabaseConfig
	 * @Description: DatabaseConfigVo转DatabaseConfig
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	private DatabaseConfig convertDatabaseConfig(DatabaseConfigVo databaseConfigVo) {
		DatabaseConfig databaseConfig = new DatabaseConfig();
		databaseConfig.setId(databaseConfigVo.getId());
		databaseConfig.setJdbcUrl(databaseConfigVo.getJdbcUrl());
		databaseConfig.setJdbcUser(databaseConfigVo.getJdbcUser());
		databaseConfig.setJdbcPassword(databaseConfigVo.getJdbcPassword());
		databaseConfig.setJdbcDriver(databaseConfigVo.getJdbcDriver());
		databaseConfig.setRemarks(databaseConfigVo.getRemarks());
		databaseConfig.setDatabaseName(databaseConfigVo.getDatabaseName());
		databaseConfig.setDatabaseType(databaseConfigVo.getDatabaseType());
		databaseConfig.setCreateDate(databaseConfigVo.getCreateDate());
		databaseConfig.setUpdateDate(databaseConfigVo.getUpdateDate());
		databaseConfig.setDatabaseType(databaseConfigVo.getDatabaseType());
		databaseConfig.setProjectConfigId(databaseConfigVo.getProjectConfigId());
		return databaseConfig;
	}

	/**
	 * @Title: convertDatabaseConfigDto
	 * @Description: DatabaseConfig转DatabaseConfigDto
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfig
	 * @return
	 */
	private DatabaseConfigDto convertDatabaseConfigDto(DatabaseConfig databaseConfig) {
		DatabaseConfigDto dto = new DatabaseConfigDto();
		dto.setId(databaseConfig.getId());
		dto.setJdbcUrl(databaseConfig.getJdbcUrl());
		dto.setJdbcUser(databaseConfig.getJdbcUser());
		dto.setJdbcPassword(databaseConfig.getJdbcPassword());
		dto.setJdbcDriver(databaseConfig.getJdbcDriver());
		dto.setRemarks(databaseConfig.getRemarks());
		dto.setDatabaseName(databaseConfig.getDatabaseName());
		dto.setDatabaseType(databaseConfig.getDatabaseType());
		dto.setCreateDate(databaseConfig.getCreateDate());
		dto.setUpdateDate(databaseConfig.getUpdateDate());
		dto.setDatabaseType(databaseConfig.getDatabaseType());
		dto.setProjectConfigId(databaseConfig.getProjectConfigId());
		
		if(null != databaseConfig.getDatabaseType()){
			Map<String,Object> paramMap =  new HashMap<String, Object>();
			paramMap.put("code", databaseConfig.getDatabaseType());
			DatabaseTypeConfig databaseTypeConfig = databaseTypeConfigDao.getOneByMap(paramMap);
			if(null != databaseTypeConfig){
				String databaseTypeName= databaseTypeConfig.getTypeName();
				dto.setDatabaseTypeName(databaseTypeName);
			}
		}
		
		if(null != databaseConfig.getProjectConfigId()){
			 ProjectConfig  projectConfig = projectConfigDao.get(databaseConfig.getProjectConfigId());
			if(null  != projectConfig){
				String projectConfigIdName= projectConfig.getprojectName();
				dto.setProjectConfigIdName(projectConfigIdName);
			}
		}
		
		return dto;
	}
	
}
