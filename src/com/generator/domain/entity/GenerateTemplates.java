package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: GenerateTemplates
 * @Description: 模本生成管理
 * @author zhuzq
 * @date 2021年12月27日 09:37:26
 */ 
public class GenerateTemplates extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 组合配置Id
	 */
	private Integer uniteConfigId;
	/**
	 * 表名
	 */
	private String tableName;
	private String extendParam;
	private String pathParam;
	
 
	public String getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(String extendParam) {
		this.extendParam = extendParam;
	}

	public String getPathParam() {
		return pathParam;
	}

	public void setPathParam(String pathParam) {
		this.pathParam = pathParam;
	}

	public Integer getUniteConfigId(){
		return this.uniteConfigId;
	}
	
	public void setUniteConfigId(Integer uniteConfigId){
		this.uniteConfigId = uniteConfigId;
	}
	public String getTableName(){
		return this.tableName;
	}
	
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
}