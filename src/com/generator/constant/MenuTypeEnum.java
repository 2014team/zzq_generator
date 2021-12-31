
package com.generator.constant;

/**
 * @ClassName: MenuTypeEnum
 * @Description: 菜单按钮类型
 * @author zhuzq
 * @date 2020年4月16日 下午3:46:06
 */
public enum MenuTypeEnum {
	MENU(0, "菜单"), // 菜单
	BUTtON(1, "按钮"),// 按钮
	;

	private Integer value;
	// 显示名称
	private String displayName;

	MenuTypeEnum(int value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static String getNameByValue(Integer value) {
		MenuTypeEnum type = getByValue(value);
		return null == type ? "" : type.name();
	}

	public static String getDisplayNameByValue(Integer value) {
		MenuTypeEnum type = getByValue(value);
		return null == type ? "" : type.getDisplayName();
	}

	public static MenuTypeEnum getByValue(Integer value) {
		if (null != value) {
			for (MenuTypeEnum type : MenuTypeEnum.values()) {
				if (type.getValue() == value) {
					return type;
				}
			}
		}
		return null;
	}

	public static Integer getValueByName(String name) {
		if (null != name && "".equals(name.trim())) {
			MenuTypeEnum type = MenuTypeEnum.valueOf(name);
			return null == type ? 0 : type.value;
		}
		return 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
