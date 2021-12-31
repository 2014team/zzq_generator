package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: ProductType
 * @Description: 产品类别
 * @author zhuzq
 * @date 2021年04月10日 13:18:27
 */ 
public class ProductType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer productTypeId;
	/**
	 * 分类名称
	 */
	private String typeName;
	
	private Integer sort;
 
	public Integer getProductTypeId(){
		return this.productTypeId;
	}
	
	public void setProductTypeId(Integer productTypeId){
		this.productTypeId = productTypeId;
	}
	public String getTypeName(){
		return this.typeName;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}