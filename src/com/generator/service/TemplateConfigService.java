package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.TemplateConfig;
import com.generator.domain.vo.TemplateConfigVo;
import com.generator.domain.dto.TemplateConfigDto;

import java.util.List;
import java.util.Map;

import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: TemplateConfigDao
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:05
 */
public interface TemplateConfigService extends BaseService<TemplateConfig,Integer>{

	/**
	 * @Title: saveTemplateConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	public boolean saveTemplateConfig(TemplateConfigVo templateConfigVo);

	/**
	 * @Title: deleteTemplateConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigId
	 * @return
	 */
	public boolean deleteTemplateConfig(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] templateConfigIdArr);

	/**
	 * @Title: updateTemplateConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	public boolean updateTemplateConfig(TemplateConfigVo templateConfigVo);

	/**
	 * @Title: getTemplateConfig
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigId
	 * @return
	 */
	public TemplateConfigDto getTemplateConfig(Integer templateConfigId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(TemplateConfigVo templateConfigVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	public String checkParam(TemplateConfigVo templateConfigVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月23日 17:27:05
	 * @param templateConfigVo
	 * @return
	 */
	public String checkUnique(TemplateConfigVo templateConfigVo);


}
