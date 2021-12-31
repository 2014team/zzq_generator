package com.generator.dao;

import com.generator.common.dao.BaseDao;
import org.springframework.stereotype.Repository;
import com.generator.domain.entity.DatabaseTypeConfig;

/**
 * @ClassName: DatabaseTypeConfigDao
 * @Description: 数据库类型配置
 * @author zhuzq
 * @date 2021年12月25日 17:37:05
 */
@Repository
public interface DatabaseTypeConfigDao extends BaseDao<DatabaseTypeConfig,Integer>{

}
