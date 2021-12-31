
package com.generator.dao;

import org.springframework.stereotype.Repository;

import com.generator.common.dao.BaseDao;
import com.generator.domain.entity.User;

/**
 * @ClassName: UserDao
 * @Description: 用户
 * @author zhuzq
 * @date 2020年4月23日 下午1:42:24
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {


}