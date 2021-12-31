
package com.generator.domain.dto;

import java.util.List;

import com.generator.common.entity.BaseEntity;
import com.generator.domain.entity.Menu;

/**
 * @ClassName: MenuDto
 * @Description: 菜单
 * @author zhuzq
 * @date 2020年05月04日 13:39:50
 */
public class MenuDto extends Menu {

	private static final long serialVersionUID = 1L;

	private List<MenuDto> childList;

	
	public List<MenuDto> getChildList() {
		return childList;
	}

	
	public void setChildList(List<MenuDto> childList) {
		this.childList = childList;
	}

	
	

}