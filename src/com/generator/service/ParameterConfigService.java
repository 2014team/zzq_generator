package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.ParameterConfig;
import com.generator.domain.vo.ParameterConfigVo;
import com.generator.domain.dto.ParameterConfigDto;

import java.util.List;
import java.util.Map;

import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: ParameterConfigDao
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:36
 */
public interface ParameterConfigService extends BaseService<ParameterConfig,Integer>{

	/**
	 * @Title: saveParameterConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	public boolean saveParameterConfig(ParameterConfigVo parameterConfigVo);

	/**
	 * @Title: deleteParameterConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigId
	 * @return
	 */
	public boolean deleteParameterConfig(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] parameterConfigIdArr);

	/**
	 * @Title: updateParameterConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	public boolean updateParameterConfig(ParameterConfigVo parameterConfigVo);

	/**
	 * @Title: getParameterConfig
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigId
	 * @return
	 */
	public ParameterConfigDto getParameterConfig(Integer parameterConfigId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(ParameterConfigVo parameterConfigVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	public String checkParam(ParameterConfigVo parameterConfigVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月09日 16:15:36
	 * @param parameterConfigVo
	 * @return
	 */
	public String checkUnique(ParameterConfigVo parameterConfigVo);

}
