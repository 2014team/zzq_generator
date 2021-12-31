package com.generator.dao;

import org.springframework.stereotype.Repository;

import com.generator.common.dao.BaseDao;
import com.generator.domain.entity.Role;

/**
 * @ClassName: RoleDao
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:25
 */
@Repository
public interface RoleDao extends BaseDao<Role,Integer>{
	
	

}
