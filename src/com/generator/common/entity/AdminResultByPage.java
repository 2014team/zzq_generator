
package com.generator.common.entity;

public class AdminResultByPage extends PageBean {

	private static final long serialVersionUID = 1L;

	private int code;

	private String msg;

	private Object data;
	
	public AdminResultByPage() {
	}
	
	public AdminResultByPage(int page, int limit) {
		super(page, limit);
	}
	
	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	
	/**
	 * @Title: success
	 * @Description: 成功
	 */
	public void success() {

		this.code = 0;
		this.msg = "请求成功";
	}

	public void success(Object data) {

		success();
		this.data = data;
	}

	public void success(String msg) {

		success();
		this.msg = msg;
	}

	/**
	 * @Title: failure
	 * @Description: 失败
	 */
	public void failure() {

		this.code = 500;
		this.msg = "请求失败";
	}

	/**
	 * @Title: failure
	 * @Description: 失败
	 */
	public void failure(String msg) {

		failure();
		this.msg = msg;
	}

}
