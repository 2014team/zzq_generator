package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: ProjectConfig
 * @Description: 项目配置管理
 * @author zhuzq
 * @date 2021年12月28日 16:37:21
 */ 
public class ProjectConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 排序号
	 */
	private Integer orderId;
 
	public String getprojectName(){
		return this.projectName;
	}
	
	public void setprojectName(String projectName){
		this.projectName = projectName;
	}
	public Integer getOrderId(){
		return this.orderId;
	}
	
	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}
}