package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: TemplateConfig
 * @Description: 模板配置
 * @author zhuzq
 * @date 2021年12月23日 17:27:05
 */ 
public class TemplateConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 模板内容
	 */
	private String templateContent;
	/**
	 * 生成路径
	 */
	private String buildPath;
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
	 * 项目配置Id
	 */
	private Integer projectConfigId;
	/**
	 * 状态0有效 1无效
	 */
	private Integer status;
	
	/**
	 * 生成文件后缀
	 */
	private String suffix;
	
	
	
 
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

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

	public String getTemplateName(){
		return this.templateName;
	}
	
	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}
	public String getTemplateContent(){
		return this.templateContent;
	}
	
	public void setTemplateContent(String templateContent){
		this.templateContent = templateContent;
	}
	public String getBuildPath(){
		return this.buildPath;
	}
	
	public void setBuildPath(String buildPath){
		this.buildPath = buildPath;
	}
	public String getRemarks(){
		return this.remarks;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}