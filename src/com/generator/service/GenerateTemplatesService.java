package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.GenerateTemplates;
import com.generator.domain.vo.GenerateTemplatesVo;
import com.generator.domain.dto.GenerateTemplatesDto;
import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: GenerateTemplatesDao
 * @Description: 模本生成管理
 * @author zhuzq
 * @date 2021年12月27日 09:37:26
 */
public interface GenerateTemplatesService extends BaseService<GenerateTemplates,Integer>{

	/**
	 * @Title: saveGenerateTemplates
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	public boolean saveGenerateTemplates(GenerateTemplatesVo generateTemplatesVo);

	/**
	 * @Title: deleteGenerateTemplates
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesId
	 * @return
	 */
	public boolean deleteGenerateTemplates(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] generateTemplatesIdArr);

	/**
	 * @Title: updateGenerateTemplates
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	public boolean updateGenerateTemplates(GenerateTemplatesVo generateTemplatesVo);

	/**
	 * @Title: getGenerateTemplates
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesId
	 * @return
	 */
	public GenerateTemplatesDto getGenerateTemplates(Integer generateTemplatesId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(GenerateTemplatesVo generateTemplatesVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	public String checkParam(GenerateTemplatesVo generateTemplatesVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月27日 09:37:26
	 * @param generateTemplatesVo
	 * @return
	 */
	public String checkUnique(GenerateTemplatesVo generateTemplatesVo);

	/**
	* @Title: execute
	* @Description: 执行
	* @author zhuzq
	* @date  2021年12月27日 上午11:16:48
	* @param generateTemplatesVo
	* @return
	*/
	public String execute(Integer id);

}
