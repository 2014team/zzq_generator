
package com.generator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.annotation.AdminServiceLog;
import com.generator.common.entity.AdminResultByPage;
import com.generator.common.service.impl.BaseServiceImpl;
import com.generator.constant.MenuTypeEnum;
import com.generator.constant.ValidFlagEnum;
import com.generator.dao.MenuDao;
import com.generator.dao.RoleDao;
import com.generator.domain.dto.MenuDto;
import com.generator.domain.dto.MenuTreeDto;
import com.generator.domain.entity.Menu;
import com.generator.domain.entity.Role;
import com.generator.domain.vo.MenuVo;
import com.generator.service.MenuService;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:51
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Integer> implements MenuService {
	
	private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RoleDao roleDao;

	/**
	 * @Title: saveMenu
	 * @Description: 保存
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@AdminServiceLog(description="菜单保存")
	@Override
	public boolean saveMenu(MenuVo menuVo) {

		// MenuVo转Menu
		Menu menu = convertMenu(menuVo);
		Integer result = menuDao.save(menu);
		if (null != result && result > 0) {
			//创建子菜单
			createChild(menu);
			return true;
		}
		return false;
	}
	private void createChild(Menu menu){
		Integer menuType = menu.getMenuType();
		 // 菜单
		 if(menuType == 0){
			 Integer addFlag = menu.getAddFlag();
			 Integer batchDeleteFlag = menu.getBatchDeleteFlag();
			 Integer deleteFlag = menu.getDeleteFlag();
			 Integer searchFla = menu.getSearchFlag();
			 Integer updateFlag = menu.getUpdateFlag();
			 if(addFlag == 1){
				 createChildMenu(menu, "增加");
			 }
			 if(batchDeleteFlag == 1){
				 createChildMenu(menu, "批量删除");
			 }
			 if(deleteFlag == 1){
				 createChildMenu(menu, "删除");
			 }
			 if(searchFla == 1){
				 createChildMenu(menu, "查询");
			 }
			 if(updateFlag == 1){
				 createChildMenu(menu, "编辑");
			 }
		 }
	}
	private void createChildMenu(Menu menu,String msg){
		 Menu m = new Menu();
		 m.setMenuName(menu.getMenuName()+"/"+msg);
		 m.setMenuUrl(menu.getMenuName()+"/"+msg);
		 m.setMenuType(MenuTypeEnum.BUTtON.getValue());
		 m.setParentId(menu.getMenuId()+"");
		 m.setSortId(1);
		 Integer save =  menuDao.save(m);
		 logger.info("创建子菜单save="+save);
		 
	}
	private void updateChild(Menu menu){
		Integer menuType = menu.getMenuType();
		// 菜单
		if(menuType == 0){
			Integer addFlag = menu.getAddFlag();
			Integer batchDeleteFlag = menu.getBatchDeleteFlag();
			Integer deleteFlag = menu.getDeleteFlag();
			Integer searchFla = menu.getSearchFlag();
			Integer updateFlag = menu.getUpdateFlag();
			updateChildMenu(menu, "增加",addFlag);
			updateChildMenu(menu, "批量删除",batchDeleteFlag);
			updateChildMenu(menu, "删除",deleteFlag);
			updateChildMenu(menu, "查询",searchFla);
			updateChildMenu(menu, "编辑",updateFlag);
		}
	}
	private void updateChildMenu(Menu menu,String msg,Integer flag){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName",menu.getMenuName()+"/"+msg);
		paramMap.put("menuUrl",menu.getMenuName()+"/"+msg);
		Menu m = menuDao.getOneByMap(paramMap);
		if(flag == 1){
			if(null == m){
				m = new Menu();
				m.setMenuName(menu.getMenuName()+"/"+msg);
				m.setMenuUrl(menu.getMenuName()+"/"+msg);
				m.setMenuType(MenuTypeEnum.BUTtON.getValue());
				m.setParentId(menu.getMenuId()+"");
				m.setSortId(menu.getSortId());
				 m.setValidFlag(ValidFlagEnum.ON.ordinal());
				Integer save =  menuDao.save(m);
				logger.info("修改子菜单save="+save);
			}else{
				m.setMenuName(menu.getMenuName()+"/"+msg);
				m.setMenuUrl(menu.getMenuName()+"/"+msg);
				m.setMenuType(MenuTypeEnum.BUTtON.getValue());
				m.setParentId(menu.getMenuId()+"");
				m.setSortId(menu.getSortId());
				 m.setValidFlag(menu.getValidFlag());
				Integer save =  menuDao.update(m);
				
				logger.info("修改子菜单");
				
			}
		}else if(flag == 2 || flag == 0){
			if(null != m){
				Integer delete = menuDao.delete(m.getMenuId());
				logger.info("删除子菜单delete="+delete);
			}
			
		}
		
	}

	/**
	 * @Title: deleteMenu
	 * @Description: 删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	@AdminServiceLog(description="菜单删除")
	@Override
	public boolean deleteMenu(Integer menuId) {

		Integer result = menuDao.delete(menuId);
		if (null != result && result > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuIdArr
	 * @return
	 */
	@AdminServiceLog(description="菜单批量删除")
	@Override
	public int deleteByBatch(Integer[] menuIdArr) {

		List<Integer> menuIdList = Arrays.asList(menuIdArr);
		return menuDao.deleteByBatch(menuIdList);
	}

	/**
	 * @Title: updateMenu
	 * @Description: 修改
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@AdminServiceLog(description="菜单修改")
	@Override
	public boolean updateMenu(MenuVo menuVo) {

		// MenuVo转Menu
		Menu menu = convertMenu(menuVo);
		Integer result = menuDao.update(menu);
		if (null != result && result > 0) {
			updateChild(menu);
			return true;
		}
		return false;
	}

	/**
	 * @Title: getMenu
	 * @Description: 根据menuId获取菜单
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuId
	 * @return
	 */
	@AdminServiceLog(description="根据menuId获取菜单")
	@Override
	public MenuDto getMenu(Integer menuId) {

		MenuDto menuDTO = null;
		Menu menu = menuDao.get(menuId);
		if (null != menu) {
			menuDTO = menuDtoDeal(menu);
		}
		return menuDTO;
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @author zhuzq * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @param jsonResult
	 * @return
	 */
	@AdminServiceLog(description="查单分页查询")
	@Override
	public AdminResultByPage findByPage(MenuVo menuVo, AdminResultByPage jsonResult) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String menuName = menuVo.getMenuName();
		if (StringUtils.isBlank(menuName)) {
			// 默认选择父类
			menuVo.setParentId("0");
		}

		paramMap.put("menuVo", menuVo);
		paramMap.put("page", jsonResult);

		int count = menuDao.findByPageCount(paramMap);

		if (count > 0) {
			List<MenuDto> dataList = null;
			List<Menu> menuList = findByPage(paramMap);
			if (null != menuList && menuList.size() > 0) {
				dataList = new ArrayList<MenuDto>();
				for (Menu menu : menuList) {
					// Menu转MenuDTO
					MenuDto menuDTO = menuDtoDeal(menu);
					dataList.add(menuDTO);
				}
			}
			jsonResult.setData(dataList);
			jsonResult.setCount(count);
		}
		return jsonResult;
	}

	/**
	 * @Title: checkParam
	 * @Description: 参数验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@AdminServiceLog(description="菜单参数验证")
	@Override
	public String checkParam(MenuVo menuVo) {

		String menuName = menuVo.getMenuName();
		if (StringUtils.isBlank(menuName)) {
			return "菜单名称不能为空";
		}
		String menuUrl = menuVo.getMenuUrl();
		if (StringUtils.isBlank(menuUrl)) {
			menuVo.setMenuUrl("");
		}
		Integer validFlag = menuVo.getValidFlag();
		if (null == validFlag) {
			return "有效标识 0:启用1：停用不能为空";
		}
		Integer sortId = menuVo.getSortId();
		if (null == sortId) {
			return "排序不能为空";
		}
		Integer menuType = menuVo.getMenuType();
		if (null == menuType) {
			return "0:菜单1：按钮不能为空";
		}
		String parentId = menuVo.getParentId();
		if (StringUtils.isBlank(parentId)) {
			menuVo.setParentId("0");
		}
		return null;
	}

	/**
	 * @Title: checkUnique
	 * @Description: 唯一性验证
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	@AdminServiceLog(description="菜单唯一性验证")
	@Override
	public String checkUnique(MenuVo MenuVo) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName", MenuVo.getMenuName());
		List<Menu> menuList = menuDao.select(paramMap);
		if (null == menuList || menuList.size() < 1) {
			return null;
		}

		Integer menuId = MenuVo.getMenuId();
		if (null != menuId) {
			for (Menu entity : menuList) {
				if (!entity.getMenuId().equals(menuId) && entity.getMenuName().equals(MenuVo.getMenuName())) {
					return "菜单名称已经存在";
				}
			}
		}
		else {
			return "菜单名称已经存在";
		}
		return null;

	}

	/**
	 * @Title: convertMenu
	 * @Description: MenuVo转Menu
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menuVo
	 * @return
	 */
	private Menu convertMenu(MenuVo menuVo) {

		Menu menu = new Menu();

		menu.setMenuId(menuVo.getMenuId());
		menu.setMenuName(menuVo.getMenuName());
		menu.setMenuUrl(menuVo.getMenuUrl());
		menu.setValidFlag(menuVo.getValidFlag());
		menu.setSortId(menuVo.getSortId());
		menu.setMenuType(menuVo.getMenuType());
		menu.setCreateDate(menuVo.getCreateDate());
		menu.setUpdateDate(menuVo.getUpdateDate());
		menu.setParentId(menuVo.getParentId());
		menu.setIcon(menuVo.getIcon());
		menu.setAddFlag(menuVo.getAddFlag());
		menu.setDeleteFlag(menuVo.getDeleteFlag());
		menu.setBatchDeleteFlag(menuVo.getBatchDeleteFlag());
		menu.setUpdateFlag(menuVo.getUpdateFlag());
		menu.setSearchFlag(menuVo.getSearchFlag());
		return menu;
	}

	/**
	 * @Title: convertMenuDto
	 * @Description: Menu转MenuDto
	 * @author zhuzq
	 * @date 2020年05月04日 13:39:51
	 * @param menu
	 * @return
	 */
	private MenuDto convertMenuDto(Menu menu) {

		MenuDto dto = new MenuDto();
		dto.setMenuId(menu.getMenuId());
		dto.setMenuName(menu.getMenuName());
		dto.setMenuUrl(menu.getMenuUrl());
		dto.setValidFlag(menu.getValidFlag());
		dto.setSortId(menu.getSortId());
		dto.setMenuType(menu.getMenuType());
		dto.setCreateDate(menu.getCreateDate());
		dto.setUpdateDate(menu.getUpdateDate());
		dto.setParentId(menu.getParentId());
		dto.setIcon(menu.getIcon());
		dto.setAddFlag(menu.getAddFlag());
		dto.setDeleteFlag(menu.getDeleteFlag());
		dto.setBatchDeleteFlag(menu.getBatchDeleteFlag());
		dto.setUpdateFlag(menu.getUpdateFlag());
		dto.setSearchFlag(menu.getSearchFlag());
		return dto;
	}

	private MenuTreeDto convertTreeMenuDto(Menu menu) {

		MenuTreeDto tree = new MenuTreeDto();
		tree.setId(menu.getMenuId());
		tree.setField("menuId");
		tree.setTitle(menu.getMenuName());
		tree.setChecked(false);
		tree.setSpread(false);
		return tree;
	}

	private MenuDto menuDtoDeal(Menu menu) {

		MenuDto result = convertMenuDto(menu);
		Integer menuId = menu.getMenuId();
		if (null != menuId) {
			// 二级菜单
			List<MenuDto> childList = null;
			List<Menu> menuList = menuChildList(menuId);
			if (null != menuList && menuList.size() > 0) {
				childList = new ArrayList<MenuDto>();
				for (Menu m : menuList) {
					MenuDto childDto = convertMenuDto(m);

					// 三级菜单
					List<MenuDto> thirdList = null;
					List<Menu> thirdMenuList = menuChildList(childDto.getMenuId());
					if (null != thirdMenuList && thirdMenuList.size() > 0) {
						thirdList = new ArrayList<MenuDto>();
						for (Menu menu2 : thirdMenuList) {
							MenuDto thirdDto = convertMenuDto(menu2);
							thirdList.add(0,thirdDto);
						}
						childDto.setChildList(thirdList);
					}

					childList.add(0,childDto);
				}
				result.setChildList(childList);
			}

		}

		return result;
	}

	private List<Menu> menuChildList(Integer menuId) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", menuId);
		List<Menu> menuList = menuDao.select(paramMap);
		return menuList;
	}

	/**
	 * @Title: selectList
	 * @Description: 查询列表
	 * @author zhuzq
	 * @date 2020年5月6日 上午11:08:39
	 * @return
	 */
	@AdminServiceLog(description="菜单查询列表")
	@Override
	public List<MenuDto> selectList(Map<String, Object> paramMap) {
		List<MenuDto> menuDtoList = null;
		List<Menu> menuList = menuDao.select(paramMap);
		if (null != menuList && menuList.size() > 0) {
			menuDtoList = new ArrayList<MenuDto>();
			for (Menu menu : menuList) {
				MenuDto menuDto = convertMenuDto(menu);
				menuDtoList.add(menuDto);
			}
		}
		return menuDtoList;
	}

	@AdminServiceLog(description="菜单获取树形列表")
	@Override
	public List<MenuTreeDto> getMenuTree() {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", 0);
		paramMap.put("validFlag",ValidFlagEnum.ON.ordinal());
		List<Menu> menuList = menuDao.select(paramMap);
		List<MenuTreeDto> menuTreeDtoList = new ArrayList<MenuTreeDto>();;
		if (null != menuList && menuList.size() > 0) {

			for (Menu menu : menuList) {
				MenuTreeDto tree = menuDeal(menu);
				menuTreeDtoList.add(tree);
			}
		}
		return menuTreeDtoList;
	}

	private MenuTreeDto menuDeal(Menu menu) {

		MenuTreeDto result = convertTreeMenuDto(menu);
		Integer menuId = menu.getMenuId();
		if (null != menuId) {
			// 二级菜单
			List<MenuTreeDto> childList = null;
			List<Menu> menuList = menuChildList(menuId);
			if (null != menuList && menuList.size() > 0) {
				childList = new ArrayList<MenuTreeDto>();
				for (Menu m : menuList) {
					MenuTreeDto childDto = convertTreeMenuDto(m);

					// 三级菜单
					List<MenuTreeDto> thirdList = null;
					List<Menu> thirdMenuList = menuChildList(childDto.getId());
					if (null != thirdMenuList && thirdMenuList.size() > 0) {
						thirdList = new ArrayList<MenuTreeDto>();
						for (Menu menu2 : thirdMenuList) {
							MenuTreeDto thirdDto = convertTreeMenuDto(menu2);
							thirdList.add(thirdDto);
						}
						childDto.setChildren(thirdList);
					}

					childList.add(childDto);
				}
				result.setChildren(childList);
			}

		}

		return result;
	}

	@AdminServiceLog(description="根据角色ID获取菜单")
	@Override
	public String getMenuIds(Integer roleId) {

		Role role = roleDao.get(roleId);
		List<String> list = null;
		String[] menuIdArr = null;
		if (null != role) {
			String menuId = role.getMenuId();
			if (StringUtils.isNotBlank(menuId)) {
				menuIdArr = menuId.split(",");
				if (null != menuIdArr && menuIdArr.length > 0) {
					list = new ArrayList<String>();
					for (String str : menuIdArr) {
						list.add(str);
					}
				}

				if (null != list && list.size() > 0) {
					// 获取菜单
					Map<String,Object> paramMap = new HashMap<String, Object>();
					paramMap.put("validFlag", ValidFlagEnum.ON.ordinal());
					paramMap.put("menuIdlist", list);
					List<Menu> menuList = menuDao.selectByBatch(paramMap);
					for (String str : menuIdArr) {
						if (null != menuList && menuList.size() > 0) {
							for (Menu menu : menuList) {
								String parentId = menu.getParentId();
								if (parentId.equals(str)) {
									list.remove(str);
								}
							}

						}

					}

				}

			}

		}
		return StringUtils.strip(Arrays.asList(list).toString(), "[]").replaceAll("\\s*", "");

	}

	
	/**
	* @Title: getMenuByIndex
	* @Description: 获取菜单列表
	* @author zhuzq
	* @date  2021年4月5日 下午3:27:07
	* @return
	*/
	@Override
	public Map<String,MenuDto> getMenuByIndex() {
		Map<String,Object> paramMap = null;
		List<Menu> list = menuDao.select(paramMap);
		Map<String,MenuDto> memuMap = new LinkedHashMap<String, MenuDto>();
		if(null != list){
			for (Menu menu : list) {
				MenuDto menuDto = convertMenuDto(menu);
				memuMap.put(String.valueOf(menuDto.getMenuId()), menuDto);
			}
		}
		
		
		for (Map.Entry<String, MenuDto> map : memuMap.entrySet()) {
			MenuDto value = map.getValue();
			
			if("0".equals(value.getParentId())){
				continue;
			}
			
			MenuDto menuDto = memuMap.get(value.getParentId());
			
			if(null != menuDto){
				List<MenuDto> childList = menuDto.getChildList();
				if(null == childList){
					childList = new LinkedList<MenuDto>();
				}
				childList.add(value);
				menuDto.setChildList(childList);
			}
			
			if(null != menuDto){
				memuMap.put(String.valueOf(menuDto.getMenuId()), menuDto);
			}
		}
		
		return memuMap;
	}

}
