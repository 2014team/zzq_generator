package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: DatabaseTypeConfig
 * @Description: 数据库类型配置
 * @author zhuzq
 * @date 2021年12月25日 17:37:05
 */ 
public class DatabaseTypeConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 编号
	 */
	private String code;
 
	public String getTypeName(){
		return this.typeName;
	}
	
	public void setTypeName(String typeName){
		this.typeName = typeName;
	}
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
}