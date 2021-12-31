package com.generator.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.generator.common.dao.BaseDao;
import com.generator.domain.entity.ParameterConfig;

/**
 * @ClassName: ParameterConfigDao
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:36
 */
@Repository
public interface ParameterConfigDao extends BaseDao<ParameterConfig,Integer>{
	
	public List<ParameterConfig> getBatchByMap(Map<String,Object> ids);

}
