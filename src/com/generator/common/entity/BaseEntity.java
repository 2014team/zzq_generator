package com.generator.common.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	public Integer id;

	/**
	 * 创建时间
	 */
	protected Date createDate;

	/**
	 * 更新时间
	 */
	protected Date updateDate;
	
	protected String searchKey;
	protected String searchValue;
	
	
	// 时间
	protected String createDateStr;

	// 查询-开始时间
	protected String beginDate;

	// 查询-结束时间
	protected String endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
	public String getSearchKey() {
		return searchKey;
	}

	
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	
	public String getSearchValue() {
		return searchValue;
	}

	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	
	public String getCreateDateStr() {
		return createDateStr;
	}

	
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	
	public String getBeginDate() {
		return beginDate;
	}

	
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	
	public String getEndDate() {
		return endDate;
	}

	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
