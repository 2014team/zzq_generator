//
//package com.generator.constant;
//
//import org.apache.commons.lang.StringUtils;
//
///**
//* @ClassName: DatabaseTypeConfigEnum
//* @Description: 数据库类型
//* @author zhuzq
//* @date 2021年12月25日 下午5:44:00
//*/
//public enum GeneratorFileTypeEnum {
//	entity(1, "entity.ftl",".java"), 
//	dao(2, "dao.ftl","Dao.java"),
//	service(3, "service.ftl","Service.java"),
//	serviceImpl(4, "serviceImpl.ftl","ServiceImpl.java"),
//	controller(5, "controller.ftl","Controller.java"),
//	vo(6, "vo.ftl","Vo.java"),
//	dto(7, "dto.ftl","Dto.java"),
//	mapper(8, "mapper.ftl","Mapper.xml"),
//	list(9, "list.ftl",".jsp"),
//	edit(10, "edit.ftl",".jsp"),
//	;
//
//	
//	private Integer value;
//	// 显示名称
//	private String displayName;
//	
//	private String suffixName;
//
//	GeneratorFileTypeEnum(int value, String displayName,String suffixName) {
//		this.value = value;
//		this.displayName = displayName;
//		this.suffixName = suffixName;
//	}
//
//	public static String getNameByValue(Integer value) {
//		GeneratorFileTypeEnum type = getByValue(value);
//		return null == type ? "" : type.name();
//	}
//
//	public static String getDisplayNameByValue(Integer value) {
//		GeneratorFileTypeEnum type = getByValue(value);
//		return null == type ? "" : type.getDisplayName();
//	}
//	
//	public static GeneratorFileTypeEnum getGeneratorFileTypeEnumByNameByValue(String displayName) {
//		if (StringUtils.isNotEmpty(displayName)) {
//			for (GeneratorFileTypeEnum type : GeneratorFileTypeEnum.values()) {
//				if (type.getDisplayName().equals(displayName)) {
//					return type;
//				}
//			}
//		}
//		return null;
//	}
//
//	public static GeneratorFileTypeEnum getByValue(Integer value) {
//		if (null != value) {
//			for (GeneratorFileTypeEnum type : GeneratorFileTypeEnum.values()) {
//				if (type.getValue() == value) {
//					return type;
//				}
//			}
//		}
//		return null;
//	}
//
//	public static Integer getValueByName(String name) {
//		if (null != name && "".equals(name.trim())) {
//			GeneratorFileTypeEnum type = GeneratorFileTypeEnum.valueOf(name);
//			return null == type ? 0 : type.value;
//		}
//		return 0;
//	}
//
//	public int getValue() {
//		return value;
//	}
//
//	public void setValue(int value) {
//		this.value = value;
//	}
//
//	public String getDisplayName() {
//		return displayName;
//	}
//
//	public void setDisplayName(String displayName) {
//		this.displayName = displayName;
//	}
//
//	public String getSuffixName() {
//		return suffixName;
//	}
//
//	public void setSuffixName(String suffixName) {
//		this.suffixName = suffixName;
//	}
//	
//}
