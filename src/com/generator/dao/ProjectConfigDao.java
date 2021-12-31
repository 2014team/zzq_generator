package com.generator.dao;

import com.generator.common.dao.BaseDao;
import org.springframework.stereotype.Repository;
import com.generator.domain.entity.ProjectConfig;

/**
 * @ClassName: ProjectConfigDao
 * @Description: 项目配置管理
 * @author zhuzq
 * @date 2021年12月28日 16:37:20
 */
@Repository
public interface ProjectConfigDao extends BaseDao<ProjectConfig,Integer>{

}
