
package com.generator.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.annotation.AdminServiceLog;
import com.generator.constant.ValidFlagEnum;
import com.generator.dao.MenuDao;
import com.generator.dao.RoleDao;
import com.generator.domain.dto.RightDto;
import com.generator.domain.dto.UserDto;
import com.generator.domain.entity.Menu;
import com.generator.domain.entity.Role;
import com.generator.service.RightService;

@Service
public class RightServiceImpl implements RightService {

	private static Map<Integer, Map<String,RightDto>> cacheMap = new LinkedHashMap<Integer,  Map<String,RightDto>>();
	

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MenuDao menuDao;

	/**
	* @Title: checkRight
	* @Description: 权限验证
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:14
	* @param userId
	* @param menuName
	* @param menuUrl
	* @return true:有权限 ：false：没有权限
	*/
	@Override
	public boolean checkRight(Integer userId, String menuName, String menuUrl) {

		if (StringUtils.isBlank(menuName) && StringUtils.isBlank(menuUrl)) {
			return false;
		}

		// 获取用户权限
		Map<Integer, Map<String, RightDto>> map = cacheMap;
		if (null != map && map.size() > 0 && map.containsKey(userId)) {
			Map<String, RightDto> rightDto = map.get(userId);
//			for (Entry<String, RightDto> r : rightDto.entrySet()) {
//				System.out.println("-->"+r.getKey());
//			}
			if (null != rightDto) {
				StringBuffer sb = new StringBuffer();
				sb.append(userId);
				sb.append("_");
				sb.append(menuName);
				if(StringUtils.isNotBlank(menuUrl)){
					sb.append("_");
					sb.append(menuUrl);
						
				}
//				System.out.println("----->"+sb.toString());
				if(rightDto.containsKey(sb.toString())){
					return true;
				}
				
			}
				
//				List<Menu> menuList = rightDto.getMenuList();
//				if (null != menuList && menuList.size() > 0) {
//					for (Menu menu : menuList) {	
//						String name = menu.getMenuName();
//						String url = menu.getMenuUrl();
//						if (StringUtils.isNotBlank(menuName) && StringUtils.isNotBlank(menuUrl)) {
//							// 有权限
//							if (name.equals(menuName) && url.equals(menuUrl)) {
//								return true;
//							}
//						}
//						else if (StringUtils.isNotBlank(name)) {
//							if (name.equals(menuName)) {
//								return true;
//							}
//						}
//
//					}
//				}
//			}

		}
		return false;
	}

	/**
	* @Title: addCache
	* @Description: 新增到缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:25
	* @param userDto
	*/
	@Override
	public void addCache(UserDto userDto) {

		if (null == userDto) {
			return;
		}

		Integer userId = userDto.getUserId();
		Integer roleId = userDto.getRoleId();
		Role role = roleDao.get(roleId);
		if (null == role) {
			return;
		}
		String menuId = role.getMenuId();
		if (StringUtils.isBlank(menuId)) {
			return;
		}

		// 根据menuId获取权限菜单
		List<String> menuIdlist = Arrays.asList(menuId.split(","));
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("validFlag", ValidFlagEnum.ON.ordinal());
		paramMap.put("menuIdlist", menuIdlist);
		List<Menu> menuList = menuDao.selectByBatch(paramMap);

		Date now = new Date();
		RightDto right = new RightDto(now, menuList);
		
		if(cacheMap.size() > 5000) {
			delFirst();
		}
		 Map<String,RightDto> m  = getRightMap(userId, right);
		cacheMap.put(userId, m);

	}
	
	
	public  Map<String,RightDto> getRightMap(Integer userId ,RightDto right){
		Map<String,RightDto> map = new HashMap<String, RightDto>();
		List<Menu> menu = right.getMenuList();
		for (Menu menu2 : menu) {
			StringBuffer sb = new StringBuffer();
			sb.append(userId);
			String menuName  = menu2.getMenuName();
			sb.append("_");
			sb.append(menuName);
			String menuUrl  = menu2.getMenuUrl();
			if(StringUtils.isNotBlank(menuUrl)){
				sb.append("_");
				sb.append(menuUrl);
				
			}
			map.put(sb.toString(), right);			
		}
		
		return map;
		
		
	}

	/**
	* @Title: removeCache
	* @Description: 移除缓存
	* @author zhuzq
	* @date  2020年5月13日 下午11:59:37
	* @param userDto
	*/
	@Override
	public void removeCache(UserDto userDto) {

		if (null == userDto) {
			return;
		}

		Integer userId = userDto.getUserId();

		if (null != cacheMap && cacheMap.size() > 0 && cacheMap.containsKey(userId)) {
			cacheMap.remove(userId);
		}

	}
	
	
	private static Integer delFirst() {
		Integer key = null;
		for (Entry<Integer, Map<String, RightDto>> entry : cacheMap.entrySet()) {
			key = entry.getKey();
			if (key != null) {
				cacheMap.remove(key);
				break;
			}
		}
		return key;
	}

}
