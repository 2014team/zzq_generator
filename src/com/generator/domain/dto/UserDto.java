package com.generator.domain.dto;

import com.generator.domain.entity.User;

public class UserDto extends User {

	private static final long serialVersionUID = 1L;

	
	private String roleName;
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 * */
	private String endDate;

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	public String getRoleName() {
	
		return roleName;
	}

	public void setRoleName(String roleName) {
	
		this.roleName = roleName;
	}

	
	
	
}
