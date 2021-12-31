
package com.generator.domain.entity;

import com.generator.common.entity.BaseEntity;

/**
 * @ClassName: Product
 * @Description: 产品展示
 * @author zhuzq
 * @date 2021年04月10日 10:55:13
 */
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer productId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 封面图片
	 */
	private String coverImage;

	/**
	 * 简介描述
	 */
	private String describe;

	/**
	 * 内容介绍
	 */
	private String content;

	/**
	 * 0:上架1：下架
	 */
	private Integer status;

	/**
	 * 产品类别Id
	 */
	private Integer productTypeId;
	
	private Integer sort;

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverImage() {
		return this.coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	
	public Integer getSort() {
		return sort;
	}

	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}