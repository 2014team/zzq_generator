package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.dto.LogDto;
import com.generator.domain.entity.Log;
import com.generator.domain.vo.LogVo;
import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: LogDao
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月14日 22:04:07
 */
public interface LogService extends BaseService<Log, Integer> {

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] logIdArr);

	/**
	 * @Title: getLog
	 * @Description: 根据logId获取对象
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logId
	 * @return
	 */
	public LogDto getLog(Integer logId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年05月14日 22:04:07
	 * @param logVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(LogVo logVo, AdminResultByPage jsonResult);

}
