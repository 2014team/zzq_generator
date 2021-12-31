package com.generator.common.entity;

import java.io.Serializable;

public class PageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 总的记录数
	 */
	protected int count; // 总的记录数

	/**
	 * 页码
	 */
	protected int page = 1;// 第几页

	/**
	 * 每页多少条
	 */
	protected int limit = 10; // 每页多少条

	protected int totalPages = 10; // 总的页数
	
	protected int step = 10; 			// 步长
	protected int stepBeginIndex = 1; 			// 
	protected int stepEndIndex = 10; 			// 

	
	public PageBean() {
	}

	public PageBean(int page, int limit) {
		super();
		this.page = page;
		this.limit = limit;
	}

	public int getTotalPages() {

		totalPages = totalPages < 1 ? 1 : totalPages;
		totalPages = getCount() % getLimit() == 0 ? (getCount() / getLimit()) : (getCount() / getLimit() + 1);
		return totalPages;
	}

	public void setTotalPages(int totalPages) {

		this.totalPages = totalPages;
	}
	
	public int getCount() {

		return count;
	}

	public void setCount(int count) {

		this.count = count;
	}

	public int getPage() {

		return page;
	}

	public void setPage(int page) {

		this.page = page;
	}

	public int getLimit() {

		return limit;
	}

	public void setLimit(int limit) {

		this.limit = limit;
	}

	public int getBegin() {

		return (this.getPage() - 1) * this.getLimit();
	}
	
	public int getEnd() {
		return (this.getPage() - 1) * this.getLimit() + this.getLimit();
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getStepBeginIndex() {
		int index = this.getStep()/2;
		if (this.getPage() <= index) {
			stepBeginIndex = 1;
		} else if ((this.getPage() + index) > this.getTotalPages()) {
			int begin = this.getTotalPages() - this.getStep() + 1;
			stepBeginIndex = begin; // 如果小于0,责等于1
		}else {
			int begin = this.getPage() - index ;
			begin = begin > 0 ? begin : 1; // 如果小于0,责等于1
			stepBeginIndex = begin;
		}
		return stepBeginIndex;
	}

	public void setStepBeginIndex(int stepBeginIndex) {
		this.stepBeginIndex = stepBeginIndex;
	}

	public int getStepEndIndex() {
		int index = this.getStep()/2;
		if (this.getPage() <= index) {
			stepEndIndex =  this.getTotalPages() > this.getStep() ? this.getStep() :this.getTotalPages();
		} else if ((this.getPage() + index) > this.getTotalPages()) {
			int end = this.getTotalPages();
			stepEndIndex = end;
		}else {
			int end = this.getPage() + index -1;
			stepEndIndex=end;
		}
		return stepEndIndex;
	}

	public void setStepEndIndex(int stepEndIndex) {
		this.stepEndIndex = stepEndIndex;
	}
	
	

}
