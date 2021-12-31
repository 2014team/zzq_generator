package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.DatabaseConfig;
import com.generator.domain.vo.DatabaseConfigVo;
import com.generator.domain.dto.DatabaseConfigDto;
import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: DatabaseConfigDao
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:30
 */
public interface DatabaseConfigService extends BaseService<DatabaseConfig,Integer>{

	/**
	 * @Title: saveDatabaseConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	public boolean saveDatabaseConfig(DatabaseConfigVo databaseConfigVo);

	/**
	 * @Title: deleteDatabaseConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigId
	 * @return
	 */
	public boolean deleteDatabaseConfig(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] databaseConfigIdArr);

	/**
	 * @Title: updateDatabaseConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	public boolean updateDatabaseConfig(DatabaseConfigVo databaseConfigVo);

	/**
	 * @Title: getDatabaseConfig
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigId
	 * @return
	 */
	public DatabaseConfigDto getDatabaseConfig(Integer databaseConfigId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(DatabaseConfigVo databaseConfigVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	public String checkParam(DatabaseConfigVo databaseConfigVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月09日 14:37:30
	 * @param databaseConfigVo
	 * @return
	 */
	public String checkUnique(DatabaseConfigVo databaseConfigVo);

}
