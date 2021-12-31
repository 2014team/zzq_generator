package com.generator.dao;

import com.generator.common.dao.BaseDao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.generator.domain.entity.TemplateConfig;

/**
 * @ClassName: TemplateConfigDao
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:04
 */
@Repository
public interface TemplateConfigDao extends BaseDao<TemplateConfig,Integer>{

	List<TemplateConfig> getBatchByMap(Map<String, Object> paramMap);

}
