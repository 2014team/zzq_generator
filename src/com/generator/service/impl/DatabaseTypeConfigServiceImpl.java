package com.generator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.dao.DatabaseTypeConfigDao;
import com.generator.domain.entity.DatabaseTypeConfig;
import com.generator.service.DatabaseTypeConfigService;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.domain.vo.DatabaseTypeConfigVo;
import com.generator.domain.dto.DatabaseTypeConfigDto;
import com.generator.common.entity.AdminResultByPage;
import com.generator.annotation.AdminServiceLog;

/**
 * @ClassName: DatabaseTypeConfigServiceImpl
 * @Description: 数据库类型配置
 * @author zhuzq
 * @date 2021年12月25日 17:37:06
 */
@Service
public class DatabaseTypeConfigServiceImpl extends BaseServiceImpl<DatabaseTypeConfig,Integer>  implements DatabaseTypeConfigService {
	
	@Autowired
	private DatabaseTypeConfigDao databaseTypeConfigDao;


	/**
	 * @Title: saveDatabaseTypeConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@AdminServiceLog(description="数据库类型配置保存")
	@Override
	public boolean saveDatabaseTypeConfig(DatabaseTypeConfigVo databaseTypeConfigVo) {
		// DatabaseTypeConfigVo转DatabaseTypeConfig
		DatabaseTypeConfig databaseTypeConfig = convertDatabaseTypeConfig(databaseTypeConfigVo);
		Integer result = databaseTypeConfigDao.save(databaseTypeConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteDatabaseTypeConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigId
	 * @return
	 */
	@AdminServiceLog(description="数据库类型配置 删除")
	@Override
	public boolean deleteDatabaseTypeConfig(Integer id) {
		Integer result = databaseTypeConfigDao.delete(id);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigIdArr
	 * @return
	 */
	@AdminServiceLog(description="数据库类型配置 批量删除")
	@Override
	public int deleteByBatch(Integer[] idArr) {
		List<Integer> idList = Arrays.asList(idArr);
		return databaseTypeConfigDao.deleteByBatch(idList);
	}

	/**
	 * @Title: updateDatabaseTypeConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@AdminServiceLog(description="数据库类型配置 批量修改")
	@Override
	public boolean updateDatabaseTypeConfig(DatabaseTypeConfigVo databaseTypeConfigVo) {
		// DatabaseTypeConfigVo转DatabaseTypeConfig
		DatabaseTypeConfig databaseTypeConfig = convertDatabaseTypeConfig(databaseTypeConfigVo);
		Integer result = databaseTypeConfigDao.update(databaseTypeConfig);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: getDatabaseTypeConfig
	 * @Description: 根据databaseTypeConfigId获取数据库类型配置
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigId
	 * @return
	 */
	 @AdminServiceLog(description="数据库类型配置根据databaseTypeConfigId获取数据库类型配置")
	@Override
	public DatabaseTypeConfigDto getDatabaseTypeConfig(Integer databaseTypeConfigId) {
		DatabaseTypeConfigDto databaseTypeConfigDTO = null;
		DatabaseTypeConfig databaseTypeConfig = databaseTypeConfigDao.get(databaseTypeConfigId);
		if (null != databaseTypeConfig) {
			databaseTypeConfigDTO = convertDatabaseTypeConfigDto(databaseTypeConfig);
		}
		return databaseTypeConfigDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="数据库类型配置分页查找")
	@Override
	public AdminResultByPage findByPage(DatabaseTypeConfigVo databaseTypeConfigVo, AdminResultByPage jsonResult) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("databaseTypeConfigVo", databaseTypeConfigVo);
		paramMap.put("page", jsonResult);

		int count = databaseTypeConfigDao.findByPageCount(paramMap);

		if (count > 0) {
			List<DatabaseTypeConfigDto> dataList = null;
			List<DatabaseTypeConfig> databaseTypeConfigList = findByPage(paramMap);
			if (null != databaseTypeConfigList && databaseTypeConfigList.size() > 0) {
				dataList = new ArrayList<DatabaseTypeConfigDto>();
				for (DatabaseTypeConfig databaseTypeConfig : databaseTypeConfigList) {
					// DatabaseTypeConfig转DatabaseTypeConfigDTO
					DatabaseTypeConfigDto databaseTypeConfigDTO = convertDatabaseTypeConfigDto(databaseTypeConfig);
					dataList.add(databaseTypeConfigDTO);
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
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@Override
	public String checkParam(DatabaseTypeConfigVo databaseTypeConfigVo) {
	    String typeName = databaseTypeConfigVo.getTypeName();
		if (StringUtils.isBlank(typeName)) {
			return "类型名称不能为空";
		}
	    String code = databaseTypeConfigVo.getCode();
		if (StringUtils.isBlank(code)) {
			return "编号不能为空";
		}
		return null;
	}


	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	@Override
	public String checkUnique(DatabaseTypeConfigVo DatabaseTypeConfigVo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeName", DatabaseTypeConfigVo.getTypeName());
		List<DatabaseTypeConfig> databaseTypeConfigList = databaseTypeConfigDao.select(paramMap);
		if (null == databaseTypeConfigList || databaseTypeConfigList.size() < 1) {
			return null;
		}

		Integer id = DatabaseTypeConfigVo.getId();
		if (null != id) {
			for (DatabaseTypeConfig entity : databaseTypeConfigList) {
				if (!entity.getId().equals(id) && entity.getTypeName().equals(DatabaseTypeConfigVo.getTypeName())) {
					return "类型名称已经存在";
				}
			}
		} else {
			return "类型名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertDatabaseTypeConfig
	 * @Description: DatabaseTypeConfigVo转DatabaseTypeConfig
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	private DatabaseTypeConfig convertDatabaseTypeConfig(DatabaseTypeConfigVo databaseTypeConfigVo) {
		DatabaseTypeConfig databaseTypeConfig = new DatabaseTypeConfig();
		databaseTypeConfig.setId(databaseTypeConfigVo.getId());
		databaseTypeConfig.setTypeName(databaseTypeConfigVo.getTypeName());
		databaseTypeConfig.setCode(databaseTypeConfigVo.getCode());
		databaseTypeConfig.setCreateDate(databaseTypeConfigVo.getCreateDate());
		databaseTypeConfig.setUpdateDate(databaseTypeConfigVo.getUpdateDate());
		return databaseTypeConfig;
	}

	/**
	 * @Title: convertDatabaseTypeConfigDto
	 * @Description: DatabaseTypeConfig转DatabaseTypeConfigDto
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfig
	 * @return
	 */
	private DatabaseTypeConfigDto convertDatabaseTypeConfigDto(DatabaseTypeConfig databaseTypeConfig) {
		DatabaseTypeConfigDto dto = new DatabaseTypeConfigDto();
		dto.setId(databaseTypeConfig.getId());
		dto.setTypeName(databaseTypeConfig.getTypeName());
		dto.setCode(databaseTypeConfig.getCode());
		dto.setCreateDate(databaseTypeConfig.getCreateDate());
		dto.setUpdateDate(databaseTypeConfig.getUpdateDate());
		return dto;
	}
	
}
