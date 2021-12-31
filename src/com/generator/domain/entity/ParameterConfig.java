package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: ParameterConfig
 * @Description: 参数配置
 * @author zhuzq
 * @date 2021年12月09日 16:15:36
 */ 
public class ParameterConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 参数key
	 */
	private String paramKey;
	/**
	 * 参数value
	 */
	private String paramValue;
	/**
	 * 备注
	 */
	private String remarks;
	private boolean checked;
	
	/**
	 * 排序号
	 */
	private Integer orderId;
	/**
	 * 项目配置ID
	 */
	private Integer projectConfigId;
	/**
	 * 状态 0有效1无效
	 */
	private Integer status;
	
	
	
	
	
	
 
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProjectConfigId() {
		return projectConfigId;
	}

	public void setProjectConfigId(Integer projectConfigId) {
		this.projectConfigId = projectConfigId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getParamKey(){
		return this.paramKey;
	}
	
	public void setParamKey(String paramKey){
		this.paramKey = paramKey;
	}
	public String getParamValue(){
		return this.paramValue;
	}
	
	public void setParamValue(String paramValue){
		this.paramValue = paramValue;
	}
	public String getRemarks(){
		return this.remarks;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
}