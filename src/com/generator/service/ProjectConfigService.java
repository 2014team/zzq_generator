package com.generator.service;

import com.generator.common.service.BaseService;
import com.generator.domain.entity.ProjectConfig;
import com.generator.domain.vo.ProjectConfigVo;
import com.generator.domain.dto.ProjectConfigDto;
import com.generator.common.entity.AdminResultByPage;

/**
 * @ClassName: ProjectConfigDao
 * @Description: 项目配置管理
 * @author zhuzq
 * @date 2021年12月28日 16:37:21
 */
public interface ProjectConfigService extends BaseService<ProjectConfig,Integer>{

	/**
	 * @Title: saveProjectConfig
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	public boolean saveProjectConfig(ProjectConfigVo projectConfigVo);

	/**
	 * @Title: deleteProjectConfig
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigId
	 * @return
	 */
	public boolean deleteProjectConfig(Integer id);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] projectConfigIdArr);

	/**
	 * @Title: updateProjectConfig
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	public boolean updateProjectConfig(ProjectConfigVo projectConfigVo);

	/**
	 * @Title: getProjectConfig
	 * @Description: 根据id获取对象
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigId
	 * @return
	 */
	public ProjectConfigDto getProjectConfig(Integer projectConfigId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(ProjectConfigVo projectConfigVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	public String checkParam(ProjectConfigVo projectConfigVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2021年12月28日 16:37:21
	 * @param projectConfigVo
	 * @return
	 */
	public String checkUnique(ProjectConfigVo projectConfigVo);

}
