
package com.generator.constant;

/**
 * @ClassName: UploadPathEnum
 * @Description: 上传路径
 * @author zhuzq
 * @date 2021年4月9日 下午9:55:45
 */
public enum UploadPathEnum {
	INDEX_BANNER(1, "/upload/banner"), // 首页banner
	LAYEDIT(2, "/upload/layedit"), // 富文本
	PRODUCT(3, "/upload/product"), // 产品展示
	NEWS(4, "/upload/news"), // 资讯发布
	DRIVER(4, "/upload/driver"), //车手介绍
	;

	private Integer value;

	private String name;

	private UploadPathEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(Integer value) {
		UploadPathEnum[] menuTypeEnumArr = UploadPathEnum.values();
		for (UploadPathEnum menuTypeEnum : menuTypeEnumArr) {
			Integer valueEnum = menuTypeEnum.value;
			if (valueEnum.equals(value)) {
				return menuTypeEnum.name;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
