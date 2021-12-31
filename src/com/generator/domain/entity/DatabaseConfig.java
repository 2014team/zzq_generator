package com.generator.domain.entity;
import com.generator.common.entity.BaseEntity;
 
/**
 * @ClassName: DatabaseConfig
 * @Description: 数据库配置
 * @author zhuzq
 * @date 2021年12月09日 14:37:30
 */ 
public class DatabaseConfig extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 数据库url
	 */
	private String jdbcUrl;
	/**
	 * 数据库用户名
	 */
	private String jdbcUser;
	/**
	 * 数据库密码
	 */
	private String jdbcPassword;
	/**
	 * 驱动
	 */
	private String jdbcDriver;
	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 数据库库名称
	 */
	private String databaseName;
	/**
	 * 数据库类型
	 */
	private Integer databaseType;
	
	private String databaseTypeName;
	
	/**
	 * 项目配置ID
	 */
	private Integer projectConfigId;
	
	
	
	
	public Integer getProjectConfigId() {
		return projectConfigId;
	}

	public void setProjectConfigId(Integer projectConfigId) {
		this.projectConfigId = projectConfigId;
	}

	public String getDatabaseTypeName() {
		return databaseTypeName;
	}

	public void setDatabaseTypeName(String databaseTypeName) {
		this.databaseTypeName = databaseTypeName;
	}

	public String getJdbcUrl(){
		return this.jdbcUrl;
	}
	
	public void setJdbcUrl(String jdbcUrl){
		this.jdbcUrl = jdbcUrl;
	}
	public String getJdbcUser(){
		return this.jdbcUser;
	}
	
	public void setJdbcUser(String jdbcUser){
		this.jdbcUser = jdbcUser;
	}
	public String getJdbcPassword(){
		return this.jdbcPassword;
	}
	
	public void setJdbcPassword(String jdbcPassword){
		this.jdbcPassword = jdbcPassword;
	}
	public String getJdbcDriver(){
		return this.jdbcDriver;
	}
	
	public void setJdbcDriver(String jdbcDriver){
		this.jdbcDriver = jdbcDriver;
	}
	public String getRemarks(){
		return this.remarks;
	}
	
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public Integer getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(Integer databaseType) {
		this.databaseType = databaseType;
	}

	
	
}