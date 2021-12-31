package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.DatabaseTypeConfig;
import com.generator.domain.vo.DatabaseTypeConfigVo;
import com.generator.domain.dto.DatabaseTypeConfigDto;
import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: DatabaseTypeConfigDao
 * @Description: 数据库类型配置
 * @author zhuzq
 * @date 2021年12月25日 17:37:06
 */
public interface DatabaseTypeConfigService extends BaseService<DatabaseTypeConfig,Integer>{

	/**
	 * @Title: saveDatabaseTypeConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	public boolean saveDatabaseTypeConfig(DatabaseTypeConfigVo databaseTypeConfigVo);

	/**
	 * @Title: deleteDatabaseTypeConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigId
	 * @return
	 */
	public boolean deleteDatabaseTypeConfig(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] databaseTypeConfigIdArr);

	/**
	 * @Title: updateDatabaseTypeConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	public boolean updateDatabaseTypeConfig(DatabaseTypeConfigVo databaseTypeConfigVo);

	/**
	 * @Title: getDatabaseTypeConfig
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigId
	 * @return
	 */
	public DatabaseTypeConfigDto getDatabaseTypeConfig(Integer databaseTypeConfigId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(DatabaseTypeConfigVo databaseTypeConfigVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	public String checkParam(DatabaseTypeConfigVo databaseTypeConfigVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月25日 17:37:06
	 * @param databaseTypeConfigVo
	 * @return
	 */
	public String checkUnique(DatabaseTypeConfigVo databaseTypeConfigVo);

}
