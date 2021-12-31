package com.generator.service;

import java.util.List;
import java.util.Map;

import com.generator.annotation.AdminServiceLog;
import com.generator.common.entity.AdminResultByPage;
import com.generator.common.service.BaseService;
import com.generator.domain.dto.MenuDto;
import com.generator.domain.dto.MenuTreeDto;
import com.generator.domain.entity.Menu;
import com.generator.domain.vo.MenuVo;

/**
 * @ClassName: MenuDao
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:51
 */
public interface MenuService extends BaseService<Menu,Integer>{

	/**
	 * @Title: saveMenu
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public boolean saveMenu(MenuVo menuVo);

	/**
	 * @Title: deleteMenu
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	public boolean deleteMenu(Integer menuId);

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuIdArr
	 * @return
	 */
	public int deleteByBatch(Integer[] menuIdArr);

	/**
	 * @Title: updateMenu
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public boolean updateMenu(MenuVo menuVo);

	/**
	 * @Title: getMenu
	 * @Description: 根据menuId获取对象
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	public MenuDto getMenu(Integer menuId);

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @param jsonResult
	 * @return
	 */
	public AdminResultByPage findByPage(MenuVo menuVo, AdminResultByPage jsonResult);

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public String checkParam(MenuVo menuVo);

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	public String checkUnique(MenuVo menuVo);
	
	/**
	* @Title: selectList
	* @Description: 查询列表
	* @author zhuzq
	* @date  2020年5月6日 上午11:08:39
	* @return
	*/
	public List<MenuDto> selectList(Map<String, Object> paramMap);

	public List<MenuTreeDto> getMenuTree();

	public String getMenuIds(Integer roleId);

	/**
	* @Title: getMenuByIndex
	* @Description: 获取菜单列表
	* @author zhuzq
	* @date  2021年4月5日 下午3:27:07
	* @return
	*/
	public Map<String,MenuDto> getMenuByIndex();

}
