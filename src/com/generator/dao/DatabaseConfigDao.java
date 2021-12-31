package com.generator.dao;

import com.generator.common.dao.BaseDao;
import org.springframework.stereotype.Repository;
import com.generator.domain.entity.DatabaseConfig;

/**
 * @ClassName: DatabaseConfigDao
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:30
 */
@Repository
public interface DatabaseConfigDao extends BaseDao<DatabaseConfig,Integer>{

}
