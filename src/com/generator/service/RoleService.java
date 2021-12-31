package com.generator.service;

import java.util.List;
import java.util.Map;

import com.generator.common.entity.AdminResultByPage;
import com.generator.common.service.BaseService;
import com.generator.domain.dto.RoleDto;
import com.generator.domain.entity.Role;
import com.generator.domain.vo.RoleVo;

/**
 * @ClassName: RoleDao
 * @Description: 角色
 * @author zhuzq
 * @date 2020年04月30日 14:04:26
 */
public interface RoleService extends BaseService<Role,Integer>{

	/**
	 * @Title: saveRole
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public boolean saveRole(RoleVo roleVo);

	/**
	 * @Title: deleteRole
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	public boolean deleteRole(Integer roleId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] roleIdArr);

	/**
	 * @Title: updateRole
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public boolean updateRole(RoleVo roleVo);

	/**
	 * @Title: getRole
	 * @Description: 根据roleId获取对象
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleId
	 * @return
	 */
	public RoleDto getRole(Integer roleId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(RoleVo roleVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public String checkParam(RoleVo roleVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年04月30日 14:04:26
	 * @param roleVo
	 * @return
	 */
	public String checkUnique(RoleVo roleVo);

	/**
	* @Title: updateValidFlag
	* @Description: 更新状态
	* @author zhuzq
	* @date  2020年5月3日 上午11:35:21
	* @param roleVo
	* @return
	*/
	public boolean updateValidFlag(RoleVo roleVo);

	/**
	* @Title: selectList
	* @Description: 获取列表
	* @author zhuzq
	* @date  2020年5月3日 下午2:32:59
	* @return
	*/
	public List<RoleDto> selectList(Map<String, Object> paramMap);

	/**
	* @Title: saveRight
	* @Description: 保存权限
	* @author zhuzq
	* @date  2020年5月14日 下午1:43:05
	* @param roleId
	* @param menuIdArr
	* @return
	*/
	public boolean saveRight(Integer roleId,Integer[] menuIdArr);
	

}
